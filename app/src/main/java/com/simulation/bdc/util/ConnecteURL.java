package com.simulation.bdc.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ConnecteURL {

    private static final String TAG = "ConnecteURL";

    /**
     * 访问后台服务单，并返回服务端传过来的json数据
     *
     * @param url
     * @return
     */
    public String connecteUrl(String url) {
        String responseData = null;
        if (url != null) {
            try {
                OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build();
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                responseData = response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseData;
    }
}
