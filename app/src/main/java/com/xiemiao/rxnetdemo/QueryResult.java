package com.xiemiao.rxnetdemo;

import com.google.gson.annotations.SerializedName;
import com.xiemiao.rxnet.BaseResponse;

/**
 * Title: <br />
 * Description: <br />
 * Copyright:手趣云商科技有限公司 Copyright(c)2017 <br />
 * author:xiemiao <br />
 * date: 2017-11-01  <br />
 * version 1.0 <br />
 */
public class QueryResult extends BaseResponse {

    /**
     * msg : success
     * result : {"zipCode":"430000","province":"湖北","city":"武汉市","cityCode":"027","mobileNumber":"1887115","operator":"移动188卡"}
     * retCode : 200
     */
    @SerializedName("result")
    private ResultEntity resultt;

    public ResultEntity getResultt() {
        return resultt;
    }

    public void setResultt(ResultEntity resultt) {
        this.resultt = resultt;
    }

    public static class ResultEntity {
        /**
         * zipCode : 430000
         * province : 湖北
         * city : 武汉市
         * cityCode : 027
         * mobileNumber : 1887115
         * operator : 移动188卡
         */
        private String zipCode;
        private String province;
        private String city;
        private String cityCode;
        private String mobileNumber;
        private String operator;

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getZipCode() {
            return zipCode;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getCityCode() {
            return cityCode;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public String getOperator() {
            return operator;
        }
    }
}
