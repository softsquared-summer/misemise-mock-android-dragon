package com.softsquared.template.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HourForecastResponse {
    @SerializedName("isSuccess")
    boolean isSuccess;
    @SerializedName("code")
    int code;
    @SerializedName("message")
    String message;


    @SerializedName("result")
    public ArrayList<ForecastResult> result;

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

    public ArrayList<ForecastResult> getResult() {
        return result;
    }

    public void setResult(ArrayList<ForecastResult> result) {
        this.result = result;
    }

    public class ForecastResult{
        @SerializedName("no")
        int no;
        @SerializedName("hour")
        String hour;
        @SerializedName("current_grade")
        String current_grade;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getCurrent_grade() {
            return current_grade;
        }

        public void setCurrent_grade(String current_grade) {
            this.current_grade = current_grade;
        }
    }
}
