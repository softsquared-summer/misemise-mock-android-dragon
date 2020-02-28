package com.softsquared.template.src.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.softsquared.template.R;

import java.util.ArrayList;


public class InPageViewPagerAdapter extends PagerAdapter {
    ArrayList<Float> infos = new ArrayList<>();
    ArrayList<TextView> tvs = new ArrayList<>();
    ArrayList<Float> grades = new ArrayList<>();
    ArrayList<TextView> tv_grades = new ArrayList<>();
    ArrayList<ImageView> iv_statuses = new ArrayList<>();
    ArrayList<TextView> tv_infoTitles = new ArrayList<>();
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
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_page_in_page, container, false);
            if (position == 1) {
                TextView tv_item1 = view.findViewById(R.id.tv_item1);
                TextView tv_item2 = view.findViewById(R.id.tv_item2);
                TextView tv_item3 = view.findViewById(R.id.tv_item3);
                tv_item1.setText("오존");
                tv_item2.setText("일산화탄소");
                tv_item3.setText("아황산가스");
            }
        }
        // 뷰페이저에 추가.
        tvs.add((TextView) view.findViewById(R.id.tv_item1_value));
        tvs.add((TextView) view.findViewById(R.id.tv_item2_value));
        tvs.add((TextView) view.findViewById(R.id.tv_item3_value));

        tv_grades.add((TextView) view.findViewById(R.id.tv_item1_grade));
        tv_grades.add((TextView) view.findViewById(R.id.tv_item2_grade));
        tv_grades.add((TextView) view.findViewById(R.id.tv_item3_grade));

        iv_statuses.add((ImageView) view.findViewById(R.id.iv_item1_status));
        iv_statuses.add((ImageView) view.findViewById(R.id.iv_item2_status));
        iv_statuses.add((ImageView) view.findViewById(R.id.iv_item3_status));

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

    public void addGradeItem(Float grade) {
        grades.add(grade);
    }

    public void myNotifyDataSetChanged() {
        //Log.e("imgpaepage","notifyDataSetChanged , infsoSize : "+ infos.size());
        for (int i = 0; i < infos.size(); i++) {
            //  Log.e("hihi",Float.toString(infos.get(i)) + " /// index : " + i );
            ((TextView) tvs.get(i)).setText(Float.toString(infos.get(i)));
        }
    }

    public void myGradeNotifyDataSetChanged() {
        for (int i = 0; i < grades.size(); i++) {
            String strGrade = decidePageGrade(grades.get(i));
            ((TextView) tv_grades.get(i)).setText(strGrade);
            if (strGrade.equals("좋음"))
                iv_statuses.get(i).setImageResource(R.drawable.ic_smile_1);
            else if (strGrade.equals("양호"))
                iv_statuses.get(i).setImageResource(R.drawable.ic_smile_2);
            else if (strGrade.equals("보통"))
                iv_statuses.get(i).setImageResource(R.drawable.ic_smile_3);
            else if (strGrade.equals("나쁨"))
                iv_statuses.get(i).setImageResource(R.drawable.ic_smile_4);
            else
                iv_statuses.get(i).setImageResource(R.drawable.ic_smile_0);
        }
    }

    public String decidePageGrade(Float grade) {
        int curGrade = grade.intValue();
        if (curGrade == 1) {
            return "좋음";
        } else if (curGrade == 2) {
            return "양호";
        } else if (curGrade == 3) {
            return "보통";
        } else if (curGrade == 4) {
            return "나쁨";
        } else {
            return "점검중";
        }
    }
}