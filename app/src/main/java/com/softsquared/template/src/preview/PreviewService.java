package com.softsquared.template.src.preview;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.softsquared.template.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.template.src.preview.models.JapanResponse;
import com.softsquared.template.src.preview.models.MapResponse;
import com.softsquared.template.src.preview.preview_interface.PreviewActivityView;
import com.softsquared.template.src.preview.preview_interface.PreviewRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.getRetrofit;

public class PreviewService {
    private final PreviewActivityView mPreviewActivityView;

    public PreviewService(final PreviewActivityView previewActivityView){
        this.mPreviewActivityView = previewActivityView;
    }

    public void getMap(final GoogleMap googleMap){
        final PreviewRetrofitInterface previewRetrofitInterface = getRetrofit().create(PreviewRetrofitInterface.class);
        previewRetrofitInterface.getMap().enqueue(new Callback<MapResponse>(){

            @Override
            public void onResponse(Call<MapResponse> call, Response<MapResponse> response) {
                final MapResponse mapResponse = response.body();
                if(mapResponse == null){
                    mPreviewActivityView.validateFailure(null);
                    return;
                }
                Log.e("mapOnResponse", "응답");
                if(mapResponse.getCode() == 100)
                    mPreviewActivityView.getMapResult(mapResponse.getResult(), googleMap);
            }

            @Override
            public void onFailure(Call<MapResponse> call, Throwable t) {

            }
        });
    }
    public void getJapan() {
        final PreviewRetrofitInterface previewRetrofitInterface = getRetrofit().create(PreviewRetrofitInterface.class);
        previewRetrofitInterface.getJapan().enqueue(new Callback<JapanResponse>() {
            @Override
            public void onResponse(Call<JapanResponse> call, Response<JapanResponse> response) {
                final JapanResponse japanResponse = response.body();
                if (japanResponse == null) {
                    Log.e("JapanResponse", "JapanResponse : null값");
                    mPreviewActivityView.validateFailure(null);
                    return;
                }
                Log.e("Japan", "code : " + japanResponse.getCode());
                if (japanResponse.getCode() == 100) {
                    mPreviewActivityView.getJapanResult(japanResponse.getResult());
                }
            }

            @Override
            public void onFailure(Call<JapanResponse> call, Throwable t) {
                Log.e("japanOnFailure", t.getLocalizedMessage());
            }
        });
    }
}
