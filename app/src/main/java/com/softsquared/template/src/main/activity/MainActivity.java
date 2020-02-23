package com.softsquared.template.src.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.bookMark.activity.BookMarkActivity;
import com.softsquared.template.src.main.adapter.MainViewPagerAdapter;
import com.softsquared.template.src.main.sideBar.SideEightStage;
import com.softsquared.template.src.main.sideBar.SideInfoActivity;
import com.softsquared.template.src.main.sideBar.SideSetting;
import com.softsquared.template.src.main.sideBar.SideWho;
import com.softsquared.template.src.splash.SplashActivity;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private MainViewPagerAdapter mPagerAdapter;
    private ImageButton mIbtnOpenDrawer, mIbtnShare, mIbtnBookMark;
    private ImageButton mIbtnInfo, mIbtnEight, mIbtnSetting, mIbtnWho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);
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


}
