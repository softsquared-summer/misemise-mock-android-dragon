package com.softsquared.template.src.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.softsquared.template.R;

import java.util.ArrayList;


public class InPageViewPagerAdapter extends PagerAdapter {
    ArrayList<Float> infos = new ArrayList<>();
    ArrayList<TextView> tvs = new ArrayList<>();
    private Context mContext;

    public InPageViewPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view;
        view = null;
//        position %= 2;
        int backColor = R.color.colorSoso;
        int cardColor = R.color.colorCardSoso;


        if (mContext != null) {
            // LayoutInflater를 통해 "/res/layout/layout_page.xml"을 뷰로 생성.
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_page_in_page, container, false);
        }
        // 뷰페이저에 추가.
        tvs.add((TextView) view.findViewById(R.id.tv_item1_value));
        tvs.add((TextView) view.findViewById(R.id.tv_item2_value));
        tvs.add((TextView) view.findViewById(R.id.tv_item3_value));



        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View) object);
    }

    public void addItem(Float info) {
        infos.add(info);
    }

    public void myNotifyDataSetChanged() {
        //Log.e("imgpaepage","notifyDataSetChanged , infsoSize : "+ infos.size());
        for (int i = 0; i < infos.size(); i++) {
            //  Log.e("hihi",Float.toString(infos.get(i)) + " /// index : " + i );
            ((TextView) tvs.get(i)).setText(Float.toString(infos.get(i)));
        }
    }
}