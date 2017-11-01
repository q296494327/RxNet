package com.xiemiao.rxnet.observer;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xiemiao.rxnet.utils.RxUtils;

import io.reactivex.Observable;

/**
 * Title:包装网络请求的Observable(生命周期,线程调度,错误统一处理) <br />
 * Description: <br />
 * author:xiemiao <br />
 * date: 2017-11-01  <br />
 * version 1.0 <br />
 */
public class ObservablePacking {

    /**
     * 包装一个统一调度的被监听者,无管理生命周期,容易导致内存溢出
     *
     * @param <T>           the type parameter
     * @param apiObservable the api observable
     * @return the observable
     * @User: xiemiao
     * @Time: 2017 -11-01
     * @Date: 20 :00:28
     * @Desc:
     */
    public static <T> Observable<T> getObservable(Observable<T> apiObservable) {
        return apiObservable
                .compose(RxUtils.<T>schedulersTransformer())//线程调度
                .compose(RxUtils.<T>httpTransformer());//网络请求错误统一处理
    }


    /**
     * 包装一个统一调度的被监听者
     * 传入LifecycleProvider自动管理生命周期,避免内存溢出
     * 备注:需要继承RxAppCompatActivity RxFragment
     *
     * @param <T>               the type parameter
     * @param apiObservable     the api observable
     * @param lifecycleProvider the lifecycle provider
     * @return the observable
     * @User: xiemiao
     * @Time: 2017 -11-01
     * @Date: 20 :05:45
     * @Desc:
     */
    public static <T> Observable<T> getObservable(Observable<T> apiObservable, LifecycleProvider lifecycleProvider) {
        Observable<T> observable;
        if (lifecycleProvider != null) {
            observable = apiObservable
                    .compose(RxUtils.<T>schedulersTransformer())//线程调度
                    .compose(lifecycleProvider.bindToLifecycle())//自动管理生命周期
                    .compose(RxUtils.<T>httpTransformer());//网络请求错误统一处理
        } else {
            observable = getObservable(apiObservable);
        }
        return observable;
    }


    /**
     * 包装一个统一调度的被监听者
     * 传入LifecycleProvider<ActivityEvent>手动管理生命周期,避免内存溢出
     * 备注:需要继承RxAppCompatActivity
     *
     * @param <T>               the type parameter
     * @param apiObservable     the api observable
     * @param lifecycleProvider the lifecycle provider
     * @return the observable
     * @User: xiemiao
     * @Time: 2017 -11-01
     * @Date: 20 :05:45
     * @Desc:
     */
    public static <T> Observable<T> getObservable(Observable<T> apiObservable, LifecycleProvider<ActivityEvent> lifecycleProvider, ActivityEvent event) {
        Observable<T> observable;
        if (lifecycleProvider != null) {
            observable = apiObservable
                    .compose(RxUtils.<T>schedulersTransformer())//线程调度
                    .compose(lifecycleProvider.<T>bindUntilEvent(event))//手动管理生命周期
                    .compose(RxUtils.<T>httpTransformer());//网络请求错误统一处理
        } else {
            observable = getObservable(apiObservable);
        }
        return observable;
    }

    /**
     * 包装一个统一调度的被监听者
     * 传入LifecycleProvider<FragmentEvent>手动管理生命周期,避免内存溢出
     * 备注:需要继承RxFragment
     *
     * @param <T>               the type parameter
     * @param apiObservable     the api observable
     * @param lifecycleProvider the lifecycle provider
     * @return the observable
     * @User: xiemiao
     * @Time: 2017 -11-01
     * @Date: 20 :05:45
     * @Desc:
     */
    public static <T> Observable<T> getObservable(Observable<T> apiObservable, LifecycleProvider<FragmentEvent> lifecycleProvider, FragmentEvent event) {
        Observable<T> observable;
        if (lifecycleProvider != null) {
            observable = apiObservable
                    .compose(RxUtils.<T>schedulersTransformer())//线程调度
                    .compose(lifecycleProvider.<T>bindUntilEvent(event))//手动管理生命周期
                    .compose(RxUtils.<T>httpTransformer());//网络请求错误统一处理
        } else {
            observable = getObservable(apiObservable);
        }
        return observable;
    }


}
