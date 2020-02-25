package com.softsquared.template.src.main;

import android.util.Log;

import com.softsquared.template.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.template.src.main.models.EtcResponse;
import com.softsquared.template.src.main.models.RegionResponse;
import com.softsquared.template.src.main.interfaces.MainActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.getRetrofit;

public class MainService {
    private final MainActivityView mMainActivityView;


    public MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    public void getRegion(final String region, final int idx) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getRegion(region).enqueue(new Callback<RegionResponse>() {
            @Override
            public void onResponse(Call<RegionResponse> call, Response<RegionResponse> response) {
                final RegionResponse regionResponse = response.body();
                if (regionResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                Log.e("onResponse", "응답");
                mMainActivityView.validateSuccess(regionResponse.getMessage());
                mMainActivityView.getRegionCode(regionResponse.getCode());
                mMainActivityView.getRegionResult(regionResponse.getResult(), region, idx);
            }

            @Override
            public void onFailure(Call<RegionResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
                Log.e("onFailure", t.getLocalizedMessage());
            }
        });
    }

    public void getEtc(final String region, final int idx) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getEtc(region).enqueue(new Callback<EtcResponse>() {
            @Override
            public void onResponse(Call<EtcResponse> call, Response<EtcResponse> response) {
                final EtcResponse etcResponse = response.body();
                if (etcResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }
                Log.e("ectOnResponse", "응답");
                mMainActivityView.getEtcCode(etcResponse.getCode());
                mMainActivityView.getEtcResult(etcResponse.getEtcResult(), region, idx);
            }

            @Override
            public void onFailure(Call<EtcResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
                Log.e("onFailure", t.getLocalizedMessage());
            }
        });
    }
}
