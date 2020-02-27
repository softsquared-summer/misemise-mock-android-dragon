package com.softsquared.template.src.preview;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
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
}
