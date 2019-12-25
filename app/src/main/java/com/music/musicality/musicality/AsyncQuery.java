package com.music.musicality.musicality;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

public class AsyncQuery extends AsyncQueryHandler{
    private MainActivity homeObject;
    public AsyncQuery(ContentResolver c, MainActivity homeObject){
        super(c);
        this.homeObject = homeObject;
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {

    }
}
