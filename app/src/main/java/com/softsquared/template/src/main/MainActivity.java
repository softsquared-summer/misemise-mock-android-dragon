package com.softsquared.template.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.main.interfaces.MainActivityView;
import com.softsquared.template.src.splash.SplashActivity;

public class MainActivity extends BaseActivity implements MainActivityView {

    private ViewPager mViewPager;
    private MainViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_main);



        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mPagerAdapter = new MainViewPagerAdapter(this);
        mViewPager.setAdapter(mPagerAdapter);
    }



    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();

    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
