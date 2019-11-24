package com.music.musicality.musicality;

import android.content.ContentResolver;
import android.media.AudioManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView title;
    private ImageView picture;
    private SeekBar bar;
    private Button playButton;
    private MediaPlayer musicPlayer;
    private List<Song> playList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = getContentResolver();

        musicPlayer = new MediaPlayer();
        musicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //musicPlayer.setDataSource();
        //musicPlayer.prepare()
        //musicPlayer.start()
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!musicPlayer.isPlaying())
                    musicPlayer.start();
                else
                    musicPlayer.pause();
            }
        });
    }
    private void setUp(){
        title = findViewById(R.id.musicTitle);
        picture = findViewById(R.id.musicImage);
        bar =  findViewById(R.id.musicBar);
        playButton = findViewById(R.id.musicPlay);
        musicPlayer = new MediaPlayer();

    }
}
