package com.example.dell.packageokhttp.request;

import com.example.dell.packageokhttp.callback.MyCallBack;
import com.example.dell.packageokhttp.response.IResponse;

import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by dell on 2017/9/6.
 */

public class GetBuilderRequest extends RequestBuilldParams<GetBuilderRequest> {
    private OkHttpClient okHttpClient;

    public GetBuilderRequest(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public  void enqueue(IResponse iResponse) {
        Request.Builder builder = new Request.Builder();
        if (url == null) {
            throw new RuntimeException("网络地址不可为空");
        }

        if (tag != null) {
            //tag标记  用来结束网络请求
            builder.tag(tag);
        }
        //添加请求参数
        if (params != null && !params.isEmpty()) {
            builder.url(appendParams(url, params));
        } else {
            builder.url(url);
        }


        //添加请求头
        appendHeads(builder, headrs);


        //发送网络请求
        okHttpClient.newCall(builder.build()).enqueue(new MyCallBack(iResponse));
    }

    /**
     * 平接参数
     *
     * @param url    网络地址
     * @param params 参数集合
     */
    private String appendParams(String url, Map<String, String> params) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(url + "?");
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            stringBuffer.append(key)
                    .append("=")
                    .append(params.get(key))
                    .append("&");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }
}
