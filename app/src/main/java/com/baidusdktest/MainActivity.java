package com.baidusdktest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mobstat.MtjConfig;
import com.baidu.mobstat.StatService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ListView listView;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        StatService.enableListTrack(listView);
        StatService.setListName(listView, "test_list");
        for (int i=0; i<50; i++) {
            list.add("abc " + i);
        }
        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.setRecyclerListener(new AbsListView.RecyclerListener() {
            @Override
            public void onMovedToScrapHeap(View view) {

            }
        });
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
                tv = new TextView(MainActivity.this);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                tv = (TextView)view;
            }
            StatService.setContentTitle(tv, list.get(i));
            tv.setText(list.get(i));
            return tv;
        }
    }


}
