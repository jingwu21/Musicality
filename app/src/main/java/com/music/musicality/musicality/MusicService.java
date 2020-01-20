package com.music.musicality.musicality;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.List;

import static android.content.Intent.getIntent;

public class MusicService extends Service {

    private MediaPlayer player = null;
    private String path;
    private int post;
    private List<Song> songList;

//    public MusicService(MediaPlayer player){
//        this.player = player;
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }

    public void setPath(String s, int pos){
        path = s;
        post = pos;
    }

    public void setPos(int pos){
        post = pos;
    }

    public void playPrev(){
        player.reset();
        String mPath = songList.get(post).getPath();
        try {
            player.setDataSource(path);
            player.prepareAsync();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void playNext(){
        player.reset();
        String mPath = songList.get(post).getPath();
        try {
            player.setDataSource(path);
            player.prepareAsync();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int onStartCommand (Intent intent, int flags, int startId){
        try {
            if(player == null){
                player = new MediaPlayer();
                path = MusicPlayerActivity.path;
                player.setDataSource(path);
                player.prepareAsync();
            }

            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                     player.start();
                }
            });
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    String mPath = songList.get(post).getPath();
                    if(mp.isPlaying()) {
                        if (post >= songList.size()){
                            post = 0;
                            mPath = songList.get(post).getPath();
                        }
                        else{
                            post = post + 1;
                            mPath = songList.get(post).getPath();
                        }
                    }
                    mp.reset();
                    try {
                        mp.setDataSource(path);
                        mp.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
//            player.setDataSource(path);
//            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return START_STICKY;
}
    public class MusicServiceBind extends Binder{
        public MusicService getBinder(){
            return MusicService.this;
        }
    }

    public void setSongList(List<Song> songList, int currPos){
        this.songList = songList;
        post = currPos;
    }

    public void onDestroy (){
        super.onDestroy();

        player.stop();
    }
}
