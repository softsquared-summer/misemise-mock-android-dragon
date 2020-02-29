package com.softsquared.template.src.preview.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.softsquared.template.R;

public class NullSchoolFragment extends Fragment {
    ImageButton mIbtn_nullCancel, mIbtn_null_prev, mIbtn_null_next;
    TextView mTv_nullTime;
    private WebView mWebVIew_mise, mWebView_super;
    String curTime = "15";
    int ct = 15;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.layout_null_school_fragment, container, false);
        mIbtn_null_next = layout.findViewById(R.id.ibtn_null_next);
        mIbtn_null_prev = layout.findViewById(R.id.ibtn_null_prev);
        mIbtn_nullCancel = layout.findViewById(R.id.ibtn_null_cancel);
        mTv_nullTime = layout.findViewById(R.id.tv_null_time);
        mWebVIew_mise = layout.findViewById(R.id.wv_null_mise);
        mWebView_super = layout.findViewById(R.id.wv_null_super_mise);
        mTv_nullTime.setText("2월 29일\n오후 3:00");
        mIbtn_nullCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        mIbtn_null_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upCount();
                mWebVIew_mise.loadUrl("https://earth.nullschool.net/ko/#2020/02/29/" + ct + "00Z/particulates/surface/level/overlay=pm10/orthographic=-233.60,36.14,2447/loc=127.424,36.939");
                mWebView_super.loadUrl("https://earth.nullschool.net/ko/#2020/02/29/" + ct + "00Z/particulates/surface/level/overlay=pm2.5/orthographic=-233.60,36.14,2447/loc=127.424,36.939");
                ct--;
                if(ct > 12){
                    int dct = ct % 12;
                    mTv_nullTime.setText("2월 29일\n오후 "+ dct +":00");
                }else{
                    mTv_nullTime.setText("2월 29일\n오전 "+ ct +":00");
                }
            }
        });
        mIbtn_null_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downCount();
                mWebVIew_mise.loadUrl("https://earth.nullschool.net/ko/#2020/02/29/" + ct + "00Z/particulates/surface/level/overlay=pm10/orthographic=-233.60,36.14,2447/loc=127.424,36.939");
                mWebView_super.loadUrl("https://earth.nullschool.net/ko/#2020/02/29/" + ct + "00Z/particulates/surface/level/overlay=pm2.5/orthographic=-233.60,36.14,2447/loc=127.424,36.939");
                ct++;
                if(ct > 12){
                    int dct = ct % 12;
                    mTv_nullTime.setText("2월 29일\n오후 "+ dct +":00");
                }else{
                    mTv_nullTime.setText("2월 29일\n오전 "+ ct +":00");
                }
            }
        });

        mWebVIew_mise.getSettings().setJavaScriptEnabled(true);
        mWebView_super.getSettings().setJavaScriptEnabled(true);
        mWebVIew_mise.loadUrl("https://earth.nullschool.net/ko/#2020/02/29/" + curTime + "00Z/particulates/surface/level/overlay=pm10/orthographic=-233.60,36.14,2447/loc=127.424,36.939");
        mWebView_super.loadUrl("https://earth.nullschool.net/ko/#2020/02/29/" + curTime + "00Z/particulates/surface/level/overlay=pm2.5/orthographic=-233.60,36.14,2447/loc=127.424,36.939");

        return layout;
    }

    void upCount(){
        int cur = Integer.parseInt(curTime);
        cur = (cur + 1) % 24;
    }
    void downCount(){
        int cur = Integer.parseInt(curTime);
        cur = (cur + 23) % 24;
    }
}
