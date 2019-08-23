package com.baidusdktest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_listview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_recyclerview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_recyclerview_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RecyclerFragmentActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_listview_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ListViewFragmentActivity.class);
                startActivity(intent);
            }
        });
    }



}
