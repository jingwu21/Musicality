package com.music.musicality.musicality;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MusicPlayerActivity extends AppCompatActivity {

    private ImageButton playButton, prevButton, nextButton;
    private SeekBar playBar;
    private TextView musicTitle;
    private MediaPlayer player;
    private MusicService service;
    private String title;
    private String path;

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
        player = new MediaPlayer();
        player.prepareAsync();
        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        path = intent.getExtras().getString("path");

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                service = new MusicService(player);
            }
        });

    }





}
