package com.simulation.bdc.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PictureBitmap {
    private static final String TAG = "PictureBitmap";
    public static Bitmap getPictureBitMap(String url) {
        URL pictureUrl = null;
        Bitmap bitmap = null;
        try {
            pictureUrl = new URL(url);
        } catch (MalformedURLException e) {
            Log.e(TAG, "getPictureBitMap: " + e);
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) pictureUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "getPictureBitMap: " + e);
        }
        return bitmap;
    }
}
