package com.example.lxview.function.listswip.listviewdeletedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lxview.R;

/***
 *
 * ListView+ExpandableListView 侧滑删除
 *
 * @author zhangqie
 *
 */
public class ListSwipMainActivity extends Activity implements View.OnClickListener {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button1:
                intent=new Intent(ListSwipMainActivity.this,ActivityListViewDelete.class);
                break;
            case R.id.button2:
                intent=new Intent(ListSwipMainActivity.this,ActivityExpandableListViewDelete.class);
                break;

            default:
                break;
        }
        startActivity(intent);
    }
}

