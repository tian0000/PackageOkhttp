package com.example.dell.packageokhttp.response;

import com.example.dell.packageokhttp.PageOkHttpClient;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by dell on 2017/9/6.
 */

public abstract class GsonIResponse<T> implements IResponse {

    private final Type type;

    public GsonIResponse() {
        //这里获取当前泛型的字节码类型
        Type genericSuperclass = getClass().getGenericSuperclass();
        /**获取到类型以后 强转成reflect包下的  ParameterizedType类型
         * 里面包含了我当前所有泛型的数组
         * 如果当前类只需要一个泛型
         * 可以直接写0
         */
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        /*
        调用Gson内置type解析器
         */
        type = $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }

    @Override
    public void onSuccess(Response response) {
        String bodyString = "";
        try {
            bodyString = response.body().string();
        } catch (final IOException e) {
            e.printStackTrace();
            PageOkHttpClient.handler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure("读取失败------" + e.getMessage());
                }
            });

        } finally {
            response.code();
        }
        final String finalBodyString = bodyString;
        PageOkHttpClient.handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                onSyccess((T) gson.fromJson(finalBodyString, type));
            }
        });

    }

    protected abstract void onSyccess(T response);


}
