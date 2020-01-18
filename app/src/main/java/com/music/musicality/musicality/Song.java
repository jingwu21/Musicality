package com.music.musicality.musicality;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
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

    protected Song(Parcel in) {
        title = in.readString();
        author = in.readString();
        duration = in.readString();
        path = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(duration);
        dest.writeString(path);
    }
}
