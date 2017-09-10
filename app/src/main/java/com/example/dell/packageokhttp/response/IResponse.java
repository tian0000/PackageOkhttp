package com.example.dell.packageokhttp.response;

import okhttp3.Response;

/**
 * Created by dell on 2017/9/6.
 */

public interface IResponse {
    /**
     * 成功
     * @param response
     */
    void onSuccess(Response response);

    /**
     * 失败
     * @param message
     */
    void onFailure(String message);

}
