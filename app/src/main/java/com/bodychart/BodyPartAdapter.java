package com.bodychart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bodychart.databinding.ViewBodyChartListBinding;

import java.util.List;

public class BodyPartAdapter extends RecyclerView.Adapter<BodyPartAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<BodyChartInfo> mChartInfos;
    private final OnBodyPartListener mOnBodyPartListener;

    public BodyPartAdapter(Context mContext, List<BodyChartInfo> mChartInfos, OnBodyPartListener mOnBodyPartListener) {
        this.mContext = mContext;
        this.mChartInfos = mChartInfos;
        this.mOnBodyPartListener = mOnBodyPartListener;
    }

    @NonNull
    @Override
    public BodyPartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewBodyChartListBinding binding = ViewBodyChartListBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new BodyPartAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyPartAdapter.MyViewHolder holder, int position) {
        BodyChartInfo bodyChartInfo = mChartInfos.get(holder.getAdapterPosition());

        holder.binding.txtBodyPartName.setText(bodyChartInfo.getBodyPartName());
        holder.binding.txtNumberOfPain.setText(bodyChartInfo.getNumberOfPain());
        holder.binding.imgRemove.setOnClickListener(view -> {
            mOnBodyPartListener.onRemoveClick(bodyChartInfo);
        });
    }


    @Override
    public int getItemCount() {
        return mChartInfos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ViewBodyChartListBinding binding;

        public MyViewHolder(ViewBodyChartListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
