package com.softsquared.template.src.preview.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MapResponse {
    @SerializedName("isSuccess")
    boolean isSuccess;
    @SerializedName("code")
    int code;
    @SerializedName("message")
    String message;

    @SerializedName("result")
    ArrayList<MapResult> result;

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

    public ArrayList<MapResult> getResult() {
        return result;
    }

    public void setResult(ArrayList<MapResult> result) {
        this.result = result;
    }

    public class MapResult{
        @SerializedName("no")
        int no;
        @SerializedName("station_name")
        String station_name;
        @SerializedName("x")
        Double x;
        @SerializedName("y")
        Double y;
        @SerializedName("pm10_value")
        String pm10_value;
        @SerializedName("pm25_value")
        String pm25_value;
        @SerializedName("current_grade")
        String current_status_grade;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getStation_name() {
            return station_name;
        }

        public void setStation_name(String station_name) {
            this.station_name = station_name;
        }

        public Double getX() {
            return x;
        }

        public void setX(Double x) {
            this.x = x;
        }

        public Double getY() {
            return y;
        }

        public void setY(Double y) {
            this.y = y;
        }

        public String getPm10_value() {
            return pm10_value;
        }

        public void setPm10_value(String pm10_value) {
            this.pm10_value = pm10_value;
        }

        public String getPm25_value() {
            return pm25_value;
        }

        public void setPm25_value(String pm25_value) {
            this.pm25_value = pm25_value;
        }

        public String getCurrent_status_grade() {
            return current_status_grade;
        }

        public void setCurrent_status_grade(String current_status_grade) {
            this.current_status_grade = current_status_grade;
        }
    }
}
