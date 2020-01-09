package com.music.musicality.musicality;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

public class MusicService extends Service {

    private MediaPlayer player;
    private String path;

    public MusicService(MediaPlayer player){
        this.player = player;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void setPath(String s){
        path = s;
    }

    public int onStartCommand (Intent intent, int flags, int startId){

        return START_STICKY;
}


    public void onDestroy (){

    }
}
