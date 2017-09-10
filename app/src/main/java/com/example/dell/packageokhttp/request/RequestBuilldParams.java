package com.example.dell.packageokhttp.request;

import com.example.dell.packageokhttp.response.IResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.Request;

/**
 * Created by dell on 2017/9/6.
 */

public abstract class RequestBuilldParams<T extends RequestBuilldParams> {
    //存放网络地址的url
    protected String url;

    //请求头的集合
    protected Map<String, String> params;

    //添加请求参数的集合

    protected Map<String, String> headrs;

    protected Object tag;

    /**
     * 添加网络地址
     *
     * @param url 网路地址字符串
     * @return
     */
    public T url(String url) {
        this.url = url;
        return (T) this;
    }

    /**
     * 添加请求头
     *
     * @param key   请求头对应key值
     * @param value 请求头对应 value值
     * @return
     */
    public T head(String key, String value) {
        if (headrs == null) {
            headrs = new HashMap<>();
        }
        headrs.put(key, value);
        return (T) this;
    }

    /**
     * 添加请求头集合  集合不可以为空
     *
     * @param heads 集合
     * @return
     */
    public T addHeads(Map<String, String> heads) {
        if (heads == null) {
            throw new RuntimeException("禁止传入空集合");
        }
        headrs = heads;
        return (T) this;
    }

    /**
     * 添加请求参数
     *
     * @param key   参数对应key值
     * @param value 参数对应value
     * @return
     */
    public T params(String key, String value) {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(key, value);
        return (T) this;
    }

    /**
     * 添加请求参数集合 禁止为空
     *
     * @param map 集合
     * @return
     */
    public T addParams(Map<String, String> map) {
        if (map == null) {
            throw new RuntimeException("禁止传入空集合");
        }
        params = map;
        return (T) this;
    }

    /**
     * 添加请求tag 用户结束网络请求
     *
     * @param tag tag标记
     * @return
     */
    public T tag(Objects tag) {
        this.tag = tag;
        return (T) this;
    }

    /**
     * 异步回调
     *
     * @param iResponse
     */
    public abstract void enqueue(IResponse iResponse);


    /**
     * 添加请求头
     *
     * @param builder 添加请求头Request
     * @param headrs  参数
     */
    protected void appendHeads(Request.Builder builder, Map<String, String> headrs) {
        if (headrs != null && !headrs.isEmpty()) {
            Headers.Builder headBuilder = new Headers.Builder();
            Set<String> keySet = headrs.keySet();
            for (String s : keySet) {
                //添加请求头
                headBuilder.add(s, headrs.get(s));
            }
            builder.headers(headBuilder.build());
        }
    }

}
