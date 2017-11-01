package com.xiemiao.rxnet.exception;

/**
 * Title:服务器返回的定义 <br />
 * Description: <br />
 * Copyright:手趣云商科技有限公司 Copyright(c)2017 <br />
 * author:xiemiao <br />
 * date: 2017-11-01  <br />
 * version 1.0 <br />
 */
public class ServerException extends RuntimeException {
    public int code;
    public String message;

    public ServerException(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
