package com.music.musicality.musicality;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.getIntent;

public class MusicService extends Service {

    private static MediaPlayer player = null;
    private static String path;
    private static int post;
    private static String title = null;
    private Intent inter;
    private static List<Song> songList;
    private static int location;

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

    public static String getName(){
        return songList.get(post).getTitle();
    }

    public static int getCurrent(){
        return location;
    }

    public static void setPos(int pos){
        post = pos;
    }

    public static void pauseMusic(){
        player.pause();
    }

    public static void playPrev(){

        player.reset();
        String mPath = songList.get(post).getPath();
        path = mPath;
        try {
            player.setDataSource(mPath);
            player.prepareAsync();
            setUp();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void setSongTime(int x){
        player.seekTo(x);
    }

    public static int getProgress(){
        return player.getCurrentPosition();
    }



    public static void setUp(){
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                post++;
                String mPath = songList.get(post).getPath();
                //if(mp.isPlaying()) {
                    if (post >= songList.size()){
                        post = 0;
                        mPath = songList.get(post).getPath();
                    }
                    else{

                        mPath = songList.get(post).getPath();
                    }
                //}
                path = mPath;
                title = songList.get(post).getTitle();
                mp.reset();
                try {
                    mp.setDataSource(path);
                    mp.prepareAsync();
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer player) {
                           player.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public static void playNext(){

        player.reset();
        path = songList.get(post).getPath();
        try {
            player.setDataSource(path);
            player.prepareAsync();
            setUp();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPlaying(){
        return player.isPlaying();
    }

    public int onStartCommand (Intent intent, int flags, int startId){
        try {

            int match = intent.getIntExtra("state", 0);

            if(player == null){
                player = new MediaPlayer();
                path = intent.getStringExtra("path");
                title = intent.getStringExtra("nameMusic");
                post = intent.getIntExtra("currentPos", 0);
                songList = (ArrayList<Song>)intent.getSerializableExtra("musicList");
                player.setDataSource(path);
                player.prepareAsync();
                setUp();
            }
            else if(match == 1){
                player.reset();
                path = intent.getStringExtra("path");
                title = intent.getStringExtra("nameMusic");
                post = intent.getIntExtra("currentPos", 0);
                songList = (ArrayList<Song>)intent.getSerializableExtra("musicList");
                player.setDataSource(path);
                player.prepareAsync();
                setUp();
            }
            else if(match == 2){
                playNext();
            }
            else if(match == -2){
                playPrev();
            }
            else{
                if(!player.isPlaying())
                    player.start();
            }
            //setUp();


//            if(!player.isPlaying()){
//                player.seekTo(location);
//                player.start();
//            }
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



        player.pause();
        location = player.getCurrentPosition();
        Log.d("PAUSING", "SERVICE CALLED");

    }
}
