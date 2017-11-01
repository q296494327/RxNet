package com.xiemiao.rxnet.observer;

import android.text.TextUtils;

import com.xiemiao.rxnet.exception.ExceptionHandle;
import com.xiemiao.rxnet.rxmanager.RxActionManagerImpl;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * 适用Retrofit网络请求Observer(监听者)
 * 备注:
 * 1.重写onSubscribe，添加请求标识
 * 2.重写onError，封装错误/异常处理，移除请求
 * 3.重写onNext，移除请求
 * 4.重写cancel，取消请求
 *
 * @param <T> the type parameter
 * @User: xiemiao
 * @Time: 2017 -10-17
 * @Date: 00 :58:08
 */
public abstract class CommonObserver<T> implements Observer<T> {

    private String mTag;//请求标识

    public CommonObserver() {
    }

    public CommonObserver(String tag) {
        this.mTag = tag;
    }

    @Override
    public void onError(Throwable e) {
        //移除网络请求
        RxActionManagerImpl.getInstance().remove(mTag);

        //转换错误异常处理
        if (e instanceof ExceptionHandle.ResponeThrowable) {
            onError((ExceptionHandle.ResponeThrowable) e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }


    @Override
    public void onComplete() {
    }

    @Override
    public void onNext(@NonNull T t) {
        //请求完毕移除请求
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().remove(mTag);
        }
        onSuccess(t);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        //开始订阅,添加请求
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().add(mTag, d);
        }
        onStart(d);
    }

    /**
     * 取消请求
     */
    public void cancel() {
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().cancel(mTag);
        }
    }

    /**
     * 请求是否已经处理
     */
    public boolean isDisposed() {
        if (TextUtils.isEmpty(mTag)) {
            return true;
        }
        return RxActionManagerImpl.getInstance().isDisposed(mTag);
    }

    /**
     * 开始订阅时
     */
    protected abstract void onStart(Disposable d);

    /**
     * 成功回调
     */
    protected abstract void onSuccess(T response);

    /**
     * 错误/异常回调
     */
    protected abstract void onError(ExceptionHandle.ResponeThrowable e);

}
