package com.softsquared.template.src.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.softsquared.template.R;

import java.util.ArrayList;


public class InPageViewPagerAdapter extends PagerAdapter {
    ArrayList<Integer> itemNums = new ArrayList<>();
    private Context mContext;
    public InPageViewPagerAdapter(Context context){
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        View view;
        view = null;
        position %= 2;
        int backColor = R.color.colorSoso;
        int cardColor = R.color.colorCardSoso;

        final TextView tvItem1, tvItem2, tvItem3;

        if (mContext != null) {
            // LayoutInflater를 통해 "/res/layout/layout_page.xml"을 뷰로 생성.
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_page_in_page, container, false);
        }
        // 뷰페이저에 추가.
        tvItem1 = view.findViewById(R.id.tv_item1);
        tvItem2 = view.findViewById(R.id.tv_item2);
        tvItem3 = view.findViewById(R.id.tv_item3);
        tvItem1.setText("itemNum : " + (itemNums.get(position) + 0));
        tvItem2.setText("itemNum : " + (itemNums.get(position) + 1));
        tvItem3.setText("itemNum : " + (itemNums.get(position) + 2));

        container.addView(view) ;
        return view ;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((View) object);
    }
    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }

    public void addItem(Integer itemNum){
        itemNums.add(itemNum);
    }
}