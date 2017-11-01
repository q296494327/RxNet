package com.xiemiao.rxnetdemo;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import retrofit2.Retrofit;

/**
 * Title: <br />
 * Description: <br />
 * Copyright:手趣云商科技有限公司 Copyright(c)2017 <br />
 * author:xiemiao <br />
 * date: 2017-11-01  <br />
 * version 1.0 <br />
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;
    private Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

         /*初始化日志打印相关*/
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // 是否显示日志所在线程
                .methodCount(0)         // 展示方法层的数量(日志所在方法位置)
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("Logger")   // 日志TAG
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                //控制日志是否打印 true 打开日志  false 关闭日志
                return BuildConfig.LOG_DEBUG;
            }
        });

        /*初始化retrofit*/
        mRetrofit = RetrofitUtils.getInstance().retrofit();

    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
