package com.example.lxview.listTimestamp.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lxview.R;
import com.example.lxview.listTimestamp.adapter.TimeStampAdapter;

/**
 * author: 李 祥
 * date:   2021/9/27 1:55 下午
 * description:
 */
public class ListTimeActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.list_timestamp_activity_layout);
        RecyclerView recycleView = findViewById(R.id.recycler_list);
        TimeStampAdapter timeStampAdapter = new TimeStampAdapter(R.layout.list_timestamp_item,recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(timeStampAdapter);
    }

}
