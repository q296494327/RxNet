package com.xiemiao.rxnet.function;


import com.xiemiao.rxnet.exception.ExceptionHandle;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Title:http返回结果异常处理 <br />
 * Description: <br />
 * author:xiemiao <br />
 * date: 2017-10-16  <br />
 * version 1.0 <br />
 */
public class HttpErrorFunction<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        return Observable.error(ExceptionHandle.handleException(throwable));
    }
}
