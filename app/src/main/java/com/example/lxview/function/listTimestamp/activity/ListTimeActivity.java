package com.example.lxview.function.listTimestamp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baselib.view.TitleView;
import com.example.lxview.R;
import com.example.lxview.function.listTimestamp.adapter.TimeStampAdapter;
import com.example.lxview.function.listTimestamp.bean.TimeStampBean;

import java.util.ArrayList;

/**
 * author: 李 祥
 * date:   2021/9/27 1:55 下午
 * description: 时间戳列表
 */
public class ListTimeActivity extends AppCompatActivity {


    private ArrayList<TimeStampBean> listData =new ArrayList();
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_timestamp_activity_layout);
        TitleView titleView  =findViewById(R.id.title_view);
        titleView.setCenterTitle("测试测试");
        titleView.setLeftText("返回");
        titleView.setLeftImg(this.getDrawable(R.mipmap.picture_back));
        RecyclerView recycleView = findViewById(R.id.recycler_list);
        TimeStampAdapter timeStampAdapter = new TimeStampAdapter(R.layout.list_timestamp_item,recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(timeStampAdapter);

        listData.add(new TimeStampBean(0,"no 1","qqqq"));
        listData.add(new TimeStampBean(0,"no 2","qqqq"));
        listData.add(new TimeStampBean(0,"no 3","qqqq"));
        listData.add(new TimeStampBean(0,"no 4","qqqq"));
        listData.add(new TimeStampBean(0,"no 5","qqqq"));
        timeStampAdapter.addData(listData);
    }

}
