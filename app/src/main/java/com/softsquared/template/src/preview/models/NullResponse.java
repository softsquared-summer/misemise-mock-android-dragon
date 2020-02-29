package com.softsquared.template.src.preview.models;

import com.google.gson.annotations.SerializedName;

public class NullResponse {
    @SerializedName("isSuccess")
    boolean isSuccess;
    @SerializedName("code")
    int code;
    @SerializedName("message")
    String message;
    @SerializedName("nullSchoolPm10")
    String nullSchoolPm10;
    @SerializedName("nullSchoolPm2_5")
    String nullSchoolPm2_5;


}
