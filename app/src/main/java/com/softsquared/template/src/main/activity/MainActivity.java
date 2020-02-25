package com.softsquared.template.src.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.BookMarkData;
import com.softsquared.template.src.bookMark.activity.BookMarkActivity;
import com.softsquared.template.src.main.MainService;
import com.softsquared.template.src.main.adapter.MainViewPagerAdapter;
import com.softsquared.template.src.main.interfaces.MainActivityView;
import com.softsquared.template.src.main.models.EtcResponse;
import com.softsquared.template.src.main.models.RegionResponse;
import com.softsquared.template.src.main.sideBar.SideEightStage;
import com.softsquared.template.src.main.sideBar.SideInfoActivity;
import com.softsquared.template.src.main.sideBar.SideSetting;
import com.softsquared.template.src.main.sideBar.SideWho;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class MainActivity extends BaseActivity implements MainActivityView {

    private ViewPager mViewPager;
    private MainViewPagerAdapter mPagerAdapter;
    private ImageButton mIbtnOpenDrawer, mIbtnShare, mIbtnBookMark;
    private ImageButton mIbtnInfo, mIbtnEight, mIbtnSetting, mIbtnWho;
    private Retrofit mRetrofit;
    private RegionResponse.result mResult;
    ArrayList<BookMarkData> mAlBookMarkData, mAlNewData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actList.add(this);
        mAlBookMarkData = getBookMarkList();
//        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
//        startActivity(intent);
        if(mAlBookMarkData.size() < 1){
            BookMarkData cur_my_loc = new BookMarkData("현재 위치");
            mAlBookMarkData.add(cur_my_loc);
            saveBookMarkList(mAlBookMarkData);
        }
        Log.e("sharedPreCOunt",mAlBookMarkData.size()+"");


        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPager);
        mIbtnInfo = findViewById(R.id.btn_info);
        mIbtnEight = findViewById(R.id.ibtn_eight);
        mIbtnWho = findViewById(R.id.ibtn_who);
        mIbtnSetting = findViewById(R.id.ibtn_setting);
        mIbtnOpenDrawer = findViewById(R.id.ibtn_openDrawer);
        mIbtnShare = findViewById(R.id.ibtn_share);
        mIbtnBookMark = findViewById(R.id.ibtn_bookMark);

        mViewPager = findViewById(R.id.viewPager);
        mPagerAdapter = new MainViewPagerAdapter(this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(10);

        for(int i=0;i< mAlBookMarkData.size();i++){
            String curName = mAlBookMarkData.get(i).getLocation_name();
            Log.e("curName : ", curName);
            int pos = mPagerAdapter.addItem(new BookMarkData(curName));
            getRegion(curName, pos);
            getEtc(curName, pos);
        }
        mPagerAdapter.notifyDataSetChanged();

        mIbtnBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookMarkActivity.class);
                startActivity(intent);
            }
        });
        mIbtnOpenDrawer.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.dlo_drawer);
                if (!drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.openDrawer(Gravity.LEFT);
                }
            }
        });
        mIbtnWho.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SideWho.class);
                startActivity(intent);
            }
        });
        mIbtnSetting.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SideSetting.class);
                startActivity(intent);
            }
        });
        mIbtnEight.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SideEightStage.class);
                startActivity(intent);
            }
        });
        mIbtnInfo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SideInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mAlBookMarkData.size() != getBookMarkList().size()){
            actFinish();
            onRestart();
        }
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }
    @Override
    public void getRegionCode(int code) {
        if(code == 100){
            Log.e("RegionResponse : ", code + "");


        }else{
            Log.e("RegionResponse : ", code + "");
        }
    }

    @Override
    public void getRegionResult(RegionResponse.result result, String name, int pos) {
        BookMarkData bookMarkData = mPagerAdapter.getItem(pos);

//        result.setCo_value((float)10);
//        result.setNo2_value((float)10);
//        result.setO3_value((float)10);
//        result.setPm10_value(10);
//        result.setPm25_value(10);
//        result.setSo2_value((float)10);
//        result.setTotal_value(10);
//
//        bookMarkData.setRegionResponse_result(result);

        //mPagerAdapter.getBookMarkList().get(pos).setRegionResponse_result(result);
        //mPagerAdapter.notifyDataSetChanged();
        mPagerAdapter.setRegionStatus(pos,result);
    }

    @Override
    public void getEtcCode(int code) {
        if(code == 100){
            Log.e("EtcResponse : ", code + "");

        }else{
            Log.e("EtcResponse : ", code + "");
        }
    }

    @Override
    public void getEtcResult(EtcResponse.etcResult etcResult, String name, int pos) {
        BookMarkData bookMarkData = mPagerAdapter.getItem(pos);
        mPagerAdapter.setEtcStatus(pos,etcResult);
    }

    public void getRegion(String name, int pos) {
        final MainService mainService = new MainService(this);
        mainService.getRegion(name, pos);
    }
    public void getEtc(String name, int pos) {
        final MainService mainService = new MainService(this);
        mainService.getEtc(name, pos);
    }


}
