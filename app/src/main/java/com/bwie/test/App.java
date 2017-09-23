package com.bwie.test;

import android.app.Application;
import android.util.Log;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhanggang on 2017/9/22.
 */

public class App extends Application {

    public static OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                        .build();
        ImageLoader.getInstance().init(configuration);

        okHttpClient = new OkHttpClient();
        okHttpClient = okHttpClient.newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new MyLogInterceptor())
                .build();
    }

    //拦截器,可以修改header,可以通过拦截器打印日志
    public class MyLogInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request().newBuilder()
                    .header("shenfen", "chinesse")
                    .build();
            HttpUrl url = request.url();
            String httpUrl = url.url().toString();
            Log.e("TAG", "============" + httpUrl);
            Response response = chain.proceed(request);
            int code = response.code();
            Log.e("TAG", "============response.code() == " + code);
            return response;
        }
    }
    public static OkHttpClient send(){
        return okHttpClient;
    }
}
