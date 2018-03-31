package com.example.asus.diagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   private RecyclerView mDiagramRecyclerView;
   private int[] mHeightInts;
   private int[] mLowInts;
   private DiagramAdapter mDiagramAdapter;
//    private DiagramListAdapter mAdapter;
//    private HorizontalListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initView(){
        mDiagramRecyclerView = findViewById(R.id.rv_diagram);
        mDiagramAdapter = new DiagramAdapter(mHeightInts,mLowInts);
        mDiagramRecyclerView.setAdapter(mDiagramAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mDiagramRecyclerView.setLayoutManager(manager);
//        mListView = findViewById(R.id.rv_diagram);
//        mAdapter = new DiagramListAdapter(this,R.layout.item_dailydata,mHeightInts,mLowInts);
//        mListView.setAdapter(mAdapter);
//        mListView.getChildAt(0);


    }

    private void initData(){
        mHeightInts = new int[]{270,280,280,290,300,290,290,240,220,260,280,300,290,290,280};
        mLowInts = new int[]   {200,190,200,210,210,220,160,170,140,160,190,200,200,210,180};
        for (int i=0;i<mLowInts.length;i++){
            mLowInts[i] = 800 - mLowInts[i];
        }
//        mHeightInts = new int[]{100,100,100,100,100,100,100,100,100,100,100,100,100,100};
//        mLowInts = new int[]{600,600,600,600,600,600,600,600,600,600,600,600,600,600};

    }
}
