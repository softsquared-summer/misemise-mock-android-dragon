package com.softsquared.template.src.bookMark.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.BookMarkData;
import com.softsquared.template.src.bookMark.BookMarkDialog;
import com.softsquared.template.src.bookMark.adapter.RecyclerBookMarkAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BookMarkActivity extends BaseActivity {
    private ImageButton mBtnBack, mIbtn_deleteBookMark;
    public RecyclerBookMarkAdapter mRecyclerBookMarkAdapter;
    private ArrayList<BookMarkData> mAlBookMarkDataList = new ArrayList<BookMarkData>();
    private RecyclerView rvBookMark;
    private TextView mTv_howManySelected;
    private Button mbtnAddBookMark, mEditBookMark;
    public Boolean editMode;
    boolean animationFlag = false;
    public RelativeLayout rlo_deleteDialog;
    int mAnimationMargin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAnimationMargin = 250;
        actList.add(this);
        setContentView(R.layout.activity_book_mark);
        editMode = false;
        mIbtn_deleteBookMark = findViewById(R.id.ibtn_deleteBookMark);
        animationFlag = false;
        mEditBookMark = findViewById(R.id.ibtn_editBookMark);
        mAlBookMarkDataList = getBookMarkList();
        Intent intent = getIntent();
        HashMap<String, String> hashMap = (HashMap<String, String>)intent.getSerializableExtra("hashMap");
        for(int i=0;i<mAlBookMarkDataList.size();i++){
            mAlBookMarkDataList.get(i).setMise_status(hashMap.get(mAlBookMarkDataList.get(i).getLocation_name()));
        }
        saveBookMarkList(mAlBookMarkDataList);



        rvBookMark = findViewById(R.id.rv_bookMarkList);
        mTv_howManySelected = findViewById(R.id.tv_howManySelected);

        mRecyclerBookMarkAdapter = new RecyclerBookMarkAdapter(mAlBookMarkDataList, this);
        rvBookMark.setAdapter(mRecyclerBookMarkAdapter);
        rvBookMark.setLayoutManager(new LinearLayoutManager(this));


         rlo_deleteDialog = findViewById(R.id.rlo_deleteDialog);

        mAlBookMarkDataList = getBookMarkList();
        mRecyclerBookMarkAdapter.notifyDataSetChanged();
        mbtnAddBookMark = findViewById(R.id.btn_add_book_mark);
        mbtnAddBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAlBookMarkDataList.size() > 5){
                    BookMarkDialog bookMarkDialog = new BookMarkDialog(BookMarkActivity.this);
                    bookMarkDialog.callFunction();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), AddBookMarkActivity.class);
                startActivity(intent);
            }
        });
        mBtnBack = findViewById(R.id.ibtn_backBookMarkToMain);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mIbtn_deleteBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerBookMarkAdapter.deleteChecked();
                editMode = false;
                mEditBookMark.setText("편집");
                mRecyclerBookMarkAdapter.controlEditMode(false);
                mRecyclerBookMarkAdapter.notifyDataSetChanged();
                Log.e("deleteChecked", "실행은함");
            }
        });

        mEditBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerBookMarkAdapter.initFlag();
                if (animationFlag) {
                    animationFlag = false;
                }
                if (editMode) {
                    editMode = false;
                    mEditBookMark.setText("편집");
                    mRecyclerBookMarkAdapter.controlEditMode(false);
                } else {
                    editMode = true;
                    mEditBookMark.setText("완료");
                    mRecyclerBookMarkAdapter.controlEditMode(true);
                }
                Log.e("mEditBookMark",Boolean.toString(editMode));
                mRecyclerBookMarkAdapter.notifyDataSetChanged();
            }
        });
    }

    public  void showHowManySelected(int cnt){
        if(cnt == 0){
            finishAnimation(rlo_deleteDialog);
            animationFlag = false;
        }else{
            if(!animationFlag){
                delAnimation(rlo_deleteDialog);

                animationFlag = true;
            }
        }
        mTv_howManySelected.setText(Integer.toString(cnt)+"개 선택됨");

    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerBookMarkAdapter.notifyDataSetChanged();
    }

    public void delAnimation(final RelativeLayout rlo){
        rlo.clearAnimation();
        TranslateAnimation translateAnimationDtoU= new TranslateAnimation(0, 0, 0, -mAnimationMargin);
        translateAnimationDtoU.setDuration(400);
        translateAnimationDtoU.setFillAfter(true);

        translateAnimationDtoU.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                final int left = rlo.getLeft();
                final int top = rlo.getTop();
                final int right = rlo.getRight();
                final int bottom = rlo.getBottom();
                rlo.layout(left, top - mAnimationMargin, right, bottom - mAnimationMargin);
                rlo.clearAnimation();
                Log.e("레이아웃", "left " + left + " top " + top + " right " + right + " bottom " + bottom);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rlo.startAnimation(translateAnimationDtoU);
    }
    public void finishAnimation(final RelativeLayout rlo){
        rlo.clearAnimation();
        TranslateAnimation translateAnimationUtoD= new TranslateAnimation(0, 0, 0, +mAnimationMargin);
        translateAnimationUtoD.setDuration(400);
        translateAnimationUtoD.setFillAfter(true);

        translateAnimationUtoD.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                final int left = rlo.getLeft();
                final int top = rlo.getTop();
                final int right = rlo.getRight();
                final int bottom = rlo.getBottom();
                rlo.layout(left, top + mAnimationMargin, right, bottom + mAnimationMargin);
                rlo.clearAnimation();
                Log.e("레이아웃", "left " + left + " top " + top + " right " + right + " bottom " + bottom);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rlo.startAnimation(translateAnimationUtoD);
    }
}
