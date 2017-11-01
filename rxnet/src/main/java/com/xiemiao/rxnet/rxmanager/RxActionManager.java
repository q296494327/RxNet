package com.xiemiao.rxnet.rxmanager;

import io.reactivex.disposables.Disposable;


/**
 * RxJavaAction管理接口
 *
 * @param <T> the type parameter
 */
public interface RxActionManager<T> {
    /**
     * 添加
     *
     * @param tag        the tag
     * @param disposable the disposable
     * @User: xiemiao
     * @Time: 2017 -11-01
     * @Date: 14 :07:28
     */
    void add(T tag, Disposable disposable);

    /**
     * 移除
     *
     * @param tag the tag
     * @User: xiemiao
     * @Time: 2017 -11-01
     * @Date: 14 :07:28
     */
    void remove(T tag);

    /**
     * 取消
     *
     * @param tag the tag
     * @User: xiemiao
     * @Time: 2017 -11-01
     * @Date: 14 :07:28
     */
    void cancel(T tag);

}