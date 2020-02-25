package com.softsquared.template.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class EtcResponse {
    @SerializedName("isSuccess")
    public boolean isSuccess;

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

    public EtcResponse.etcResult getEtcResult() {
        return etcResult;
    }

    public void setEtcResult(EtcResponse.etcResult etcResult) {
        this.etcResult = etcResult;
    }

    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;

    @SerializedName("etcResult")
    public etcResult etcResult = new etcResult();

    public class etcResult{
        public String getRegion_2depth_name() {
            return region_2depth_name;
        }

        public void setRegion_2depth_name(String region_2depth_name) {
            this.region_2depth_name = region_2depth_name;
        }

        public String getRetgion_3depth_name() {
            return retgion_3depth_name;
        }

        public void setRetgion_3depth_name(String retgion_3depth_name) {
            this.retgion_3depth_name = retgion_3depth_name;
        }

        public Time getCurrent_time() {
            return current_time;
        }

        public void setCurrent_time(Time current_time) {
            this.current_time = current_time;
        }

        public Time getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(Time update_time) {
            this.update_time = update_time;
        }

        public String getPm10_mang_name() {
            return pm10_mang_name;
        }

        public void setPm10_mang_name(String pm10_mang_name) {
            this.pm10_mang_name = pm10_mang_name;
        }

        public String getPm25_mang_name() {
            return pm25_mang_name;
        }

        public void setPm25_mang_name(String pm25_mang_name) {
            this.pm25_mang_name = pm25_mang_name;
        }

        public String getPm10_station() {
            return pm10_station;
        }

        public void setPm10_station(String pm10_station) {
            this.pm10_station = pm10_station;
        }

        public String getPm25_station() {
            return pm25_station;
        }

        public void setPm25_station(String pm25_station) {
            this.pm25_station = pm25_station;
        }

        public String getNo2_station() {
            return no2_station;
        }

        public void setNo2_station(String no2_station) {
            this.no2_station = no2_station;
        }

        public String getO3_station() {
            return o3_station;
        }

        public void setO3_station(String o3_station) {
            this.o3_station = o3_station;
        }

        public String getCo_station() {
            return co_station;
        }

        public void setCo_station(String co_station) {
            this.co_station = co_station;
        }

        public String getSo2_station() {
            return so2_station;
        }

        public void setSo2_station(String so2_station) {
            this.so2_station = so2_station;
        }

        @SerializedName("region_2depth_name")
        String region_2depth_name;
        @SerializedName("retgion_3depth_name")
        String retgion_3depth_name;
        @SerializedName("current_time")
        Time current_time;
        @SerializedName("update_time")
        Time update_time;
        @SerializedName("pm10_mang_name")
        String pm10_mang_name;
        @SerializedName("pm25_mang_name")
        String pm25_mang_name;
        @SerializedName("pm10_station")
        String pm10_station;
        @SerializedName("pm25_station")
        String pm25_station;
        @SerializedName("no2_station")
        String no2_station;
        @SerializedName("o3_station")
        String o3_station;
        @SerializedName("co_station")
        String co_station;
        @SerializedName("so2_station")
        String so2_station;
    }
}
