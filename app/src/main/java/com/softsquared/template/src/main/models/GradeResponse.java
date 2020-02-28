package com.softsquared.template.src.main.models;

import com.google.gson.annotations.SerializedName;

public class GradeResponse {
    @SerializedName("isSuccess")
    boolean isSuccess;
    @SerializedName("code")
    int code;
    @SerializedName("message")
    String message;
    @SerializedName("result")
    gradeResult gradeResult;

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

    public GradeResponse.gradeResult getGradeResult() {
        return gradeResult;
    }

    public void setGradeResult(GradeResponse.gradeResult gradeResult) {
        this.gradeResult = gradeResult;
    }

    public class gradeResult {
        @SerializedName("total_grade")
        int total_grade;
        @SerializedName("pm10_grade")
        int pm10_grade;
        @SerializedName("pm25_grade")
        int pm25_grade;
        @SerializedName("no2_grade")
        Float no2_grade;
        @SerializedName("o3_grade")
        Float o3_grade;
        @SerializedName("co_grade")
        Float co_grade;
        @SerializedName("so2_grade")
        Float so2_grade;
        @SerializedName("current_status_grade")
        int current_status_grade;

        public int getTotal_grade() {
            return total_grade;
        }

        public void setTotal_grade(int total_grade) {
            this.total_grade = total_grade;
        }

        public int getPm10_grade() {
            return pm10_grade;
        }

        public void setPm10_grade(int pm10_grade) {
            this.pm10_grade = pm10_grade;
        }

        public int getPm25_grade() {
            return pm25_grade;
        }

        public void setPm25_grade(int pm25_grade) {
            this.pm25_grade = pm25_grade;
        }

        public Float getNo2_grade() {
            return no2_grade;
        }

        public void setNo2_grade(Float no2_grade) {
            this.no2_grade = no2_grade;
        }

        public Float getO3_grade() {
            return o3_grade;
        }

        public void setO3_grade(Float o3_grade) {
            this.o3_grade = o3_grade;
        }

        public Float getCo_grade() {
            return co_grade;
        }

        public void setCo_grade(Float co2_grade) {
            this.co_grade = co2_grade;
        }

        public Float getSo2_grade() {
            return so2_grade;
        }

        public void setSo2_grade(Float so2_grade) {
            this.so2_grade = so2_grade;
        }

        public int getCurrent_status_grade() {
            return current_status_grade;
        }

        public void setCurrent_status_grade(int current_status_grade) {
            this.current_status_grade = current_status_grade;
        }
    }
}
