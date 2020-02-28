package com.softsquared.template.src.preview.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.softsquared.template.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JapanFragment extends Fragment {
    ArrayList<String> mAlPictures;
    ArrayList<Bitmap> mAlBitmapPictures;
    SeekBar mSeekBar;
    ImageButton mIbtn_play, mIbtn_goNext, mIbtn_goPrev, mIbtn_cancel;
    ImageView mIv_japanImage;
    int mSeekBarPosition = 0;
    boolean mPlayFlag = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAlPictures = new ArrayList<String>();
        mAlBitmapPictures = new ArrayList<Bitmap>();
        forcedInit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.layout_japan_fragment, container, false);
        mIv_japanImage = layout.findViewById(R.id.iv_japan);
        mSeekBar = layout.findViewById(R.id.sb_japan);
        mIbtn_play = layout.findViewById(R.id.ibtn_play_japan);
        mIbtn_goNext = layout.findViewById(R.id.ibtn_go_next);
        mIbtn_goPrev = layout.findViewById(R.id.ibtn_go_prev);
        mIbtn_cancel = layout.findViewById(R.id.ibtn_cancel_japan);



        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

//                Glide.with(JapanFragment.this).load(mAlPictures.get(mSeekBarPosition)).dontAnimate().into(mIv_japanImage);
                if(mSeekBarPosition < mAlBitmapPictures.size())
                    mIv_japanImage.setImageBitmap(mAlBitmapPictures.get(mSeekBarPosition));
                else{
                    mSeekBar.setProgress(0);
                    mPlayFlag = false;
                    mIbtn_play.setImageResource(R.drawable.ic_right_black);
                }
//                Glide.with(JapanFragment.this).asBitmap().load(mAlPictures.get(mSeekBarPosition)).dontAnimate().into(mIv_japanImage);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mIbtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        mIbtn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mPlayFlag){
                    mPlayFlag = true;
                    mSeekBarPosition %= mAlBitmapPictures.size();
                    mIbtn_play.setImageResource(R.drawable.pause);
                    startTimerThread();
                }else{
                    mPlayFlag = false;
                    mIbtn_play.setImageResource(R.drawable.ic_right_black);
                }
            }
        });
        mIbtn_goPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekBarPosition = (mSeekBarPosition - 2 + mAlBitmapPictures.size()) % mAlBitmapPictures.size();
                mSeekBar.setProgress(mSeekBarPosition);
            }
        });
        mIbtn_goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekBarPosition++;
                if(mSeekBarPosition >= mAlBitmapPictures.size()) {
                    mPlayFlag = false;
                    mIbtn_play.setImageResource(R.drawable.ic_right_black);
                    mSeekBarPosition = 0;
                    mSeekBar.setProgress(0);
                }
                else{
                    mSeekBarPosition %= mAlBitmapPictures.size();
                    mSeekBar.setProgress(mSeekBarPosition);
                }

            }
        });


        return layout;
    }


    void forcedInit() {
        for (int i = 24; i <= 69; i += 3) {
            mAlPictures.add("https://static.tenki.jp/static-images/pm25/" + i + "/japan-detail/large.jpg");
        }
        for (int i = 0; i < mAlPictures.size(); i++) {
            URL url = null;
            DownloadImageTask downloadImageTask = new DownloadImageTask(mAlBitmapPictures, new AsyncTaskCallback() {
                @Override
                public void onSuccess(String result) {
                    if(mAlBitmapPictures.size() == 1){
                        mIv_japanImage.setImageBitmap(mAlBitmapPictures.get(0));
                    }
                }
                @Override
                public void onFailure(String e) {

                }
            });

            try {
                url = new URL(mAlPictures.get(i));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            downloadImageTask.execute(url);
        }
    }

    public void startTimerThread() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                while (mPlayFlag && mSeekBarPosition < mAlBitmapPictures.size()) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        public void run() {
                            mSeekBar.setProgress(mSeekBarPosition);
                            mSeekBarPosition = (mSeekBarPosition + 1);
                            Log.e("mSeekBarPosition : ", mSeekBarPosition + "");
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }
//
//    public static Bitmap getBitmapFromURL(String src) {
//        try {
//            URL url = new URL(src);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//            return myBitmap;
//        } catch (IOException e) {
//            // Log exception
//            return null;
//        }
//    }
//    public class DownloadImagesTask extends AsyncTask<ImageView, Void, Bitmap> {
//        Bitmap bitmap = null;
//
//        @Override
//        protected Bitmap doInBackground(Bitmap... Bitmap) {
//            this.bitmap = Bitmap[0];
//            return bitmap;
//        }
//
//        @Override
//        protected Bitmap doInBackground(ImageView... imageViews) {
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap result) {
//            bitmap =  result;
//        }
//        private Bitmap download_Image(String src) {
//            try {
//                URL url = new URL(src);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream input = connection.getInputStream();
//                Bitmap myBitmap = BitmapFactory.decodeStream(input);
//                return myBitmap;
//            } catch (IOException e) {
//                // Log exception
//                return null;
//            }
//
//        }
//    }
}
