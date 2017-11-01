package com.xiemiao.rxnetdemo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Title:存放一些常规的API <br />
 * Description: <br />
 * author:xiemiao <br />
 * date: 2017-10-15  <br />
 * version 1.0 <br />
 */
public interface CommonService {
    @GET("v1/mobile/address/query")
    Observable<QueryResult> phoneQuery(@QueryMap Map<String, String> params);
}
