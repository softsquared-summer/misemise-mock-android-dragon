package com.softsquared.template.src.preview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.softsquared.template.R;

public class PreviewImageActivity extends AppCompatActivity {
    private Context mContext;
    private TabLayout mTabLayout;
    private NonSwipeViewPager mViewPager;
    private ContentsPagerAdapter mContentsPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_image);
        mContext = getApplicationContext();



        mTabLayout = (TabLayout) findViewById(R.id.layout_tab);
        // 탭 메뉴 추가
        mTabLayout.addTab(mTabLayout.newTab().setText("미세미세 지도"));
        mTabLayout.addTab(mTabLayout.newTab().setText("안양대 연구소"));
        mTabLayout.addTab(mTabLayout.newTab().setText("일본기상청"));
        mTabLayout.addTab(mTabLayout.newTab().setText("널스쿨"));

        mViewPager = (NonSwipeViewPager) findViewById(R.id.pager_content);
        mContentsPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mContentsPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("tab.getPosition()",tab.getPosition()+"");
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
