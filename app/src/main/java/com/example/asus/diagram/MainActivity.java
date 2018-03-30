package com.example.asus.diagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   private RecyclerView mDiagramRecyclerView;
   private Map<Integer,Integer> mDataMap;
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
        mDiagramAdapter = new DiagramAdapter(mDataMap);
        mDiagramRecyclerView.setAdapter(mDiagramAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mDiagramRecyclerView.setLayoutManager(manager);

    }

    private void initData(){
        mDataMap = new HashMap<>();
        for (int i=0;i<15;i++){
            mDataMap.put(i,i);
        }
    }
}
