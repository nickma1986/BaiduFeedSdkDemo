package com.baidusdktest;

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
import android.widget.TextView;

import com.baidu.mobstat.StatService;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFragmentActivity extends FragmentActivity {


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
        RecyclerView recyclerView;
        List<String> list = new ArrayList<>();
        public MyFragment () {

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            return super.onCreateView(inflater, container, savedInstanceState);
            View contentView = inflater.inflate(R.layout.activity_recycler, container, false);
            recyclerView = contentView.findViewById(R.id.listview);
            for (int i=0; i<50; i++) {
                list.add("" + i);
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
            linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
            MyAdapter adapter = new MyAdapter(list);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
            recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), OrientationHelper.VERTICAL));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                @Override
                public void onChildViewAttachedToWindow(@NonNull View view) {
                    Log.e("_tag", "+++ " + ((TextView)view.findViewById(R.id.title)).getText());
                }

                @Override
                public void onChildViewDetachedFromWindow(@NonNull View view) {
                    Log.e("_tag", "--- " + ((TextView)view.findViewById(R.id.title)).getText());
                }
            });
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });
            adapter.notifyDataSetChanged();
            return contentView;
        }

        public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

            public static class MyHolder extends RecyclerView.ViewHolder {

                public final TextView tvTitle;
                public final TextView tvName;

                public MyHolder(@NonNull View itemView) {
                    super(itemView);
                    tvTitle = itemView.findViewById(R.id.title);
                    tvName = itemView.findViewById(R.id.name);
                }
            }
            private List<String> mDatas = new ArrayList<>();

            public MyAdapter (List<String> list) {
                mDatas.addAll(list);
            }

            @NonNull
            @Override
            public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
                return new MyHolder(v);
            }

            @Override
            public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
                myHolder.tvTitle.setText("RecyclerViewFragment " + mDatas.get(i));

                myHolder.tvName.setText("Name:RecyclerView " + mDatas.get(i));
                StatService.setContentTitle(myHolder.itemView, myHolder.tvTitle.getText().toString());
            }

            @Override
            public int getItemCount() {
                return mDatas.size();
            }
        }


    }

}
