package com.softsquared.template.src.main.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.softsquared.template.R;
import com.softsquared.template.src.BookMarkData;
import com.softsquared.template.src.bookMark.models.BookMarkResponse;
import com.softsquared.template.src.main.MainService;
import com.softsquared.template.src.main.fragments.MapFragment;
import com.softsquared.template.src.main.interfaces.MainActivityView;
import com.softsquared.template.src.main.items.PreDayItem;
import com.softsquared.template.src.main.items.PreTimeItem;
import com.softsquared.template.src.main.models.DayForecastResponse;
import com.softsquared.template.src.main.models.EtcResponse;
import com.softsquared.template.src.main.models.GradeResponse;
import com.softsquared.template.src.main.models.HourForecastResponse;
import com.softsquared.template.src.main.models.RegionResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import me.relex.circleindicator.CircleIndicator;


public class MainViewPagerAdapter extends PagerAdapter {
    private static final String TAG = "AnimationStarter";
    View view = null;

    boolean mUpdateFlag = true;
    private Context mContext;
    ArrayList<BookMarkData> mAlBookMarkData = new ArrayList<>();

    public MainViewPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        System.out.println("로딩" + position);


//        arrayList
        ArrayList<PreDayItem> mAlPreDayList = new ArrayList<PreDayItem>();
        ArrayList<PreTimeItem> mAlPreTimeList = new ArrayList<PreTimeItem>();


//        adapter
        final InPageViewPagerAdapter mInPagePagerAdapter;
        final RecyclerPreDayAdapter mRecyclerPreDayAdapter;
        final RecyclerPreTimeAdapter mRecyclerPreTimeAdapter;

//        view group
        final RecyclerView mRvPreDay, mRvPreTime;
        final ViewPager mInPageViewPager;

//        view
        final ImageButton ibtnLeft, ibtnRight;

        final ImageView IvStatusImage;
        final TextView tv_myLocation;
        final RelativeLayout rloStatusLayout;

//        indicator
        final CircleIndicator indicator;
        final MapFragment fragment1;

//        fragment
        final FragmentManager fragmentManager;
        final FragmentTransaction fragmentTransaction = null;


        if (mContext != null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.layout_page, container, false);

            MapFragment mapFragment = new MapFragment();
            FragmentTransaction transaction = ((Activity) mContext).getFragmentManager().beginTransaction();
            transaction.add(R.id.llo_fragment, mapFragment);
            transaction.commit();

            //배경색 초기화
            String stateColor = decideColor(position % 4 + 1);
            view.setBackgroundColor(Color.parseColor(stateColor));
            String cardBackColor = decideCardColor(position % 4 + 1);
            (view.findViewById(R.id.card_day_pre)).setBackgroundColor(Color.parseColor(cardBackColor));
            (view.findViewById(R.id.card_time_pre)).setBackgroundColor(Color.parseColor(cardBackColor));
            (view.findViewById(R.id.card_ad)).setBackgroundColor(Color.parseColor(cardBackColor));
            (view.findViewById(R.id.card_details)).setBackgroundColor(Color.parseColor(cardBackColor));
            (view.findViewById(R.id.card_searcher_info)).setBackgroundColor(Color.parseColor(cardBackColor));
            (view.findViewById(R.id.card_six_status)).setBackgroundColor(Color.parseColor(cardBackColor));


            final View finalView = view;
            rloStatusLayout = view.findViewById(R.id.rlo_statusLayout);
            IvStatusImage = view.findViewById(R.id.iv_statusImage);
            indicator = view.findViewById(R.id.iv_oval);
            indicator.createIndicators(getCount(), position);

            tv_myLocation = view.findViewById(R.id.tv_myLocation);
            ibtnLeft = view.findViewById(R.id.ibtn_left);
            ibtnRight = view.findViewById(R.id.ibtn_right);
            tv_myLocation.setText("pageNum : " + position);
            GradientDrawable rounding_drawable = (GradientDrawable) mContext.getDrawable(R.drawable.image_rounding);

