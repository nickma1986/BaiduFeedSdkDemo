package com.baidusdktest;

import android.app.Application;

import com.baidu.mobstat.MtjConfig;
import com.baidu.mobstat.StatService;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StatService.autoTrace(this);
        StatService.start(this);
        StatService.setDebugOn(true);

        StatService.setFeedTrack(MtjConfig.FeedTrackStrategy.TRACK_SINGLE);
    }
}
