package com.trojx.llogink;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by Administrator on 2016/6/3.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AVOSCloud.initialize(this, "rNBtbElJSa6TT46tEU6TBSl9-gzGzoHsz", "Cn4CUvSXkzjpMliR5zd0oLdX");
    }
}
