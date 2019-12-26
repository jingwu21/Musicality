package com.music.musicality.musicality;

public class Song {
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
