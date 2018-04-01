package com.example.asus.diagram;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DiagramAdapter extends RecyclerView.Adapter<DiagramAdapter.ViewHolder> {
    private int[] mHeight;
    private int[] mLows;
    private int times ;

    public DiagramAdapter(int[] height, int[] low) {
        mHeight = height;
        mLows = low;
        caculateTimes();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dailydata, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //设置RecyclerView 的这个holder 不复用
        holder.setIsRecyclable(false);
        int prePosition = position - 1;
        int nextPosition = position + 1;

        //这里默认显示15天的数据
        switch (position) {
            case 0:
                holder.mDiagramView.draws(times*mHeight[position], times*mLows[position], times*mHeight[nextPosition], times*mLows[nextPosition], 0);
                holder.itemView.setBackgroundResource(R.drawable.drawableBackground);
                break;
            case 14:
                holder.mDiagramView.draws(times*mHeight[prePosition], times*mLows[prePosition], times*mHeight[position], times*mLows[position], 2, true);

                break;
            default:
                holder.mDiagramView.draws(times*mHeight[prePosition],times* mLows[prePosition],times* mHeight[position],times* mLows[position], times*mHeight[nextPosition], times*mLows[nextPosition], 1);

                break;

        }
        holder.mDiagramView.setText(mHeight[position],mLows[position]);



    }

    @Override
    public int getItemCount() {
        return mHeight.length;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        DiagramView mDiagramView;

        public ViewHolder(View itemView) {
            super(itemView);
            mDiagramView = itemView.findViewById(R.id.dv);
        }

    }

    /**
     * 为了让温差大小不同的曲线都能显示并且显示的效果不会相差太大
     * 这里对数据进行了不同倍数的放大，差别大的放大倍数小，差别晓得放大倍数大
     */

    private void caculateTimes(){
        int max =mHeight[0];
        int min = mLows[0];
        for (int i=1;i<mHeight.length;i++){
            if (mHeight[i]>max){
                max = mHeight[i];
            }
            if (mHeight[i]<min){
                min = mHeight[i];
            }
        }
        int difference = max-min;
        if (difference<=10&&difference>5){
            times = 5;
        }else if (difference<=5&&difference>=3){
            times = 7;
        }else if (difference<3){
            times = 10;
        }else if (difference>10&&difference<=13){
            times = 3;
        }else {
            times = 1;
        }

    }


}
