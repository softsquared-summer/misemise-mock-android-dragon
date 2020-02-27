package com.softsquared.template.src.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.BookMarkData;
import com.softsquared.template.src.bookMark.activity.BookMarkActivity;
import com.softsquared.template.src.main.adapter.MainViewPagerAdapter;
import com.softsquared.template.src.main.interfaces.MainActivityView;
import com.softsquared.template.src.main.models.DayForecastResponse;
import com.softsquared.template.src.main.models.EtcResponse;
import com.softsquared.template.src.main.models.GradeResponse;
import com.softsquared.template.src.main.models.HourForecastResponse;
import com.softsquared.template.src.main.models.NoticeResponse;
import com.softsquared.template.src.main.models.RegionResponse;
import com.softsquared.template.src.preview.PreviewImageActivity;
import com.softsquared.template.src.sidebar.activity.SideEightStage;
import com.softsquared.template.src.sidebar.activity.SideInfoActivity;
import com.softsquared.template.src.sidebar.activity.SideSetting;
import com.softsquared.template.src.sidebar.activity.SideWho;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class MainActivity extends BaseActivity implements MainActivityView {
    private ViewPager mViewPager;
    private MainViewPagerAdapter mPagerAdapter;
    private ImageButton mIbtnOpenDrawer, mIbtnShare, mIbtnBookMark, mIbtn_preview_image, mIbtn_set_alarm;
    private ImageButton mIbtnInfo, mIbtnEight, mIbtnSetting, mIbtnWho;
    private ImageView mIvMainAd;
    private Retrofit mRetrofit;
    private RegionResponse.result mResult;
    ArrayList<BookMarkData> mAlBookMarkData, mAlNewData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actList.add(this);

        mAlBookMarkData = getBookMarkList();
//        //should delete
        if(mAlBookMarkData.size()<1){
            mAlBookMarkData.add(new BookMarkData("더미"));
            saveBookMarkList(mAlBookMarkData);
        }
        if(mAlBookMarkData.get(0).noticeDialogFlag){
            showNotice();
        }
//        // make splash - 조건에 맞게 스플래쉬 해주는거 추가해야함
//        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
//        startActivity(intent);
        if (mAlBookMarkData.size() < 1) {
            BookMarkData cur_my_loc = new BookMarkData("현재 위치");
            mAlBookMarkData.add(cur_my_loc);
            saveBookMarkList(mAlBookMarkData);
        }
        Log.e("sharedPreCOunt", mAlBookMarkData.size() + "");


        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPager);
        mIbtnInfo = findViewById(R.id.btn_info);
        mIbtnEight = findViewById(R.id.ibtn_eight);
        mIbtnWho = findViewById(R.id.ibtn_who);
        mIbtnSetting = findViewById(R.id.ibtn_setting);
        mIbtnOpenDrawer = findViewById(R.id.ibtn_openDrawer);
        mIbtnShare = findViewById(R.id.ibtn_share);
        mIbtnBookMark = findViewById(R.id.ibtn_bookMark);
        mIbtn_preview_image = findViewById(R.id.ibtn_preview_image);
        mIbtn_set_alarm = findViewById(R.id.ibtn_set_alarm);
        mIvMainAd = findViewById(R.id.iv_main_ad);




        mPagerAdapter = new MainViewPagerAdapter(this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(10);

        for (int i = 0; i < mAlBookMarkData.size(); i++) {
            String curName = mAlBookMarkData.get(i).getLocation_name();
            Log.e("curName : ", curName);
            int pos = mPagerAdapter.addItem(new BookMarkData(curName));
            getRegion(curName, pos);
            getEtc(curName, pos);
            getGrade(curName, pos);
            getNotice();
            getHourForecast(pos);
            getDayForecast(pos);
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
        mIbtn_preview_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PreviewImageActivity.class);
                startActivity(intent);
            }
        });
        mIbtn_set_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmDialog alarmDialog = new AlarmDialog(MainActivity.this);
                alarmDialog.callFunction();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAlBookMarkData.size() != getBookMarkList().size()) {
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
        if (code == 100) {
            Log.e("RegionResponse : ", code + "");


        } else {
            Log.e("RegionResponse : ", code + "");
        }
    }

    @Override
    public void getRegionResult(RegionResponse.result result, String name, int pos) {
        BookMarkData bookMarkData = mPagerAdapter.getItem(pos);
        mPagerAdapter.setRegionStatus(pos, result);
    }

    @Override
    public void getEtcCode(int code) {
        if (code == 100) {
            Log.e("EtcResponse : ", code + "");

        } else {
            Log.e("EtcResponse : ", code + "");
        }
    }

    @Override
    public void getEtcResult(EtcResponse.etcResult etcResult, String name, int pos) {
        BookMarkData bookMarkData = mPagerAdapter.getItem(pos);
        mPagerAdapter.setEtcStatus(pos, etcResult);
    }

    @Override
    public void getGradeResult(GradeResponse.gradeResult gradeResult, String name, int pos) {
        BookMarkData bookMarkData = mPagerAdapter.getItem(pos);
        mPagerAdapter.setGradeResult(pos, gradeResult);
    }

    @Override
    public void getNoticeResult(NoticeResponse.noticeResult result) {
        Log.e("notice_result", result.getContent());
    }

    @Override
    public void getHourForecastResult(ArrayList<HourForecastResponse.ForecastResult> result, int pos) {
        Log.e("hourForecast데이터", result.get(0).getHour()+"");
        mPagerAdapter.setHourForecast(result, pos);
    }

    @Override
    public void getDayForecastResult(ArrayList<DayForecastResponse.ForecastResult> result, int pos) {
        Log.e("dayForecast데이터", result.get(0).getDay()+"");
        mPagerAdapter.setDayForecast(result, pos);
    }

    public void getNotice() {
        final MainService mainService = new MainService(this);
        mainService.getNotice();
    }
    public void getGrade(String name, int pos) {
        final MainService mainService = new MainService(this);
        mainService.getGrade(name, pos);
    }
    public void getRegion(String name, int pos) {
        final MainService mainService = new MainService(this);
        mainService.getRegion(name, pos);
    }

    public void getEtc(String name, int pos) {
        final MainService mainService = new MainService(this);
        mainService.getEtc(name, pos);
    }

    public void getHourForecast(int pos){
        final MainService mainService = new MainService(this);
        mainService.getHourForecast(pos);
    }
    public void getDayForecast(int pos){
        final MainService mainService = new MainService(this);
        mainService.getDayForecast(pos);
    }
    public void showNotice(){
        NoticeDialog noticeDialog = new NoticeDialog(MainActivity.this);
        noticeDialog.callFunction();
    }
}
