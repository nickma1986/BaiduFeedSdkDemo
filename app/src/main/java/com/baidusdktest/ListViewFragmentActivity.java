package com.baidusdktest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mobstat.StatService;

import java.util.ArrayList;
import java.util.List;

public class ListViewFragmentActivity extends FragmentActivity {


    Fragment[] fragments = new Fragment[2];
    ViewPager pager;
    private FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int i) {
            return fragments[i];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_fragment);
        fragments[0] = new MyFragment();
        fragments[1] = new MyFragment();
        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public static class MyFragment extends Fragment {
        ListView recyclerView;
        List<String> list = new ArrayList<>();
        public MyFragment () {

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            return super.onCreateView(inflater, container, savedInstanceState);
            View contentView = inflater.inflate(R.layout.activity_listview, container, false);
            recyclerView = contentView.findViewById(R.id.listview);
            for (int i=0; i<50; i++) {
                list.add("ListViewFragment" + i);
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
            linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
            MyAdapter adapter = new MyAdapter();
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            return contentView;
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
                    tv = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_view, viewGroup, false);
                } else {
                    tv = view;
                }
                ((TextView)tv.findViewById(R.id.name)).setText("FragmentName:" + list.get(i));

                StatService.setContentTitle(tv, "ListViewFragment " + list.get(i));
                Log.e("_tag", "+++ " + list.get(i));
                return tv;
            }
        }



    }

}
