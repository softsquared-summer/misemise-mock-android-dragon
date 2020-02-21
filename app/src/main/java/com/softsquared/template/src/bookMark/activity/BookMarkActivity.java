package com.softsquared.template.src.bookMark.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.softsquared.template.R;
import com.softsquared.template.src.bookMark.adapter.RecyclerBookMarkAdapter;
import com.softsquared.template.src.main.items.BookMarkItem;

import java.util.ArrayList;

public class BookMarkActivity extends AppCompatActivity {
    private ImageButton mBtnBack;
    private RecyclerBookMarkAdapter mRecyclerBookMarkAdapter;
    private ArrayList<BookMarkItem> mAlBookMarkList = new ArrayList<BookMarkItem>();
    private RecyclerView rvBookMark;
    private Button mbtnAddBookMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_mark);


        rvBookMark = findViewById(R.id.rv_bookMarkList);
        mRecyclerBookMarkAdapter = new RecyclerBookMarkAdapter(mAlBookMarkList);
        rvBookMark.setAdapter(mRecyclerBookMarkAdapter);
        rvBookMark.setLayoutManager(new LinearLayoutManager(this));
        for(int i=0;i<3;i++){
            mRecyclerBookMarkAdapter.addItem(new BookMarkItem("지역", "상태"));
        }

        mRecyclerBookMarkAdapter.notifyDataSetChanged();
        mbtnAddBookMark = findViewById(R.id.btn_add_book_mark);
        mbtnAddBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }
}
