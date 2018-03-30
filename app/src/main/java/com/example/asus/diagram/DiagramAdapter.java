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
    private Map<Integer,Integer> mMap;

    public DiagramAdapter(Map<Integer, Integer> map) {
        mMap = map;




    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dailydata,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         holder.mDiagramView.draws(50,100);



    }

    @Override
    public int getItemCount() {
        return mMap.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        DiagramView mDiagramView ;
        public ViewHolder(View itemView) {
            super(itemView);
            mDiagramView = itemView.findViewById(R.id.dv);
        }
    }


}
