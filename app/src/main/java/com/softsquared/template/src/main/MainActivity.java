package com.softsquared.template.src.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.main.interfaces.MainActivityView;
import com.softsquared.template.src.splash.SplashActivity;

import java.io.File;

public class MainActivity extends BaseActivity implements MainActivityView {

    private ViewPager mViewPager;
    private MainViewPagerAdapter mPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
//        startActivity(intent);
        setContentView(R.layout.activity_main);
//        boolean hasPermission = (ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
//        if (!hasPermission) {
//            //ask permission
//            ActivityCompat.requestPermissions(MainActivity.this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    1);
//            Log.i("권한", "없어서 가져왔다");
//        }else{
//            Log.i("권한", "있다");
//            String folder = "CAPTURE_TEST";
//            File dirs = new File(Environment.getExternalStorageDirectory(), folder);
//            boolean checker = dirs.mkdirs();
//            if(!dirs.exists()){
//                dirs.mkdirs();
//            }
//        }

        mViewPager = findViewById(R.id.viewPager);
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
