package com.baidusdktest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends Activity {

//    Recycle listView;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        listView = findViewById(R.id.listview);
        for (int i=0; i<50; i++) {
            list.add("" + i);
        }
        MyAdapter adapter = new MyAdapter();
//        listView.setAdapter(adapter);
//        StatService.autoTrace(this);
//        StatService.setDebugOn(true);
//        StatService.enableListTrack(listView);
//        StatService.setListName(listView, "test_list");

    }

    private static class MyTextView extends LinearLayout {
        public MyTextView(Context context) {
            super(context);
        }

        public MyTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        public void onViewAdded(View child) {
            super.onViewAdded(child);
        }
    }

    private class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView tv;
            if (view == null) {
                tv = new TextView(RecyclerActivity.this);
            } else {
                tv = (TextView)view;
            }
            tv.setText(list.get(i));
            return tv;
        }
    }


}
