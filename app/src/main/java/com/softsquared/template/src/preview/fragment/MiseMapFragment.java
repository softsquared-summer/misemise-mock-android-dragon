package com.softsquared.template.src.preview.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.softsquared.template.R;
import com.softsquared.template.src.preview.PreviewService;
import com.softsquared.template.src.preview.models.MapResponse;
import com.softsquared.template.src.preview.preview_interface.PreviewActivityView;

import java.util.ArrayList;

public class MiseMapFragment extends Fragment implements OnMapReadyCallback, PreviewActivityView {

    private MapView mapView = null;
    ArrayList<MapResponse.MapResult> mAlMapList;
    FusedLocationProviderClient mFusedLocationClient;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.layout_mise_map_fragment, container, false);
        mapView = (MapView)layout.findViewById(R.id.mise_map);
        mapView.getMapAsync((OnMapReadyCallback) this);

        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //액티비티가 처음 생성될 때 실행되는 함수

        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng SEOUL = new LatLng(37.56, 126.97);

        // 여기서 일단 한번 호출해서 리스트에 측정소 좌표 전체를 받고 다시 488번 전부 호출해서 마커 찍어주기


//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(SEOUL);
//        markerOptions.title("서울");
//        markerOptions.snippet("수도");
//        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(6));

        getMap(googleMap);
    }

    @Override
    public void validateFailure(String message) {
        Log.e("validateFailure", "map");
    }

    @Override
    public void getMapResult(ArrayList<MapResponse.MapResult> result, GoogleMap googleMap) {
        mAlMapList = result;

        Log.e("지도", mAlMapList.size() + "");
        for(int i=0;i<mAlMapList.size();i++){
            double cury = mAlMapList.get(i).getY();
            double curx = mAlMapList.get(i).getX();
            String curGrade = mAlMapList.get(i).getCurrent_status_grade();
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(cury, curx));
            BitmapDrawable bitmapdraw;
            if(curGrade != null) {
                if (curGrade.equals("좋음")) {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.good);
                } else if (curGrade.equals("양호")) {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.not_bad);
                } else if (curGrade.equals("보통")) {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.soso);
                } else if (curGrade.equals("나쁨")) {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bad);
                } else {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.soso);
                }

                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 30, 30, false);
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            }
            // 이제여기서 api 또 호출해서 받으면 마커 설정해서 거기 설정한대서 마커 칠해주는걸로 바꿔야함.
            googleMap.addMarker(markerOptions);
        }
    }

    public void getMap(GoogleMap googleMap){
        final PreviewService previewService = new PreviewService(this);
        previewService.getMap(googleMap);
    }
}
