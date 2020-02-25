package com.softsquared.template.src.bookMark.interfaces;

import com.softsquared.template.src.bookMark.models.BookMarkResponse;

import java.util.ArrayList;

public interface AddBookMarkActivityView {
    void validateSuccess(String message);

    void validateFailure(String text);

    void getCode(int code);

    void getResult(ArrayList<BookMarkResponse.Result> result);
}
