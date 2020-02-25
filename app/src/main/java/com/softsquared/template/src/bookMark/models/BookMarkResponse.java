package com.softsquared.template.src.bookMark.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookMarkResponse {
    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

    @SerializedName("result")
    ArrayList<Result> result;

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

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }

    public class Result {

        @SerializedName("no")
        int no;

        @SerializedName("region_1depth_name")
        String region_1depth_name;

        @SerializedName("region_2depth_name")
        String region_2depth_name;

        @SerializedName("region_3depth_name")
        String region_3depth_name;

        @SerializedName("address_name")
        String address_name;

        @SerializedName("tm_x")
        Float tm_x;

        @SerializedName("tm_y")
        Float tm_y;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getRegion_1depth_name() {
            return region_1depth_name;
        }

        public void setRegion_1depth_name(String region_1depth_name) {
            this.region_1depth_name = region_1depth_name;
        }

        public String getRegion_2depth_name() {
            return region_2depth_name;
        }

        public void setRegion_2depth_name(String region_2depth_name) {
            this.region_2depth_name = region_2depth_name;
        }

        public String getRegion_3depth_name() {
            return region_3depth_name;
        }

        public void setRegion_3depth_name(String region_3depth_name) {
            this.region_3depth_name = region_3depth_name;
        }

        public String getAddress_name() {
            return address_name;
        }

        public void setAddress_name(String address_name) {
            this.address_name = address_name;
        }

        public Float getTm_x() {
            return tm_x;
        }

        public void setTm_x(Float tm_x) {
            this.tm_x = tm_x;
        }

        public Float getTm_y() {
            return tm_y;
        }

        public void setTm_y(Float tm_y) {
            this.tm_y = tm_y;
        }
    }
}
