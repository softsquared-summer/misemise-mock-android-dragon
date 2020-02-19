package com.softsquared.template.src.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.softsquared.template.R;
import com.softsquared.template.src.main.sideBar.SideEightStage;
import com.softsquared.template.src.main.sideBar.SideInfoActivity;
import com.softsquared.template.src.main.sideBar.SideSetting;
import com.softsquared.template.src.main.sideBar.SideWho;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import me.relex.circleindicator.CircleIndicator;


public class InPageViewPagerAdapter extends PagerAdapter {

    View view;
    private Context mContext;
    public InPageViewPagerAdapter(Context context){
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        view = null;
        int backColor = R.color.colorSoso;
        int cardColor = R.color.colorCardSoso;

        final View finalView;


        if (mContext != null) {
            // LayoutInflater를 통해 "/res/layout/layout_page.xml"을 뷰로 생성.
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_page_in_page, container, false);
            finalView = view;





        }

        // 뷰페이저에 추가.
        container.addView(view) ;

        return view ;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((View) object);
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return (view == (View)object);
    }
}
