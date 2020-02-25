package com.softsquared.template.src.bookMark.service;

import android.util.Log;

import com.softsquared.template.src.bookMark.interfaces.AddBookMarkActivityRetrofitInterface;
import com.softsquared.template.src.bookMark.interfaces.AddBookMarkActivityView;
import com.softsquared.template.src.bookMark.models.BookMarkResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.getRetrofit;

public class AddBookMarkActivityService {
    public final AddBookMarkActivityView mAddBookMarkActivityView;

    public AddBookMarkActivityService(final AddBookMarkActivityView addBookMarkActivityView) {
        this.mAddBookMarkActivityView = addBookMarkActivityView;
    }

    public void getLocationName(String location) {
        final AddBookMarkActivityRetrofitInterface addBookMarkActivityRetrofitInterface = getRetrofit().create(AddBookMarkActivityRetrofitInterface.class);
        addBookMarkActivityRetrofitInterface.getLocationName(location).enqueue(new Callback<BookMarkResponse>() {
            @Override
            public void onResponse(Call<BookMarkResponse> call, Response<BookMarkResponse> response) {
                final BookMarkResponse bookMarkResponse = response.body();

                if (bookMarkResponse == null) {
                    Log.e("fail" , "message : " );
                    mAddBookMarkActivityView.validateFailure(null);
                    return;
                }
                mAddBookMarkActivityView.getResult(bookMarkResponse.getResult());
                mAddBookMarkActivityView.validateSuccess(bookMarkResponse.getMessage());
                mAddBookMarkActivityView.getCode(bookMarkResponse.getCode());
            }

            @Override
            public void onFailure(Call<BookMarkResponse> call, Throwable t) {
                mAddBookMarkActivityView.validateFailure(null);
                Log.e("fail" , "onFailure" + t.getLocalizedMessage());
            }
        });
    }
}
