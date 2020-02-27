package com.softsquared.template.src.sidebar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.softsquared.template.R;

public class SideEightStage extends AppCompatActivity {
    ImageButton mIbtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_eight_stage);
        mIbtnBack = findViewById(R.id.btn_backEightToMain);
        mIbtnBack.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
