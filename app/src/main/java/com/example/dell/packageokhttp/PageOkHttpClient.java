package com.example.dell.packageokhttp;


import android.os.Handler;
import android.os.Looper;

import com.example.dell.packageokhttp.request.GetBuilderRequest;
import com.example.dell.packageokhttp.request.PostBuilderRequest;

import java.util.List;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

/**
 * Created by dell on 2017/9/6.
 */

public class PageOkHttpClient {
    private static OkHttpClient mOkHttpClient;
    private static PageOkHttpClient client = new PageOkHttpClient();
    //用来更新ui界面
    public static Handler handler = new Handler(Looper.getMainLooper());

    public static PageOkHttpClient getInstance() {
        mOkHttpClient = new OkHttpClient();
        return client;
    }

    public static PageOkHttpClient getInstance(OkHttpClient okHttpClient) {
        if (okHttpClient != null) {
            synchronized (PageOkHttpClient.class) {
                if (okHttpClient == null) {
                    mOkHttpClient = new OkHttpClient();
                } else {
                    mOkHttpClient = okHttpClient;
                }
            }
        } else {
            mOkHttpClient = new OkHttpClient();
        }
        return client;
    }

    /**
     * get请求
     *
     * @return
     */
    public GetBuilderRequest get() {
        return new GetBuilderRequest(mOkHttpClient);
    }

    /**
     * post请求
     *
     * @return
     */
    public PostBuilderRequest post() {
        return new PostBuilderRequest(mOkHttpClient);
    }


    public void cancle(Object tag) {
        Dispatcher dispatcher = mOkHttpClient.dispatcher();
        List<Call> calls = dispatcher.runningCalls();
        List<Call> calls1 = dispatcher.queuedCalls();
        for (Call call : calls) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
                return;
            }
        }
        for (Call call : calls1) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
                return;
            }
        }
    }

}
