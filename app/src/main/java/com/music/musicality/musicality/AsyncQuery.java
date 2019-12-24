package com.music.musicality.musicality;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

public class AsyncQuery extends AsyncQueryHandler{
    public AsyncQuery(ContentResolver c){
        super(c);
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        super.onQueryComplete(token, cookie, cursor);
    }
}
