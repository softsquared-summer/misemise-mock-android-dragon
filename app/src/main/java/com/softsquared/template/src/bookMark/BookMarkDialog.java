package com.softsquared.template.src.bookMark;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.softsquared.template.R;

public class BookMarkDialog {
    private Context context;
    public BookMarkDialog(Context context){
        this.context = context;
    };
    public void callFunction(){
        final Dialog dig = new Dialog(context);
        dig.setContentView(R.layout.mark_dialog);
        Button btn_cancel = dig.findViewById(R.id.btn_book_mark_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dig.cancel();
            }
        });
        dig.show();
    }
}

