package com.simulation.lab.bdc.util;

import java.util.concurrent.TimeUnit;


public class ConnecteURL {

    private static final String TAG = "ConnecteURL";

    /**
     * 访问后台服务单，并返回服务端传过来的json数据
     *
     * @param url
     * @return
     */
    public String getJosnString(String url) {
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
