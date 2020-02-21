package com.softsquared.template.src.bookMark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.template.R;
import com.softsquared.template.src.main.items.BookMarkItem;

import java.util.ArrayList;

public class RecyclerBookMarkAdapter extends RecyclerView.Adapter<RecyclerBookMarkAdapter.ViewHolder> {

    private ArrayList<BookMarkItem> mData = null;


    public RecyclerBookMarkAdapter(ArrayList<BookMarkItem> list){
        mData = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_book_mark_item, parent, false);
        RecyclerBookMarkAdapter.ViewHolder vh = new RecyclerBookMarkAdapter.ViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookMarkItem item = mData.get(position);

        holder.tvLocation.setText(item.getLocation());
        holder.tvStatus.setText(item.getStatus());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvLocation, tvStatus;

        ViewHolder(View itemView) {
            super(itemView) ;
            tvLocation = itemView.findViewById(R.id.tv_book_mark_location) ;
            tvStatus = itemView.findViewById(R.id.tv_book_mark_status) ;
        }
    }
    public void addItem(BookMarkItem ipt){
        mData.add(ipt);
    }
}
