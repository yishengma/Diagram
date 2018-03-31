package com.example.asus.diagram;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

/**
 * Created by asus on 18-3-30.
 */

public class DiagramAdapter extends RecyclerView.Adapter<DiagramAdapter.ViewHolder> {
    private int[] mHeight;
    private int[] mLows;

    public DiagramAdapter(int[] height, int[] low) {
        mHeight = height;
        mLows = low;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dailydata, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        int prePosition = position - 1;
        int nextPosition = position + 1;
        switch (position) {
            case 0:
                holder.mDiagramView.draws(mHeight[position], mLows[position], mHeight[nextPosition], mLows[nextPosition], 0);
                break;
            case 14:
                holder.mDiagramView.draws(mHeight[prePosition], mLows[prePosition], mHeight[position], mLows[position], 2, true);
                break;
            default:
                holder.mDiagramView.draws(mHeight[prePosition], mLows[prePosition], mHeight[position], mLows[position], mHeight[nextPosition], mLows[nextPosition], 1);
                break;

        }


    }

    @Override
    public int getItemCount() {
        return mHeight.length - 1;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        DiagramView mDiagramView;

        public ViewHolder(View itemView) {
            super(itemView);
            mDiagramView = itemView.findViewById(R.id.dv);
        }

    }


}
