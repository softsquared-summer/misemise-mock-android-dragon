package com.softsquared.template.src.main;

import android.content.Context;
import android.media.Image;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.softsquared.template.R;


public class MainViewPagerAdapter extends PagerAdapter {
    private static final String TAG = "AnimationStarter";
    ImageButton mIbtnOpenDrawer;
    ImageView mIvStatusImage;
    Button mBtnAnimation;
    View view;
    private Context mContext = null;
    public MainViewPagerAdapter(Context context){
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        view = null;


        if (mContext != null) {
            // LayoutInflater를 통해 "/res/layout/layout_page.xml"을 뷰로 생성.
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_page, container, false);
            final View finalView = view;

            mIbtnOpenDrawer = view.findViewById(R.id.ibtn_openDrawer);
            mIvStatusImage = view.findViewById(R.id.iv_statusImage);
            mBtnAnimation = view.findViewById(R.id.btn_animation);

            mBtnAnimation.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIvStatusImage.clearAnimation();
                    TranslateAnimation transAnim = new TranslateAnimation(getDisplayWidtht()/2, getDisplayWidtht()/2, -300,
                            getDisplayHeight()/2);
                    transAnim.setStartOffset(500);
                    transAnim.setDuration(3000);
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
                            mIvStatusImage.clearAnimation();
                            final int left = mIvStatusImage.getLeft();
                            final int top = mIvStatusImage.getTop();
                            final int right = mIvStatusImage.getRight();
                            final int bottom = mIvStatusImage.getBottom();
                            mIvStatusImage.layout(left, top, right, bottom);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
//                    mIvStatusImage.startAnimation(transAnim);
                }
            });



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

    private int getDisplayHeight(){
        return view.getResources().getDisplayMetrics().heightPixels;

    }
    private int getDisplayWidtht(){
        return view.getResources().getDisplayMetrics().widthPixels;
    }
}
