package com.softsquared.template.src.main.models;

import com.google.gson.annotations.SerializedName;

public class RegionResponse {
    @SerializedName("isSuccess")
    boolean isSuccess = false;
    @SerializedName("code")
    int code = -1;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegionResponse.result getResult() {
        return result;
    }

    public void setResult(RegionResponse.result result) {
        this.result = result;
    }

    @SerializedName("message")
    String message = "";
    @SerializedName("result")
    result result = new result();

    public static class result {
        @SerializedName("total_value")
        int total_value;
        @SerializedName("pm10_value")
        int pm10_value;
        @SerializedName("pm25_value")
        int pm25_value;
        @SerializedName("no2_value")
        Float no2_value;
        @SerializedName("o3_value")
        Float o3_value;
        @SerializedName("co_value")
        Float co_value;
        @SerializedName("so2_value")
        Float so2_value;

        public result() {
            total_value = pm10_value = pm25_value = -1;
            no2_value = o3_value = co_value = so2_value = (float) -1;
        }

        public int getTotal_value() {
            return total_value;
        }

        public void setTotal_value(int total_value) {
            this.total_value = total_value;
        }

        public int getPm10_value() {
            return pm10_value;
        }

        public void setPm10_value(int pm10_value) {
            this.pm10_value = pm10_value;
        }

        public int getPm25_value() {
            return pm25_value;
        }

        public void setPm25_value(int pm25_value) {
            this.pm25_value = pm25_value;
        }

        public Float getNo2_value() {
            return no2_value;
        }

        public void setNo2_value(Float no2_value) {
            this.no2_value = no2_value;
        }

        public Float getO3_value() {
            return o3_value;
        }

        public void setO3_value(Float o3_value) {
            this.o3_value = o3_value;
        }

        public Float getCo_value() {
            return co_value;
        }

        public void setCo_value(Float co_value) {
            this.co_value = co_value;
        }

        public Float getSo2_value() {
            return so2_value;
        }

        public void setSo2_value(Float so2_value) {
            this.so2_value = so2_value;
        }
    }
}