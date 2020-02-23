package com.softsquared.template.src.bookMark.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.softsquared.template.R;

public class AddBookMarkActivity extends AppCompatActivity {
    Button mAddBookMarkLoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_mark);
        mAddBookMarkLoc = findViewById(R.id.btn_add_book_mark_loc);

        mAddBookMarkLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText loc = findViewById(R.id.et_input_loc);
                String locStr = loc.getText().toString();

            }
        });
    }
}
