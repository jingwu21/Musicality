package com.music.musicality.musicality;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MusicService extends Service {

    @Nullable
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand (Intent intent, int flags, int startId){

    }


    public void onDestroy (){
        
    }
}
