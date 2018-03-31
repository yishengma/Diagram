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



    }

    private void initData(){
        mHeightInts = new int[]{27,28,28,29,30,29,29,24,22,26,28,30,29,29,28};
        mLowInts = new int[]{20,19,20,21,21,22,16,17,14,16,19,20,20,21,18};


    }


}
