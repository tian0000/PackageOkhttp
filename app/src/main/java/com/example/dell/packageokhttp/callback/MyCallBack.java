package com.example.dell.packageokhttp.callback;

import com.example.dell.packageokhttp.PageOkHttpClient;
import com.example.dell.packageokhttp.response.IResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by dell on 2017/9/6.
 */

public class MyCallBack implements Callback {
    private IResponse iResponse;

    public MyCallBack(IResponse response) {
        this.iResponse = response;
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        PageOkHttpClient.handler.post(new Runnable() {
            @Override
            public void run() {
                iResponse.onFailure("failure-----" + e.getMessage());
            }
        });
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        if (response.isSuccessful()) {
            iResponse.onSuccess(response);
        } else {
            PageOkHttpClient.handler.post(new Runnable() {
                @Override
                public void run() {
                    iResponse.onFailure("failure-----" + response.code());
                }
            });
        }
    }
}
