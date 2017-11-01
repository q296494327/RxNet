package com.xiemiao.rxnet.transformer;


import com.xiemiao.rxnet.function.HttpErrorFunction;
import com.xiemiao.rxnet.function.HttpResponseFunction;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;

/**
 * Title:Http转换器(处理统一成功数据返回,处理统一返回错误信息) <br />
 * Description: <br />
 * author:xiemiao <br />
 * date: 2017-10-17  <br />
 * version 1.0 <br />
 */
public class HttpTransformer<T> implements ObservableTransformer<T, T> {
    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        //onErrorResumeNext当发生错误的时候，由另外一个Observable来代替当前的Observable并继续发射数据
        return upstream.map(new HttpResponseFunction<T>()).onErrorResumeNext(new HttpErrorFunction<T>());
    }
}
