package com.baidusdktest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mobstat.StatService;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends Activity {

    ListView listView;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView = findViewById(R.id.listview);
        StatService.setListName(listView, "test_list_2");
        for (int i=0; i<50; i++) {
            list.add("ListView " + i);
        }
        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.setRecyclerListener(new AbsListView.RecyclerListener() {
            @Override
            public void onMovedToScrapHeap(View view) {
                if (view instanceof TextView) {
                    Log.e("_tag", "--- " + ((TextView)view).getText());
                }
            }
        });
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
            View tv;
            if (view == null) {
                tv = LayoutInflater.from(ListViewActivity.this).inflate(R.layout.list_item_view, viewGroup, false);
            } else {
                tv = view;
            }
            ((TextView)tv.findViewById(R.id.name)).setText("Name:" + list.get(i));
            StatService.setContentTitle(tv, "listview " + list.get(i));
            Log.e("_tag", "+++ " + list.get(i));
            return tv;
        }
    }


}
