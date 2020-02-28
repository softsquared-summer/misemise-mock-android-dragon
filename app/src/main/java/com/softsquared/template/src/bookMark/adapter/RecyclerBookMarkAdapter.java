package com.softsquared.template.src.bookMark.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    private TranslateAnimation animateSlideLeftToRight = new TranslateAnimation(0, 80, 0, 0);
    private TranslateAnimation animateSlideRightToLeft = new TranslateAnimation(0, -120, 0, 0);

    //    Boolean editMode, Boolean animationFlag,
    public RecyclerBookMarkAdapter(ArrayList<BookMarkData> list, Context context) {
        mData = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("onCreateViewHolder", "리사이클러 뷰 로드");
        Context context = parent.getContext();
        // initFlag();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_book_mark_item, parent, false);
        RecyclerBookMarkAdapter.ViewHolder vh = new RecyclerBookMarkAdapter.ViewHolder(view);

        animateSlideLeftToRight.setDuration(500);
        animateSlideLeftToRight.setFillAfter(true);
        animateSlideRightToLeft.setDuration(500);
        animateSlideRightToLeft.setFillAfter(true);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        BookMarkData item = mData.get(position);
        // Log.e("bind", position + " " + holder.tvLocation.getText().toString());

        String curStatus = item.getMise_status();
        holder.tvLocation.setText(item.getLocation_name());
        holder.tvStatus.setText(curStatus);

        if (curStatus.equals("좋음")) {
            holder.rlo_book_mark_image.setBackgroundColor(Color.parseColor("#0277bd"));
            holder.iv_status.setImageResource(R.drawable.ic_smile_1);
        } else if (curStatus.equals("양호")) {
            holder.iv_status.setImageResource(R.drawable.ic_smile_2);
            holder.rlo_book_mark_image.setBackgroundColor(Color.parseColor("#0098a6"));
        } else if (curStatus.equals("보통")) {
            holder.rlo_book_mark_image.setBackgroundColor(Color.parseColor("#398e3d"));
            holder.iv_status.setImageResource(R.drawable.ic_smile_3);
        } else if (curStatus.equals("나쁨")) {
            holder.rlo_book_mark_image.setBackgroundColor(Color.parseColor("#d74315"));
            holder.iv_status.setImageResource(R.drawable.ic_smile_4);
        } else {
            holder.rlo_book_mark_image.setBackgroundColor(Color.parseColor("#000000"));
            holder.iv_status.setImageResource(R.drawable.ic_smile_0);
        }


        // Log.e("onBindViewHolder", position + "");

        Log.e("inAnimation", "isEditFlag[" + position + "] : " + item.isEditFlag());
        if (mData.get(position).isEditFlag() && position != 0) {
            holder.tvLocation.startAnimation(animateSlideLeftToRight);
            holder.cb_book_mark_list_checker.startAnimation(animateSlideLeftToRight);
            holder.iv_upDownChange.startAnimation(animateSlideRightToLeft);
            holder.rlo_book_mark_image.setVisibility(INVISIBLE);
            holder.cb_book_mark_list_checker.setChecked(false);
        } else {
            holder.rlo_book_mark_image.setVisibility(View.VISIBLE);
        }
        if (item.isDeleteFlag() == true) {
            mData.remove(position);
            saveBookMarkList(mData);
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
        ImageView iv_status;

        ViewHolder(final View itemView) {
            super(itemView);
            iv_status = itemView.findViewById(R.id.iv_status_in_book_mark);
            rlo_book_mark_image = itemView.findViewById(R.id.rlo_book_mark_image);
            tvLocation = itemView.findViewById(R.id.tv_book_mark_location);
            tvStatus = itemView.findViewById(R.id.tv_book_mark_status);
            rlo_container = itemView.findViewById(R.id.rlo_container);
            cb_book_mark_list_checker = itemView.findViewById(R.id.cb_book_mark_list_checker);
            iv_upDownChange = itemView.findViewById(R.id.iv_upDownChange);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!((BookMarkActivity) mContext).editMode) {
                        Log.e("onClick", "editMode : " + ((BookMarkActivity) mContext).editMode);
                        return;
                    }
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


    public void controlEditMode(boolean mode) {
        for (int i = 0; i < getItemCount(); i++) {
            mData.get(i).setEditFlag(mode);
            Log.e("mData_editFlag", i + "");
            Log.e("mData_editFlag", mData.get(i).isEditFlag() + "");
        }
        notifyDataSetChanged();
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
