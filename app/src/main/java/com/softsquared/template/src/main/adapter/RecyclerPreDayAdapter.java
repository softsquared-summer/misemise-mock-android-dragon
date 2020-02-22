package com.softsquared.template.src.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.template.R;
import com.softsquared.template.src.main.items.PreDayItem;

import java.util.ArrayList;

public class RecyclerPreDayAdapter extends RecyclerView.Adapter<RecyclerPreDayAdapter.ViewHolder> {

    private ArrayList<PreDayItem> mData = null;

    RecyclerPreDayAdapter(ArrayList<PreDayItem> list) {
        mData = list;
    }

    @Override
    public RecyclerPreDayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_daily_pre, parent, false);
        RecyclerPreDayAdapter.ViewHolder vh = new RecyclerPreDayAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerPreDayAdapter.ViewHolder holder, int position) {
        PreDayItem item = mData.get(position);

        holder.tv_day.setText(item.getDay());
        holder.tv_when.setText(item.getWhen());
        holder.tv_state.setText(item.getState());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_day, tv_when, tv_state;

        ViewHolder(View itemView) {
            super(itemView);
            tv_day = itemView.findViewById(R.id.tv_day);
            tv_when = itemView.findViewById(R.id.tv_when);
            tv_state = itemView.findViewById(R.id.tv_state);
        }
    }

    public void addItem(PreDayItem ipt) {
        mData.add(ipt);
    }
}
