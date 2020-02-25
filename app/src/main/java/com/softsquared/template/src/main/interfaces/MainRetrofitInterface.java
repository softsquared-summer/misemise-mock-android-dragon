package com.softsquared.template.src.main.interfaces;

import com.softsquared.template.src.main.models.EtcResponse;
import com.softsquared.template.src.main.models.RegionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainRetrofitInterface {
    @GET("/dust/value")
    Call<RegionResponse> getRegion(@Query("region") String region);

    @GET("/dust/etc")
    Call<EtcResponse> getEtc(@Query("region") String region);
}
