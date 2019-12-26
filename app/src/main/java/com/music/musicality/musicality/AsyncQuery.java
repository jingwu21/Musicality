package com.music.musicality.musicality;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;


import java.util.List;

public class AsyncQuery extends AsyncQueryHandler{

    private final String songName = MediaStore.Audio.Media.TITLE;
    private final String songAuthor = MediaStore.Audio.Media.ARTIST;
    private final String songDuration = MediaStore.Audio.Media.DURATION;
    private final String songPath = MediaStore.Audio.Media.DATA;
    private List<Song> songList;
    private MainActivity mainActivity;

    public AsyncQuery(ContentResolver c, List<Song> songList){
        super(c);
        this.songList = songList;

    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {


            if(cursor == null) {

            }
            else if(cursor.getCount() == 0){

            }
            else{
                int nameIndex = cursor.getColumnIndex(songName);
                int artistIndex = cursor.getColumnIndex(songAuthor);
                int durationIndex = cursor.getColumnIndex(songDuration);
                int pathIndex = cursor.getColumnIndex(songPath);
                MediaMetadataRetriever durRetrieval = new MediaMetadataRetriever();
                while(cursor.moveToNext()){
                    /*String name = cursor.getString(nameIndex);
                    String artist = cursor.getString(artistIndex);
                    */
                    String path = cursor.getString(pathIndex);
                    durRetrieval.setDataSource(path);
                    String artist = durRetrieval.extractMetadata(durRetrieval.METADATA_KEY_ARTIST);
                    String name = durRetrieval.extractMetadata(durRetrieval.METADATA_KEY_TITLE);
                    String duration = durRetrieval.extractMetadata(durRetrieval.METADATA_KEY_DURATION);
                    songList.add(new Song(name, artist, duration, path));
                }

            }

        }

}
