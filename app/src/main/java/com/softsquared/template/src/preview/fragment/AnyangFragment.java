package com.softsquared.template.src.preview.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.softsquared.template.R;

public class AnyangFragment extends Fragment {

    Button mBtnHanbando, mBtnEastAsia;
    boolean modeFlag = false;
    View layout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.layout_anyang_fragment, container, false);
        mBtnHanbando = layout.findViewById(R.id.btn_hanbando);
        mBtnEastAsia = layout.findViewById(R.id.btn_east_asia);
        if(modeFlag == false){
            mBtnHanbando.setTextColor(Color.parseColor("#000000"));
            mBtnEastAsia.setTextColor(Color.parseColor("#d3d3d3"));
            Glide.with(AnyangFragment.this).load("http://www.webairwatch.com/kaq/modelimg_case4/PM10.09KM.Animation.gif").into((ImageView)layout.findViewById(R.id.iv_anyang_up));
            Glide.with(AnyangFragment.this).load("http://www.webairwatch.com/kaq/modelimg_case4/PM2_5.09KM.Animation.gif").into((ImageView)layout.findViewById(R.id.iv_anyang_down));
        }else{
            mBtnEastAsia.setTextColor(Color.parseColor("#000000"));
            mBtnHanbando.setTextColor(Color.parseColor("#d3d3d3"));
            Glide.with(AnyangFragment.this).load("http://www.webairwatch.com/kaq/modelimg_case4/PM10.27KM.Animation.gif").dontAnimate().into((ImageView)layout.findViewById(R.id.iv_anyang_up));
            Glide.with(AnyangFragment.this).load("http://www.webairwatch.com/kaq/modelimg_case4/PM2_5.27KM.Animation.gif").dontAnimate().into((ImageView)layout.findViewById(R.id.iv_anyang_down));
        }

        mBtnHanbando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeFlag = false;
                mBtnHanbando.setTextColor(Color.parseColor("#000000"));
                mBtnEastAsia.setTextColor(Color.parseColor("#d3d3d3"));
                Glide.with(AnyangFragment.this).load("http://www.webairwatch.com/kaq/modelimg_case4/PM10.09KM.Animation.gif").into((ImageView)layout.findViewById(R.id.iv_anyang_up));
                Glide.with(AnyangFragment.this).load("http://www.webairwatch.com/kaq/modelimg_case4/PM2_5.09KM.Animation.gif").into((ImageView)layout.findViewById(R.id.iv_anyang_down));
            }
        });
        mBtnEastAsia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeFlag = true;
                mBtnEastAsia.setTextColor(Color.parseColor("#000000"));
                mBtnHanbando.setTextColor(Color.parseColor("#d3d3d3"));
                Glide.with(AnyangFragment.this).load("http://www.webairwatch.com/kaq/modelimg_case4/PM10.27KM.Animation.gif").into((ImageView)layout.findViewById(R.id.iv_anyang_up));
                Glide.with(AnyangFragment.this).load("http://www.webairwatch.com/kaq/modelimg_case4/PM2_5.27KM.Animation.gif").into((ImageView)layout.findViewById(R.id.iv_anyang_down));
            }
        });
        return layout;
    }
}
