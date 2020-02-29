package com.softsquared.template.src.preview.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.softsquared.template.R;
import com.softsquared.template.src.preview.PreviewService;
import com.softsquared.template.src.preview.models.JapanResponse;
import com.softsquared.template.src.preview.models.MapResponse;
import com.softsquared.template.src.preview.preview_interface.PreviewActivityView;

import java.util.ArrayList;

public class MiseMapFragment extends Fragment implements OnMapReadyCallback, PreviewActivityView {
    Double lat = 0.0, lon = 0.0;
    String pickedStation = null;
    String miseStatus = null;
    private MapView mapView = null;
    ImageButton mIbtnMiseCancel;
    MarkerOptions mMarkerOptions;
    ArrayList<BitmapDrawable> mBitmapdraws_small, mBitmapdraws_smile;
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
        mMarkerOptions = new MarkerOptions();
        mBitmapdraws_small = new ArrayList<BitmapDrawable>();
        mBitmapdraws_smile = new ArrayList<BitmapDrawable>();

        mBitmapdraws_small.add((BitmapDrawable) getResources().getDrawable(R.drawable.good));
        mBitmapdraws_small.add((BitmapDrawable) getResources().getDrawable(R.drawable.not_bad));
        mBitmapdraws_small.add((BitmapDrawable) getResources().getDrawable(R.drawable.soso));
        mBitmapdraws_small.add((BitmapDrawable) getResources().getDrawable(R.drawable.bad));
        mBitmapdraws_smile.add((BitmapDrawable) getResources().getDrawable(R.drawable.good));
        mIbtnMiseCancel = layout.findViewById(R.id.ibtn_mise_back);
        mapView.getMapAsync((OnMapReadyCallback) this);
        mIbtnMiseCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        lat = 0.0;
        lon = 0.0;
        pickedStation=null;
        miseStatus = null;
        if(getActivity().getIntent().hasExtra("bundle")){
            Bundle bundle = getActivity().getIntent().getBundleExtra("bundle");
            if(bundle != null){
                lat = bundle.getDouble("latitude");
                lon = bundle.getDouble("longitude");
                pickedStation= bundle.getString("station");
                miseStatus = bundle.getString("status");
            }
        }





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


        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        LatLng SEOUL = new LatLng(37.56, 126.97);


        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(6));

        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                CameraPosition cameraPosition = googleMap.getCameraPosition();
                if(cameraPosition.zoom > 18.0) {

                } else {

                }
            }
        });

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
            String titleName = mAlMapList.get(i).getStation_name();
            BitmapDrawable bitmapdraw;
            mMarkerOptions.position(new LatLng(cury, curx));
            mMarkerOptions.title(titleName);
            if(curGrade != null) {
                if (curGrade.equals("좋음")) {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.good);
                    mMarkerOptions.snippet("좋음");
                } else if (curGrade.equals("양호")) {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.not_bad);
                    mMarkerOptions.snippet("양호");
                } else if (curGrade.equals("보통")) {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.soso);
                    mMarkerOptions.snippet("보통");
                } else if (curGrade.equals("나쁨")) {
                    mMarkerOptions.snippet("나쁨");
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bad);
                } else {
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.soso);
                    mMarkerOptions.snippet("점검중");
                }

                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 40, 40, false);
                mMarkerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            }
            googleMap.addMarker(mMarkerOptions);
        }
        if(pickedStation != null){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(lat, lon));
            markerOptions.title(pickedStation);
            markerOptions.snippet("상태 : " + miseStatus);
            googleMap.addMarker(markerOptions);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lon)));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        }
    }

    @Override
    public void getJapanResult(ArrayList<String> result) {

    }

    public void getMap(GoogleMap googleMap){
        final PreviewService previewService = new PreviewService(this);
        previewService.getMap(googleMap);
    }
}
