package com.music.musicality.musicality;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView title;
    private ImageView picture;
    private SeekBar bar;
    private Button playButton;
    private MediaPlayer musicPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
        musicPlayer = MediaPlayer.create(this, R.raw.test);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicPlayer.start();
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
