package com.music.musicality.musicality;

public class Song {
    private String title;
    private String author;
    private String duration;
    public Song(String title, String author, String duration){
        this.title = title;
        this.author = author;
        this.duration = duration;
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
}
