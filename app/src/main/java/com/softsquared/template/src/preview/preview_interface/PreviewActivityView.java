package com.softsquared.template.src.preview.preview_interface;

import com.google.android.gms.maps.GoogleMap;
import com.softsquared.template.src.preview.models.JapanResponse;
import com.softsquared.template.src.preview.models.MapResponse;

import java.util.ArrayList;

public interface PreviewActivityView {

    void validateFailure(String message);

    void getMapResult(ArrayList<MapResponse.MapResult> result, GoogleMap googleMap);

    void getJapanResult(ArrayList<String> result);

}
