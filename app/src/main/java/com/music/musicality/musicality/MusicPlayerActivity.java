package com.music.musicality.musicality;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayerActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton playButton, prevButton, nextButton;
    private SeekBar playBar;
    private TextView musicTitle;
    private MediaPlayer player;
    private MusicService musicService;
    private String title;
    private String path;
    private int size;
    private Handler handler;
    private List<Song> songList;
    private Runnable runnable;
    private int currentPos;

    private static int prevPos;
    private boolean bound = false;


    ServiceConnection serviceConnection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicServiceBind musicBind = (MusicService.MusicServiceBind)service;
            musicService = musicBind.getBinder();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        playButton = findViewById(R.id.playButton);
        playBar = findViewById(R.id.playBar);
        musicTitle = findViewById(R.id.musicName);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        handler = new Handler();
        setUp();



        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        path = intent.getExtras().getString("path");
        currentPos = intent.getExtras().getInt("pos");

        songList = (ArrayList<Song>)intent.getSerializableExtra("arraylist");
        size = songList.size();
        Log.d("FIRE DRAGON", "the szize of array " + songList.size());

        //songList = intent.getParcelableExtra("arraylist");

        Intent serviceIntent = new Intent(this, MusicService.class);
        serviceIntent.putExtra("path", path);
        serviceIntent.putExtra("nameMusic", title);
        serviceIntent.putExtra("currentPos", currentPos);
        serviceIntent.putExtra("musicList", (ArrayList<Song>) songList);
        prevPos = currentPos;

        Log.d("CREATING", "CREATING AGAIN");
        //bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        startService(serviceIntent);


    }


    public void onStart() {

        super.onStart();


    }

    public void setUp(){
        playButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        playBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    musicService.setSongTime(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    private void setBarProgress(){
        playBar.setProgress(musicService.getProgress());
        runnable = new Runnable() {
            @Override
            public void run() {
                setBarProgress();
            }
        };
    }

    @Override
    public void onClick(View v) {
        if(v == playButton && (musicService.isPlaying() == true)){
            stopService(new Intent(this, MusicService.class));
            handler.removeCallbacks(runnable);
        }
        if(v == playButton && (musicService.isPlaying() == false)){
           // musicService.setPath(path, currentPos);

            startService(new Intent(this, MusicService.class));
            int dur = musicService.getCurrent();
            playBar.setMax(dur);
            setBarProgress();


        }
        else if(v == prevButton){

            if(currentPos <= 0)
                currentPos = songList.size() - 1;
            else
                currentPos -= 1;
            musicService.setPos(currentPos);
            musicService.playPrev();
            int dur = musicService.getCurrent();
            playBar.setMax(dur);

        }
        else if(v == nextButton){
            currentPos += 1;
            if(currentPos >= size)
                currentPos = 0;

            musicService.setPos(currentPos);
            musicService.playNext();
            int dur = musicService.getCurrent();
            playBar.setMax(dur);
        }
        else{
            ;
        }


    }
}
