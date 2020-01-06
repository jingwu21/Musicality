package com.music.musicality.musicality;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private AsyncQuery asyncCursor;
    private Toolbar bar;
    private TabLayout tablayout;
    private ViewPager page;
    private ViewPagerAdapt adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        bar = findViewById(R.id.toolbar);
        setSupportActionBar(bar);
        //musicPlayer.setDataSource();
        //musicPlayer.prepare()
        //musicPlayer.start()
        askPermission();
        page = findViewById(R.id.pager);
        adapter = new ViewPagerAdapt(getSupportFragmentManager());
        page.setOffscreenPageLimit(1);
        page.setAdapter(adapter);
        tablayout = findViewById(R.id.tab);
        tablayout.setupWithViewPager(page);

    }


    private void askPermission(){
        String []permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[1]) == PackageManager.PERMISSION_GRANTED){



        }
        else{
            ActivityCompat.requestPermissions(this, permissions, 2555);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 2555){
            if( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){

                this.recreate();
            }
        }
    }



}
