package com.softsquared.template.src.main.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
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
import com.softsquared.template.src.main.models.RegionResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

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

        int backColor = R.color.colorSoso;
        int cardColor = R.color.colorCardSoso;

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
            if (mUpdateFlag) { // 애니메이션을 위한 플래그 설정
                goAnimation(IvStatusImage);
//                mUpdateFlag = false;
            }

            BookMarkData bookMarkData = mAlBookMarkData.get(position);


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
            mInPageViewPager.setAdapter( bookMarkData.mInPagePagerAdapter );



            mRvPreDay = view.findViewById(R.id.rv_preDay);
            mRecyclerPreDayAdapter = new RecyclerPreDayAdapter(mAlPreDayList);
            mRvPreDay.setAdapter(mRecyclerPreDayAdapter);
            mRvPreDay.setLayoutManager(new LinearLayoutManager(mContext));

            mRvPreTime = view.findViewById(R.id.rv_preTime);
            mRecyclerPreTimeAdapter = new RecyclerPreTimeAdapter(mAlPreTimeList);
            mRvPreTime.setAdapter(mRecyclerPreTimeAdapter);
            mRvPreTime.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));


            for (int i = 0; i < 14; i++) {
                mRecyclerPreDayAdapter.addItem(new PreDayItem("요일", "아침", "좋음"));
            }
            for (int i = 0; i < 14; i++) {
                mRecyclerPreTimeAdapter.addItem(new PreTimeItem("시간", "좋음"));
            }

            mRecyclerPreDayAdapter.notifyDataSetChanged();
            mRecyclerPreTimeAdapter.notifyDataSetChanged();

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
                    mInPageViewPager.setCurrentItem((mInPageViewPager.getCurrentItem() + 1)%2, true);
                }
            });


            ibtnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mInPageViewPager.setCurrentItem((mInPageViewPager.getCurrentItem() + 1)%2, true);
                }
            });

            //여기부터 데이터 세팅 해주기
            BookMarkData curData = mAlBookMarkData.get(position);
            TextView tv_myLoc = view.findViewById(R.id.tv_myLocation);
            TextView tv_o3 = view.findViewById(R.id.tv_details_o3);
            tv_myLoc.setText(curData.getLocation_name());
            tv_o3.setText(curData.getO3_status());
            String curName = curData.getLocation_name();
//            getRegion(curName);
        }

        //Initialize the pager
        container.addView(view);
        return view;
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

    public int addItem(BookMarkData bookMarkData){
        int ret = mAlBookMarkData.size();
        mAlBookMarkData.add(bookMarkData);
        return ret;
    }

    public BookMarkData getItem(int pos){
        return mAlBookMarkData.get(pos);
    }


    public void setRegionStatus(int pos, RegionResponse.result result) {
        BookMarkData bookMarkData = mAlBookMarkData.get(pos);

        result.setCo_value((float)10);
        result.setNo2_value((float)10);
        result.setO3_value((float)10);
        result.setPm10_value(10);
        result.setPm25_value(10);
        result.setSo2_value((float)10);
        result.setTotal_value(10);
        if(bookMarkData.mInPagePagerAdapter != null)
            bookMarkData.mInPagePagerAdapter.infos = new ArrayList<Float>();
        bookMarkData.mInPagePagerAdapter.addItem((float)result.getPm10_value());
        bookMarkData.mInPagePagerAdapter.addItem((float)result.getPm25_value());
        bookMarkData.mInPagePagerAdapter.addItem((float)result.getNo2_value());
        bookMarkData.mInPagePagerAdapter.addItem((float)result.getO3_value());
        bookMarkData.mInPagePagerAdapter.addItem((float)result.getCo_value());
        bookMarkData.mInPagePagerAdapter.addItem((float)result.getSo2_value());

        //bookMarkData.setRegionResponse_result(result);
        mAlBookMarkData.get(pos).mInPagePagerAdapter.myNotifyDataSetChanged();
    }
}