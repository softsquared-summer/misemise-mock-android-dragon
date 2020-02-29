package com.softsquared.template.src.preview.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JapanResponse {
    @SerializedName("isSuccess")
    boolean isSuccess;
    @SerializedName("code")
    int code;
    @SerializedName("message")
    String message;
    @SerializedName("result")
    ArrayList<String> result;

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

    public ArrayList<String> getResult() {
        return result;
    }

    public void setResult(ArrayList<String> result) {
        this.result = result;
    }

//    public class JapanResult{
//        @SerializedName("0")
//        String str0;
//        @SerializedName("1")
//        String str1;
//        @SerializedName("2")
//        String str2;
//        @SerializedName("3")
//        String str3;
//        @SerializedName("4")
//        String str4;
//        @SerializedName("5")
//        String str5;
//        @SerializedName("6")
//        String str6;
//        @SerializedName("7")
//        String str7;
//        @SerializedName("8")
//        String str8;
//        @SerializedName("9")
//        String str9;
//        @SerializedName("10")
//        String str10;
//        @SerializedName("11")
//        String str11;
//        @SerializedName("12")
//        String str12;
//        @SerializedName("13")
//        String str13;
//        @SerializedName("14")
//        String str14;
//        @SerializedName("15")
//        String str15;
//
//        public String getStr0() {
//            return str0;
//        }
//
//        public void setStr0(String str0) {
//            this.str0 = str0;
//        }
//
//        public String getStr1() {
//            return str1;
//        }
//
//        public void setStr1(String str1) {
//            this.str1 = str1;
//        }
//
//        public String getStr2() {
//            return str2;
//        }
//
//        public void setStr2(String str2) {
//            this.str2 = str2;
//        }
//
//        public String getStr3() {
//            return str3;
//        }
//
//        public void setStr3(String str3) {
//            this.str3 = str3;
//        }
//
//        public String getStr4() {
//            return str4;
//        }
//
//        public void setStr4(String str4) {
//            this.str4 = str4;
//        }
//
//        public String getStr5() {
//            return str5;
//        }
//
//        public void setStr5(String str5) {
//            this.str5 = str5;
//        }
//
//        public String getStr6() {
//            return str6;
//        }
//
//        public void setStr6(String str6) {
//            this.str6 = str6;
//        }
//
//        public String getStr7() {
//            return str7;
//        }
//
//        public void setStr7(String str7) {
//            this.str7 = str7;
//        }
//
//        public String getStr8() {
//            return str8;
//        }
//
//        public void setStr8(String str8) {
//            this.str8 = str8;
//        }
//
//        public String getStr9() {
//            return str9;
//        }
//
//        public void setStr9(String str9) {
//            this.str9 = str9;
//        }
//
//        public String getStr10() {
//            return str10;
//        }
//
//        public void setStr10(String str10) {
//            this.str10 = str10;
//        }
//
//        public String getStr11() {
//            return str11;
//        }
//
//        public void setStr11(String str11) {
//            this.str11 = str11;
//        }
//
//        public String getStr12() {
//            return str12;
//        }
//
//        public void setStr12(String str12) {
//            this.str12 = str12;
//        }
//
//        public String getStr13() {
//            return str13;
//        }
//
//        public void setStr13(String str13) {
//            this.str13 = str13;
//        }
//
//        public String getStr14() {
//            return str14;
//        }
//
//        public void setStr14(String str14) {
//            this.str14 = str14;
//        }
//
//        public String getStr15() {
//            return str15;
//        }
//
//        public void setStr15(String str15) {
//            this.str15 = str15;
//        }
//    }
}
