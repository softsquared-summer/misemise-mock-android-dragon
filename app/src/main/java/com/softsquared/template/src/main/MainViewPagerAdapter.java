package com.softsquared.template.src.main;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.softsquared.template.R;


public class MainViewPagerAdapter extends PagerAdapter {
    ImageButton mIbtnOpenDrawer;
    private Context mContext = null;
    public MainViewPagerAdapter(Context context){
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        View view = null;

        if (mContext != null) {
            // LayoutInflater를 통해 "/res/layout/layout_page.xml"을 뷰로 생성.
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_page, container, false);

            TextView textView = (TextView) view.findViewById(R.id.tv_myLocation) ;
            textView.setText("TEXT " + position) ;

            mIbtnOpenDrawer = view.findViewById(R.id.ibtn_openDrawer);

            final View finalView = view;
            mIbtnOpenDrawer.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DrawerLayout drawer = (DrawerLayout) finalView.findViewById(R.id.dlo_drawer);
                    if(!drawer.isDrawerOpen(Gravity.LEFT)){
                        drawer.openDrawer(Gravity.LEFT);
                    }
                }
            });
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
        return 10;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return (view == (View)object);
    }
}
