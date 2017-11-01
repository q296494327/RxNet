/**
 * Copyright 2017 JessYan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xiemiao.rxnet.utils;


import com.xiemiao.rxnet.function.HttpErrorFunction;
import com.xiemiao.rxnet.function.HttpResponseFunction;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava相关工具类
 *
 * @User: xiemiao
 * @Time: 2017 -10-19
 * @Date: 01 :38:58
 */
public class RxUtils {

    /**
     * 线程调度
     *
     * @param <T> the type parameter
     * @return the observable transformer
     * @User: xiemiao
     * @Time: 2017 -10-18
     * @Date: 20 :09:44
     */
    public static <T> ObservableTransformer<T, T> schedulersTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 网络请求返回结果错误统一处理
     *
     * @param <T> the type parameter
     * @return the observable transformer
     * @User: xiemiao
     * @Time: 2017 -10-19
     * @Date: 02 :12:01
     */
    public static <T> ObservableTransformer<T, T> httpTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                //onErrorResumeNext当发生错误的时候，由另外一个Observable来代替当前的Observable并继续发射数据
                return upstream.map(new HttpResponseFunction<T>()).onErrorResumeNext(new HttpErrorFunction<T>());
            }
        };
    }

}
