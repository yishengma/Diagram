package com.example.asus.diagram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by asus on 18-3-31.
 */

public class DiagramListAdapter extends ArrayAdapter<Integer> {
    private Integer[] mHeights;
    private Integer[] mLows;

    public DiagramListAdapter(@NonNull Context context, int resource, @NonNull Integer[] heights, Integer[] lows) {
        super(context, resource, heights);
        mHeights = heights;
        mLows = lows;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//        int nextPosition = position+1;
//        View view  = LayoutInflater.from(getContext()).inflate(R.layout.item_dailydata,parent,false);
//        DiagramView diagramView = view.findViewById(R.id.dv);
//        if (nextPosition<=mLows.length-1){
//            diagramView.draws(mHeights[position],mLows[position],mHeights[nextPosition],mLows[nextPosition],position==mLows.length-2?2:position==0?0:1);
//
//        }
        int nextPosition = position+1;
        ViewHolder viewHolder;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_dailydata, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mDiagramView = view.findViewById(R.id.dv);
        if (nextPosition<=mLows.length-1){
            viewHolder.mDiagramView.draws(mHeights[position],mLows[position],mHeights[nextPosition],mLows[nextPosition],position==mLows.length-2?2:position==0?0:1);

        }
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }



        return view;
    }


    class ViewHolder {
        DiagramView mDiagramView;

    }




}
