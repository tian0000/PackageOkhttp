package com.example.dell.packageokhttp.App;

import android.app.Application;

import com.example.dell.packageokhttp.PageOkHttpClient;

/**
 * Created by dell on 2017/9/6.
 */

public class App extends Application {
    private static PageOkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();

        client = PageOkHttpClient.getInstance();

    }

    public static PageOkHttpClient getCLeant() {
        return client;
    }
}