            mInPageViewPager = view.findViewById(R.id.vp_inPage);
            mInPageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                    Log.e("onPageScrolled", "log");
                }

                @Override
                public void onPageSelected(int position) {
//                    Log.e("onPageSelected", "log");
                }

                @Override
                public void onPageScrollStateChanged(int state) {
//                    Log.e("ScrollStateChanged", "log");
                }
            });
            if (mUpdateFlag) { // 애니메이션을 위한 플래그 설정
                goAnimation(IvStatusImage);
//                mUpdateFlag = false;
            }

            BookMarkData bookMarkData = mAlBookMarkData.get(position);
            bookMarkData.tv_update_time = view.findViewById(R.id.tv_details_update_time);
            bookMarkData.tv_pm10_mang_name = view.findViewById(R.id.tv_details_pm10_div);
            bookMarkData.tv_pm25_mang_name = view.findViewById(R.id.tv_details_pm25_div);
            bookMarkData.tv_pm25_station = view.findViewById(R.id.tv_details_pm25_name);
            bookMarkData.tv_pm10_station = view.findViewById(R.id.tv_details_pm10_name);
            bookMarkData.tv_no2_station = view.findViewById(R.id.tv_details_no2);
            bookMarkData.tv_co_station = view.findViewById(R.id.tv_details_co);
            bookMarkData.tv_o3_station = view.findViewById(R.id.tv_details_o3);
            bookMarkData.tv_so2_station = view.findViewById(R.id.tv_details_so2);
            bookMarkData.tv_current_location_flag = view.findViewById(R.id.tv_current_location_flag);
            bookMarkData.v_main = view;
            bookMarkData.card_ad = view.findViewById(R.id.card_ad);
            bookMarkData.card_day_pre = view.findViewById(R.id.card_day_pre);
            bookMarkData.card_details = view.findViewById(R.id.card_details);
            bookMarkData.card_searcher_info = view.findViewById(R.id.card_searcher_info);
            bookMarkData.card_six_status = view.findViewById(R.id.card_six_status);
            bookMarkData.card_time_pre= view.findViewById(R.id.card_time_pre);
            bookMarkData.tv_api_update_time = view.findViewById(R.id.tv_api_update_time);

            if (position != 0) {
                bookMarkData.tv_current_location_flag.setVisibility(View.INVISIBLE);
            }
            bookMarkData.tv_api_update_time = view.findViewById(R.id.tv_api_update_time);
            bookMarkData.tv_mise_status = view.findViewById(R.id.tv_miseStatus);
            bookMarkData.tv_mise_status_details = view.findViewById(R.id.tv_miseStatusDetails);
            bookMarkData.tv_details_total_value = view.findViewById(R.id.tv_details_total);
            bookMarkData.tv_details_total_status = view.findViewById(R.id.tv_details_total_status);


            bookMarkData.iv_statusImage = view.findViewById(R.id.iv_statusImage);


            mInPageViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });


//            mInPageViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//                @Override
//                public void onPageSelected(int position) {
//                    if (position < 2)
//                        mInPageViewPager.setCurrentItem((position + 2), true);
//                    else if (position >= 2 * 2)
//                        mInPageViewPager.setCurrentItem((position - 2), true);
//                }
//            });



            bookMarkData.mInPagePagerAdapter = new InPageViewPagerAdapter(mContext);
            mInPageViewPager.setAdapter(bookMarkData.mInPagePagerAdapter);


            mRvPreDay = view.findViewById(R.id.rv_preDay);
            bookMarkData.mRecyclerPreDayAdapter = new RecyclerPreDayAdapter(mAlPreDayList);
            mRvPreDay.setAdapter(bookMarkData.mRecyclerPreDayAdapter);
            mRvPreDay.setLayoutManager(new LinearLayoutManager(mContext));

            mRvPreTime = view.findViewById(R.id.rv_preTime);

            bookMarkData.mRecyclerPreTimeAdapter = new RecyclerPreTimeAdapter(mAlPreTimeList);
            mRvPreTime.setAdapter(bookMarkData.mRecyclerPreTimeAdapter);
            mRvPreTime.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));


//            for (int i = 0; i < 14; i++) {
//                mRecyclerPreTimeAdapter.addItem(new PreTimeItem("시간", "좋음"));
//            }

