package com.baidusdktest;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.baidu.mobstat.MtjConfig;
import com.baidu.mobstat.StatService;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StatService.autoTrace(this);
        StatService.setFeedTrack(MtjConfig.FeedTrackStrategy.TRACK_SINGLE);
        StatService.setDebugOn(true);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
