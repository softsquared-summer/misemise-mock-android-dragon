package com.softsquared.template.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.maps.model.LatLng;
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
import com.softsquared.template.src.splash.SplashActivity;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Retrofit;

public class MainActivity extends BaseActivity implements MainActivityView {
    private ViewPager mViewPager;
    private MainViewPagerAdapter mPagerAdapter;
    private ImageButton mIbtnOpenDrawer, mIbtnShare, mIbtnBookMark, mIbtn_preview_image, mIbtn_set_alarm, mIbtn_openMap;
    private ImageButton mIbtnInfo, mIbtnEight, mIbtnSetting, mIbtnWho;
    private ImageView mIvMainAd;
    private Retrofit mRetrofit;
    double latitude = 37.30276, longitude = 127.02444;

    private RegionResponse.result mResult;
    ArrayList<BookMarkData> mAlBookMarkData, mAlNewData;
    boolean pagerReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Main", "main was created");

        // 어댑터 생성 ( 메인 뷰 페이저 )
        mPagerAdapter = new MainViewPagerAdapter(this);
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(10);

        actList.add(this);

        mAlBookMarkData = getBookMarkList();
        pagerReady = getIntent().getBooleanExtra("pagerReady",false);



        GpsInfo gps = new GpsInfo(MainActivity.this);
        LatLng latLng = new LatLng(latitude, longitude);
        if(gps.isGetLocation()){
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            // Creating a LatLng object for the current location

             latLng = new LatLng(latitude, longitude);
        }
        if(latitude == 0 || longitude == 0){
            latitude = 37.30276;
            longitude = 127.02444;
        }


        Log.e("gps", longitude + " " + latitude);
        if (mAlBookMarkData.size() == 0) {
            BookMarkData cur_my_loc = new BookMarkData("현재 위치", longitude, latitude);
//            Log.e("Main, size<1", "현재 위치" + longitude + " " + latitude);
            mAlBookMarkData.add(cur_my_loc);
            saveBookMarkList(mAlBookMarkData);

            mPagerAdapter.addItem(cur_my_loc);
            Log.e("mPagerSize", mPagerAdapter.getCount() + "");
            mPagerAdapter.notifyDataSetChanged();
        }

        boolean restartFalg = getIntent().getBooleanExtra("restart",false);
        if(restartFalg == false){
            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
            startActivity(intent);
            if (mAlBookMarkData.get(0).noticeDialogFlag) {
                showNotice();
            }
        }
        // 첫 정보 를 좌표로 가져오기

        Log.e("sharedPreCOunt", mAlBookMarkData.size() + "");

        mIbtn_openMap = findViewById(R.id.ibtn_openMap);
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



        mPagerAdapter.addItem(new BookMarkData("현재 위치", longitude, latitude));
        Log.e("Main, size<1", "현재 위치" + longitude + " " + latitude + "adapter added");


//        for (int i = 1; i < mAlBookMarkData.size(); i++) {
//            String curName = mAlBookMarkData.get(i).getLocation_name();
//            Double tm_x = mAlBookMarkData.get(i).tm_x;
//            Double tm_y = mAlBookMarkData.get(i).tm_y;
//            Log.e("curName : ", curName);
//            int pos = mPagerAdapter.addItem(new BookMarkData(curName, tm_x, tm_y));
//            mPagerAdapter.notifyDataSetChanged();
//
//            Log.e("Main, for", "curName" + curName + " " + tm_x+ " " +tm_y + "adapter added");
//            getRegion(curName, pos);
//            getEtc(curName, pos);
//            getGrade(curName, pos);
//            getNotice();
//            getHourForecast(pos);
//            getDayForecast(pos);
//        }
//        mPagerAdapter.notifyDataSetChanged();


        final double finalLatitude = 37.715133;
        final double finalLongitude = 126.734086;
        mIbtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "https://dowellcomputer.page.link/Goto");

                Intent chooser = Intent.createChooser(intent, "공유");
                startActivity(chooser);
            }
        });
        mIbtn_openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PreviewImageActivity.class);
                Bundle args = new Bundle();
                args.putDouble("latitude", finalLatitude);
                args.putDouble("longitude", finalLongitude);
                args.putString("station", mPagerAdapter.getItem(0).tv_location_name.getText().toString());
                args.putString("status", mPagerAdapter.getItem(0).tv_mise_status.getText().toString());
//                Log.e("tv_location_name", mPagerAdapter.getItem(0).tv_location_name.getText().toString());
                intent.putExtra("bundle", args);
                startActivity(intent);
            }
        });
        mIbtnBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookMarkActivity.class);
                HashMap<String, String> hashMap = new HashMap<String, String>();
                for (int i = 0; i < mPagerAdapter.getCount(); i++) {
                    String name = mPagerAdapter.getItem(i).getLocation_name();
                    String state = mPagerAdapter.getItem(i).tv_mise_status.getText().toString();
                    Log.e("testIndex", i + "");
                    Log.e("testName", name);
                    Log.e("testState", state);
                    hashMap.put(name, state);
                }
                intent.putExtra("hashMap", hashMap);
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
    protected void onStart() {
        super.onStart();
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                getEtc2(longitude, latitude, 0);
            }
        }, 2000);  // 2000은 2초를 의미합니다.


        for (int i = 1; i < mAlBookMarkData.size(); i++) {
            final String curName = mAlBookMarkData.get(i).getLocation_name();
            Double tm_x = mAlBookMarkData.get(i).tm_x;
            Double tm_y = mAlBookMarkData.get(i).tm_y;
            Log.e("curName : ", curName);
            final int pos = mPagerAdapter.addItem(new BookMarkData(curName, tm_x, tm_y));
            mPagerAdapter.notifyDataSetChanged();

            Log.e("Main, for", "curName" + curName + " " + tm_x+ " " +tm_y + "adapter added");

            // 2초간 멈추게 하고싶다면
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
//                    my_button.setBackgroundResource(R.drawable.defaultcard);
                    getRegion(curName, pos);
                    getEtc(curName, pos);
                    getGrade(curName, pos);
                    getNotice();
                    getHourForecast(pos);
                    getDayForecast(pos);
                }
            }, 1000);  // 2000은 2초를 의미합니다.

//            getRegion(curName, pos);
//            getEtc(curName, pos);
//            getGrade(curName, pos);
//            getNotice();
//            getHourForecast(pos);
//            getDayForecast(pos);
        }
        mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("resume", mAlBookMarkData.size() + " " + getBookMarkList().size());

        if (mAlBookMarkData.size() != getBookMarkList().size()) {
//            actFinish();

        }
        Log.e("resume", "resume2");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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
    public void getEtcResult2(EtcResponse.etcResult etcResult, int pos) {
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
        Log.e("hourForecast데이터", result.get(0).getHour() + "");
        mPagerAdapter.setHourForecast(result, pos);
    }

    @Override
    public void getDayForecastResult(ArrayList<DayForecastResponse.ForecastResult> result, int pos) {
        Log.e("dayForecast데이터", result.get(0).getDay() + "");
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

    public void getEtc2(Double x, Double y, int pos) {
        final MainService mainService = new MainService(this);
        mainService.getEtc2(x, y, pos);
    }

    public void getHourForecast(int pos) {
        final MainService mainService = new MainService(this);
        mainService.getHourForecast(pos);
    }

    public void getDayForecast(int pos) {
        final MainService mainService = new MainService(this);
        mainService.getDayForecast(pos);
    }

    public void showNotice() {
        NoticeDialog noticeDialog = new NoticeDialog(MainActivity.this);
        noticeDialog.callFunction();
    }
}
