package com.softsquared.template.src.preview.fragment;

public interface AsyncTaskCallback {
    void onSuccess(String result);
    void onFailure(String e);
}