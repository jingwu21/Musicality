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

public class MusicPlayerActivity extends AppCompatActivity implements View.OnClickListener, MusicService.Communication{

    private ImageButton playButton, prevButton, nextButton;
    private SeekBar playBar;
    private TextView musicTitle;
    private MediaPlayer player;
    private MusicService musicService;
    private String title;

    private static final String q = "MUSIC_TITLE";
    private String path;
    private int size;
    private Handler handler;
    private static int currentTime;
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
        musicTitle.setText(title);
        songList = (ArrayList<Song>)intent.getSerializableExtra("arraylist");
        size = songList.size();


        //songList = intent.getParcelableExtra("arraylist");

        Intent serviceIntent = new Intent(this, MusicService.class);
        serviceIntent.putExtra("path", path);
        serviceIntent.putExtra("nameMusic", title);
        serviceIntent.putExtra("currentPos", currentPos);
        serviceIntent.putExtra("musicList", (ArrayList<Song>) songList);
        serviceIntent.putExtra("state", 1);
        prevPos = currentPos;



        //bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        startService(serviceIntent);
        musicService.setUpBar(this);



    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {



        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(q, title);
    }

    public void onStart() {

        super.onStart();


    }

    public void onDestroy(){
        super.onDestroy();
        handler.removeCallbacks(runnable);

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
        if(musicService.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    setBarProgress();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == playButton && (musicService.isPlaying() == true)){
            stopService(new Intent(this, MusicService.class));
            handler.removeCallbacks(runnable);
            currentTime = musicService.getProgress();

        }
        if(v == playButton && (musicService.isPlaying() == false)){


            startService(new Intent(this, MusicService.class));

//            int dur = musicService.getCurrent();
//            playBar.setMax(dur);
//            setBarProgress();


        }
        else if(v == prevButton){

            if(currentPos <= 0)
                currentPos = songList.size() - 1;
            else
                currentPos -= 1;

            musicTitle.setText(songList.get(currentPos).getTitle());
            Intent service = new Intent(this, MusicService.class);
            service.putExtra("currentPos", currentPos);
            service.putExtra("state", -2);
            startService(service);
//            int dur = musicService.getCurrent();
//            playBar.setMax(dur);
//            setBarProgress();

        }
        else if(v == nextButton){
            currentPos += 1;
            if(currentPos >= size)
                currentPos = 0;

            //musicService.setPos(currentPos);
            musicTitle.setText(songList.get(currentPos).getTitle());
            Intent service = new Intent(this, MusicService.class);
            service.putExtra("currentPos", currentPos);
            service.putExtra("state", 2);
            startService(service);
            //musicService.playNext();
//            int dur = musicService.getCurrent();
//            playBar.setMax(dur);
//            setBarProgress();
        }
        else{
            ;
        }


    }

    @Override
    public void seekBarUpdate(int x, String q) {
        musicTitle.setText(q);
        playBar.setMax(x);
        setBarProgress();
    }
}
