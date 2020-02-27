package com.softsquared.template.src.main;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.softsquared.template.R;
import com.softsquared.template.src.BookMarkData;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AlarmDialog {
    private Context context;
    public AlarmDialog(Context context){
        this.context = context;
    };
    public void callFunction(){
        final Dialog dig = new Dialog(context);
        dig.setContentView(R.layout.alarm_dialog);
        Button btn_cancel = dig.findViewById(R.id.btn_alarm_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dig.cancel();
            }
        });
        dig.show();
    }
}

