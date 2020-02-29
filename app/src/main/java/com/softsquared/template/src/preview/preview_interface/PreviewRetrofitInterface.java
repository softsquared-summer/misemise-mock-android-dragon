package com.softsquared.template.src.preview.preview_interface;

import com.softsquared.template.src.preview.models.JapanResponse;
import com.softsquared.template.src.preview.models.MapResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PreviewRetrofitInterface {
    @GET("/map")
    Call<MapResponse> getMap();

    @GET("/japanMeteorologicalAgency")
    Call<JapanResponse> getJapan();
}
