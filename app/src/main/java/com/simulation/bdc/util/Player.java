package com.simulation.bdc.util;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

public class Player {
    private static final String TAG = "Player";
    static MediaPlayer mediaPlayer = new MediaPlayer();
    /**
     * 播放读音
     * @param url
     */
    public static void play(final String url){
        if(url == null ) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.reset();
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepareAsync(); // prepare自动播放 ;
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            // 装载完毕回调
                            mediaPlayer.start();
                        }
                    });
                }catch (IOException e){
                    Log.d(TAG, "player: " + e);
                }
            }
        }).start();
    }
}
