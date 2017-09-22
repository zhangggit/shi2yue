package com.bwie.test.bean;

import com.bwie.test.LoggingInterceptor;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * OkHttp工具类
 * Created by zhanggang on 2017/9/22.
 */

public class UrlUtils {
     public static void sendOkHttpRequest(String address, Callback callback){
         OkHttpClient client = new OkHttpClient.Builder()
//                 .addInterceptor(new LoggingInterceptor())
                 .build();

             Request request = new Request.Builder()
                     .url(address)
                     .header("User-Agent","OKHttp Example")
                     .build();
             client.newCall(request).enqueue(callback);
         }
}
