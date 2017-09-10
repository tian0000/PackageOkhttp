package com.example.dell.packageokhttp.request;

import com.example.dell.packageokhttp.callback.MyCallBack;
import com.example.dell.packageokhttp.response.IResponse;

import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by dell on 2017/9/6.
 */

public class PostBuilderRequest extends RequestBuilldParams<PostBuilderRequest> {
    private OkHttpClient okHttpClient;

    public PostBuilderRequest(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }


    @Override
    public void enqueue(IResponse iResponse) {
        Request.Builder builder = new Request.Builder();
        if (url == null) {
            throw new RuntimeException("网络地址不可为空");
        }

        if (tag != null) {
            //tag标记  用来结束网络请求
            builder.tag(tag);
        }
        //添加请求头
        appendHeads(builder, headrs);
        //添加请求参数
        FormBody.Builder formBody = new FormBody.Builder();
        if (params != null && !params.isEmpty()) {
            appendParams(formBody, params);
        }
        builder.post(formBody.build());

        okHttpClient.newCall(builder.build())
                .enqueue(new MyCallBack(iResponse));
    }

    private void appendParams(FormBody.Builder builder, Map<String, String> params) {
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            builder.add(key, params.get(key));
        }
    }
}
