package com.softsquared.template.src.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.template.R;
import com.softsquared.template.src.main.items.PreTimeItem;

import java.util.ArrayList;

public class RecyclerPreTimeAdapter extends RecyclerView.Adapter<RecyclerPreTimeAdapter.ViewHolder> {
    private ArrayList<PreTimeItem> mData = null;

    RecyclerPreTimeAdapter(ArrayList<PreTimeItem> list) {
        mData = list;
    }

    @Override
    public RecyclerPreTimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_time_pre, parent, false);
        RecyclerPreTimeAdapter.ViewHolder vh = new RecyclerPreTimeAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerPreTimeAdapter.ViewHolder holder, int position) {
        PreTimeItem item = mData.get(position);

        holder.tv_time.setText(item.getTime());
        holder.tv_state.setText(item.getState());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_time, tv_state;

        ViewHolder(View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_timePreTime);
            tv_state = itemView.findViewById(R.id.tv_TimePreState);
        }
    }

    public void addItem(PreTimeItem ipt) {
        mData.add(ipt);
    }
}