//            mRecyclerPreDayAdapter.notifyDataSetChanged();


            mRvPreTime.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });


            ibtnLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mInPageViewPager.setCurrentItem((mInPageViewPager.getCurrentItem() + 1) % 2, true);
                }
            });


            ibtnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mInPageViewPager.setCurrentItem((mInPageViewPager.getCurrentItem() + 1) % 2, true);
                }
            });

            BookMarkData curData = mAlBookMarkData.get(position);
            TextView tv_myLoc = view.findViewById(R.id.tv_myLocation);
            tv_myLoc.setText(curData.getLocation_name());
        }

        //Initialize the pager
        container.addView(view);
        return view;


    }

    public String decideColor(int curGrade) {
        switch (curGrade) {
            case 1:
                return "#0277bd";
            case 2:
                return "#0098a6";
            case 3:
                return "#398e3d";
            case 4:
                return "#d74315";
            default:
                return "#ffffff";
        }
    }

    public String decideCardColor(int curGrade) {
        switch (curGrade) {
            case 1:
                return "#0288d1";
            case 2:
                return "#00acc2";
            case 3:
                return "#43a047";
            case 4:
                return "#e64a19";
            default:
                return "#ffffff";
        }
    }

    public void goAnimation(final ImageView IvStatusImage) {
        IvStatusImage.clearAnimation();
        TranslateAnimation transAnim = new TranslateAnimation(0, 0, -500,
                IvStatusImage.getTop());
        transAnim.setStartOffset(100);
        transAnim.setDuration(1000);
        transAnim.setFillAfter(true);
        transAnim.setInterpolator(new BounceInterpolator());
        transAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(TAG, "Starting button dropdown animation");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG,
                        "Ending button dropdown animation. Clearing animation and setting layout");
                IvStatusImage.clearAnimation();
                final int left = IvStatusImage.getLeft();
                final int top = IvStatusImage.getTop();
                final int right = IvStatusImage.getRight();
                final int bottom = IvStatusImage.getBottom();
                IvStatusImage.layout(left, top, right, bottom);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        IvStatusImage.startAnimation(transAnim);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mAlBookMarkData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View) object);
    }

    public static String saveBitmap(String filename, Bitmap bm, int quality, Context mContext) throws IOException {
        File f = new File(mContext.getCacheDir(), filename);
        f.createNewFile();

        //Convert bitmap to byte array
        Bitmap bitmap = bm;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

        //write the bytes in file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getAbsolutePath();
    }

    public ArrayList<BookMarkData> getBookMarkList() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext.getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString("bookMark", "");
        Type type = new TypeToken<ArrayList<BookMarkData>>() {
        }.getType();
        ArrayList<BookMarkData> arrayList = gson.fromJson(json, type);
        if (arrayList == null) arrayList = new ArrayList<>();
        return arrayList;
    }

    public void saveBookMarkList(ArrayList<BookMarkData> bookMarkList) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext.getApplicationContext());
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bookMarkList);
        editor.putString("bookMark", json);
        editor.commit();
    }

    public int addItem(BookMarkData bookMarkData) {
        int ret = mAlBookMarkData.size();
        mAlBookMarkData.add(bookMarkData);
        return ret;
    }

    public BookMarkData getItem(int pos) {
        return mAlBookMarkData.get(pos);
    }

    public void setEtcStatus(int pos, EtcResponse.etcResult etcResult) {
        String s = null;
        if (etcResult.getUpdate_time() == null) {
            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);
            inputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            s = inputFormat.format(now);
        } else {
            s = etcResult.getUpdate_time().toString();
        }
        mAlBookMarkData.get(pos).tv_api_update_time.setText(s);
        mAlBookMarkData.get(pos).tv_update_time.setText(" " + s);
        mAlBookMarkData.get(pos).tv_pm10_mang_name.setText(" " + etcResult.getPm10_mang_name());
        mAlBookMarkData.get(pos).tv_pm25_mang_name.setText(" " + etcResult.getPm25_mang_name());
        mAlBookMarkData.get(pos).tv_pm25_station.setText(" " + etcResult.getPm25_station());
        mAlBookMarkData.get(pos).tv_pm10_station.setText(" " + etcResult.getPm10_station());
        mAlBookMarkData.get(pos).tv_no2_station.setText(" " + etcResult.getNo2_station());
        mAlBookMarkData.get(pos).tv_co_station.setText(" " + etcResult.getCo_station());
        mAlBookMarkData.get(pos).tv_o3_station.setText(" " + etcResult.getO3_station());
        mAlBookMarkData.get(pos).tv_so2_station.setText(" " + etcResult.getSo2_station());
        notifyDataSetChanged();
        Log.e("tv_pm10_mang_name", etcResult.getPm10_mang_name());
        Log.e("tv_pm25_mang_name", etcResult.getPm25_mang_name());
        Log.e("tv_pm10_station", etcResult.getPm10_station());
    }

    public void setGradeResult(int pos, GradeResponse.gradeResult gradeResult) {
        BookMarkData bookMarkData = mAlBookMarkData.get(pos);
        int cur_grade = gradeResult.getCurrent_status_grade();
        int total_grade = gradeResult.getTotal_grade();
        switch (cur_grade) {
            case 1:
                bookMarkData.iv_statusImage.setImageResource(R.drawable.ic_smile_1);
                bookMarkData.tv_mise_status.setText("좋음");
                bookMarkData.tv_mise_status_details.setText("신선한 공기 많이 마시세요~");
                break;
            case 2:
                bookMarkData.iv_statusImage.setImageResource(R.drawable.ic_smile_2);
                bookMarkData.tv_mise_status.setText("양호");
                bookMarkData.tv_mise_status_details.setText("쾌적한 날이에요~");
                break;
            case 3:
                bookMarkData.iv_statusImage.setImageResource(R.drawable.ic_smile_3);
                bookMarkData.tv_mise_status.setText("보통");
                bookMarkData.tv_mise_status_details.setText("그냥 무난한 날이에요");
                break;
            case 4:
                bookMarkData.iv_statusImage.setImageResource(R.drawable.ic_smile_4);
                bookMarkData.tv_mise_status.setText("나쁨");
                bookMarkData.tv_mise_status_details.setText("탁한 공기, 마스크 챙기세요~");
                break;
            case 5:
                bookMarkData.iv_statusImage.setImageResource(R.drawable.ic_smile_0);
                bookMarkData.tv_mise_status.setText("점검중인 지역입니다.");
                bookMarkData.tv_mise_status_details.setText("setting...");
                break;
        }
        switch (total_grade) {
            case 1:
                bookMarkData.tv_details_total_status.setText(" 좋음 (최근 24시간 평균)");
                break;
            case 2:
                bookMarkData.tv_details_total_status.setText(" 양호 (최근 24시간 평균)");
                break;
            case 3:
                bookMarkData.tv_details_total_status.setText(" 보통 (최근 24시간 평균)");
                break;
            case 4:
                bookMarkData.tv_details_total_status.setText(" 나쁨 (최근 24시간 평균)");
                break;
            case 5:
                break;
        }

        String stateColor = decideColor(cur_grade);
        bookMarkData.v_main.setBackgroundColor(Color.parseColor(stateColor));
        String cardBackColor = decideCardColor(cur_grade);
        bookMarkData.card_time_pre.setBackgroundColor(Color.parseColor(cardBackColor));
        bookMarkData.card_six_status.setBackgroundColor(Color.parseColor(cardBackColor));
        bookMarkData.card_searcher_info.setBackgroundColor(Color.parseColor(cardBackColor));
        bookMarkData.card_details.setBackgroundColor(Color.parseColor(cardBackColor));
        bookMarkData.card_day_pre.setBackgroundColor(Color.parseColor(cardBackColor));
        bookMarkData.card_ad.setBackgroundColor(Color.parseColor(cardBackColor));

    }

    public void setRegionStatus(int pos, RegionResponse.result result) {
        BookMarkData bookMarkData = mAlBookMarkData.get(pos);
        bookMarkData.tv_details_total_value.setText(" " + result.getTotal_value() + " unit (최근 24시간 평균)");

        if (bookMarkData.mInPagePagerAdapter != null)
            bookMarkData.mInPagePagerAdapter.infos = new ArrayList<Float>();
        bookMarkData.mInPagePagerAdapter.addItem((float) result.getPm10_value());
        bookMarkData.mInPagePagerAdapter.addItem((float) result.getPm25_value());
        bookMarkData.mInPagePagerAdapter.addItem((float) result.getNo2_value());
        bookMarkData.mInPagePagerAdapter.addItem((float) result.getO3_value());
        bookMarkData.mInPagePagerAdapter.addItem((float) result.getCo_value());
        bookMarkData.mInPagePagerAdapter.addItem((float) result.getSo2_value());

        mAlBookMarkData.get(pos).mInPagePagerAdapter.myNotifyDataSetChanged();
    }

    public void setHourForecast(ArrayList<HourForecastResponse.ForecastResult> result, int pos){
        BookMarkData bookMarkData = mAlBookMarkData.get(pos);

        for(int i=0;i<result.size();i++){
            bookMarkData.mRecyclerPreTimeAdapter.addItem(new PreTimeItem(result.get(i).getHour(), result.get(i).getCurrent_grade()));
        }
        bookMarkData.mRecyclerPreTimeAdapter.notifyDataSetChanged();
    }
    public void setDayForecast(ArrayList<DayForecastResponse.ForecastResult> result, int pos){
        BookMarkData bookMarkData = mAlBookMarkData.get(pos);

        for(int i=0;i<result.size();i++){
            bookMarkData.mRecyclerPreDayAdapter.addItem(new PreDayItem(result.get(i).getDay(), result.get(i).getTime(), result.get(i).getCurrent_grade()));
        }
        bookMarkData.mRecyclerPreDayAdapter.notifyDataSetChanged();
    }
}