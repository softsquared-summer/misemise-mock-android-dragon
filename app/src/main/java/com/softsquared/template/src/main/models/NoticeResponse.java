package com.softsquared.template.src.main.models;

import com.google.gson.annotations.SerializedName;

public class NoticeResponse {
    @SerializedName("isSuccess")
    boolean isSuccess;
    @SerializedName("code")
    int code;
    @SerializedName("message")
    public String message;
    @SerializedName("result")
    public NoticeResponse.noticeResult noticeResult;

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

    public NoticeResponse.noticeResult getNoticeResult() {
        return noticeResult;
    }

    public void setNoticeResult(NoticeResponse.noticeResult noticeResult) {
        this.noticeResult = noticeResult;
    }

    public class noticeResult {
        @SerializedName("no")
        int no;
        @SerializedName("title")
        String title;
        @SerializedName("version")
        String version;
        @SerializedName("content")
        String content;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
