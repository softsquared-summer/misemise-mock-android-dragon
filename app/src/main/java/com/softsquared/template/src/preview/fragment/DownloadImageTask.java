package com.softsquared.template.src.preview.fragment;


import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<URL, Void, Bitmap> {

    private AsyncTaskCallback mCallBack;
    private static final String LOG_E_TAG = "DownloadImageTask";
    private  ArrayList<Bitmap> bitmaps = new ArrayList<>();

    public DownloadImageTask(ArrayList<Bitmap> bitmaps,  AsyncTaskCallback callback) {
        this.bitmaps = bitmaps;
        this.mCallBack = callback;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(URL... params) {
        URL imageURL = params[0];
        Bitmap downloadedBitmap = null;
        try {
            InputStream inputStream = imageURL.openStream();
            downloadedBitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            Log.e(LOG_E_TAG, e.getMessage());
            e.printStackTrace();
        }
        return downloadedBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        bitmaps.add(result);
        if (mCallBack != null){
            mCallBack.onSuccess("OK");
        } else {
            mCallBack.onFailure("NO");
        }
    }


}