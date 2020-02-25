package com.softsquared.template.src.bookMark.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.softsquared.template.R;
import com.softsquared.template.src.BookMarkData;
import com.softsquared.template.src.bookMark.activity.BookMarkActivity;
import com.softsquared.template.src.main.MainService;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.view.View.INVISIBLE;

//import com.softsquared.template.src.bookMark.dialog.DeleteDialog;

public class RecyclerBookMarkAdapter extends RecyclerView.Adapter<RecyclerBookMarkAdapter.ViewHolder> {

    private ArrayList<BookMarkData> mData = null;
    //    private Button mBtnEdit;
    View view;
    Context mContext;
//    Boolean editMode, Boolean animationFlag,
    public RecyclerBookMarkAdapter(ArrayList<BookMarkData> list ,Context context) {
        mData = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("리사이클러 뷰 로드");
        Context context = parent.getContext();
        initFlag();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_book_mark_item, parent, false);
        RecyclerBookMarkAdapter.ViewHolder vh = new RecyclerBookMarkAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        BookMarkData item = mData.get(position);
        if (item.isDeleteFlag() == true) {
            mData.remove(position);
            saveBookMarkList(mData);
            return;
        }
        holder.tvLocation.setText(item.getLocation_name());
        holder.tvStatus.setText(item.getMise_status());


        final TranslateAnimation animateSlideLeftToRight = new TranslateAnimation(0, 80, 0, 0);
        final TranslateAnimation animateSlideRightToLeft = new TranslateAnimation(0, -100, 0, 0);
        animateSlideLeftToRight.setDuration(500);
        animateSlideLeftToRight.setFillAfter(true);
        animateSlideRightToLeft.setDuration(500);
        animateSlideRightToLeft.setFillAfter(true);
        if (item.isEditFlag()) {
            holder.tvLocation.startAnimation(animateSlideLeftToRight);
            holder.cb_book_mark_list_checker.startAnimation(animateSlideLeftToRight);
            holder.iv_upDownChange.startAnimation(animateSlideRightToLeft);
            holder.rlo_book_mark_image.setVisibility(INVISIBLE);
            holder.cb_book_mark_list_checker.setChecked(false);
        } else {
            holder.rlo_book_mark_image.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLocation, tvStatus;
        RelativeLayout rlo_container, rlo_book_mark_image;
        CheckBox cb_book_mark_list_checker;
        ImageButton iv_upDownChange;

        ViewHolder(View itemView) {
            super(itemView);

            rlo_book_mark_image = itemView.findViewById(R.id.rlo_book_mark_image);
            tvLocation = itemView.findViewById(R.id.tv_book_mark_location);
            tvStatus = itemView.findViewById(R.id.tv_book_mark_status);
            rlo_container = itemView.findViewById(R.id.rlo_container);
            cb_book_mark_list_checker = itemView.findViewById(R.id.cb_book_mark_list_checker);
            iv_upDownChange = itemView.findViewById(R.id.iv_upDownChange);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!((BookMarkActivity)mContext).editMode) return;
                    if (cb_book_mark_list_checker.isChecked()) {
                        cb_book_mark_list_checker.setChecked(false);
                        mData.get(getAdapterPosition()).setDeleteFlag(false);
                    } else {
                        cb_book_mark_list_checker.setChecked(true);
                        mData.get(getAdapterPosition()).setDeleteFlag(true);
                    }

                    ((BookMarkActivity) mContext).showHowManySelected(countChecked());
                    Log.e("name:", mData.get(getAdapterPosition()).getLocation_name());
                    Log.e("dFlag:", mData.get(getAdapterPosition()).isDeleteFlag() + "");
                }
            });
        }
    }

    public void addItem(BookMarkData ipt) {
        mData.add(ipt);
    }

    public void saveBookMarkList(ArrayList<BookMarkData> bookMarkList) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext.getApplicationContext());
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bookMarkList);
        editor.putString("bookMark", json);
        editor.commit();
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

    public void controlEditMode(boolean mode) {
        for (int i = 0; i < mData.size(); i++) {
            mData.get(i).setEditFlag(mode);
        }
    }

    public int countChecked() {
        int cnt = 0;


        for (int i = 0; i < mData.size(); i++) {
            //Log.e("name:", mData.get(i).getLocation_name());
            // Log.e("dFlag:", mData.get(i).isDeleteFlag()+"");
            if (mData.get(i).isDeleteFlag()) {
                cnt += 1;
            }
        }
        return cnt;
    }

    public int deleteChecked() {
        int cnt = 0;
        for (int i = mData.size() - 1; i >= 0; i--) {
            if (mData.get(i).isDeleteFlag()) {
                mData.remove(i);
            }
        }
        initFlag();
        saveBookMarkList(mData);
//        mData = getBookMarkList();
        return cnt;
    }

    public void initFlag() {
        for (int i = 0; i < mData.size(); i++) {
            mData.get(i).setDeleteFlag(false);
            mData.get(i).setEditFlag(false);
        }
    }


}
