package com.softsquared.template.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class EtcResponse {
    @SerializedName("isSuccess")
    public boolean isSuccess;
    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;

    @SerializedName("etcResult")
    public etcResult etcResult = new etcResult();

    public class etcResult{
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
