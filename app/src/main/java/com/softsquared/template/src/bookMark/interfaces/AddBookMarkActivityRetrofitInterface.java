package com.softsquared.template.src.bookMark.interfaces;

import com.softsquared.template.src.bookMark.models.BookMarkResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AddBookMarkActivityRetrofitInterface {
    @GET("/locationSearch")
    Call<BookMarkResponse> getLocationName(@Query("location") String location);
}
