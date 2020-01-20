package com.music.musicality.musicality;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Song implements Serializable {
    private String title;
    private String author;
    private String duration;
    private String path;

    public Song(String title, String author, String duration, String path){
        this.title = title;
        this.author = author;
        this.duration = duration;
        this.path = path;
    }



    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getDuration() {
        return duration;
    }

    public String getPath(){
        return path;
    }


}
