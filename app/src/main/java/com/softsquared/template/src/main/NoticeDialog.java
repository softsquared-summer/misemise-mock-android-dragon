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

public class NoticeDialog {
    private Context context;


    public NoticeDialog(Context context){
        this.context = context;
    };
    public void callFunction(){
        final Dialog dig = new Dialog(context);
        dig.setContentView(R.layout.notice_dialog);
        Button btn_see_again = dig.findViewById(R.id.btn_see_again);
        Button btn_see_never = dig.findViewById(R.id.btn_see_never);
        btn_see_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dig.cancel();
            }
        });
        btn_see_never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BookMarkData> list = getBookMarkList();
                list.get(0).noticeDialogFlag = false;
                saveBookMarkList(list);
                dig.cancel();
            }
        });
//        dig.show();
    }


    public ArrayList<BookMarkData> getBookMarkList(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("bookMark", "");
        Type type = new TypeToken<ArrayList<BookMarkData>>() {
        }.getType();
        ArrayList<BookMarkData> arrayList = gson.fromJson(json, type);
        if(arrayList == null) arrayList = new ArrayList<>();
        return arrayList;
    }

    public void saveBookMarkList(ArrayList<BookMarkData> bookMarkList){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bookMarkList);
        editor.putString("bookMark", json);
        editor.commit();
    }
}

