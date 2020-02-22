package com.softsquared.template.src.main.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.softsquared.template.R;
import com.softsquared.template.src.bookMark.activity.BookMarkActivity;
import com.softsquared.template.src.main.fragments.MapFragment;
import com.softsquared.template.src.main.items.PreDayItem;
import com.softsquared.template.src.main.items.PreTimeItem;
import com.softsquared.template.src.main.sideBar.SideEightStage;
import com.softsquared.template.src.main.sideBar.SideInfoActivity;
import com.softsquared.template.src.main.sideBar.SideSetting;
import com.softsquared.template.src.main.sideBar.SideWho;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


public class MainViewPagerAdapter extends PagerAdapter {
    private static final String TAG = "AnimationStarter";

    boolean updateFlag = true;
    private Context mContext = null;

    public MainViewPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
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
        final ImageButton IbtnOpenDrawer, ibtnInfo, ibtnShare, ibtnEight, ibtnSetting, ibtnWho, ibtnLeft, ibtnRight, ibtnBookMark;
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
            transaction.addToBackStack(null);
            transaction.commit();

            final View finalView = view;
            rloStatusLayout = view.findViewById(R.id.rlo_statusLayout);
            ibtnEight = view.findViewById(R.id.ibtn_eight);
            IbtnOpenDrawer = view.findViewById(R.id.ibtn_openDrawer);
            IvStatusImage = view.findViewById(R.id.iv_statusImage);
            ibtnShare = view.findViewById(R.id.ibtn_share);
            indicator = view.findViewById(R.id.iv_oval);
            indicator.createIndicators(6, position);
            ibtnWho = view.findViewById(R.id.ibtn_who);
            tv_myLocation = view.findViewById(R.id.tv_myLocation);
            ibtnLeft = view.findViewById(R.id.ibtn_left);
            ibtnRight = view.findViewById(R.id.ibtn_right);
            ibtnBookMark = view.findViewById(R.id.ibtn_bookMark);
            tv_myLocation.setText("pageNum : " + position);

            mInPageViewPager = view.findViewById(R.id.vp_inPage);
            if(updateFlag){
                goAnimation(IvStatusImage);
//                updateFlag = false;
            }

            mInPageViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });


            mInPageViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    if (position < 2)
                        mInPageViewPager.setCurrentItem((position + 2), true);
                    else if (position >= 2 * 2)
                        mInPageViewPager.setCurrentItem((position - 2), true);
                }
            });
            mInPagePagerAdapter = new InPageViewPagerAdapter(mContext);
            mInPageViewPager.setAdapter(mInPagePagerAdapter);

            mInPagePagerAdapter.addItem(0);
            mInPagePagerAdapter.addItem(1);
            mInPagePagerAdapter.notifyDataSetChanged();

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


            ibtnBookMark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext.getApplicationContext(), BookMarkActivity.class);
                    mContext.startActivity(intent);
                }
            });

            ibtnLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mInPageViewPager.setCurrentItem(mInPageViewPager.getCurrentItem() + 1, true);
                }
            });


            ibtnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mInPageViewPager.setCurrentItem(mInPageViewPager.getCurrentItem() + 1, true);
                }
            });


            ibtnWho.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SideWho.class);
                    mContext.startActivity(intent);
                }
            });

            ibtnSetting = view.findViewById(R.id.ibtn_setting);
            ibtnSetting.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SideSetting.class);
                    mContext.startActivity(intent);
                }
            });

            ibtnInfo = view.findViewById(R.id.btn_info);
            ibtnEight.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SideEightStage.class);
                    mContext.startActivity(intent);
                }
            });
            ibtnShare.setOnClickListener(new Button.OnClickListener() {
                String path;

                @Override
                public void onClick(View v) {
//
//                    View container = ((Activity)mContext).getWindow().getDecorView();
//                    container.buildDrawingCache();
//                    Bitmap bm = container.getDrawingCache();
//                    String fileName = "image_" + System.currentTimeMillis();
//
//                    try {
//                        path = saveBitmap(fileName, bm, 30, mContext);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
////                    Uri uri = Uri.fromFile(new File(path));
//                    Uri uri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() , new File(path));
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    intent.putExtra(Intent.EXTRA_STREAM, uri);
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    intent.setType("image/*");
//                    mContext.startActivity(Intent.createChooser(intent, "공유하기"));
                }
            });

            ibtnInfo.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SideInfoActivity.class);
                    mContext.startActivity(intent);
                }
            });


            IbtnOpenDrawer.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DrawerLayout drawer = (DrawerLayout) finalView.findViewById(R.id.dlo_drawer);
                    if (!drawer.isDrawerOpen(Gravity.LEFT)) {
                        drawer.openDrawer(Gravity.LEFT);
                    }
                }
            });


        }

        //Initialize the pager
        container.addView(view);
        return view;
    }

    public void goAnimation(final ImageView IvStatusImage){
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
        return 6;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View) object);
    }

    private int getDisplayHeight(View view) {
        return view.getResources().getDisplayMetrics().heightPixels;

    }

    private int getDisplayWidtht(View view) {
        return view.getResources().getDisplayMetrics().widthPixels;
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

}