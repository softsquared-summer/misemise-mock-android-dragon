package com.softsquared.template.src.sidebar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.softsquared.template.R;

public class SideSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton mIbtnBack;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_setting);
        mIbtnBack = findViewById(R.id.btn_backSettingToMain);
        mIbtnBack.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
