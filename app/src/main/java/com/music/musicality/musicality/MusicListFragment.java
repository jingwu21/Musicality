package com.music.musicality.musicality;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MusicListFragment extends Fragment {

    private AsyncQuery asyncCursor;
    private RecyclerView.Adapter recycleAdapter;
    private List<Song> songList;
    private RecyclerView recycleContainer;
    private RecyclerView.LayoutManager recycleLayout;
    public MusicListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.music_list, container, false);
        songList = new ArrayList<>();

        recycleContainer = v.findViewById(R.id.recyclelist);

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = getActivity().getContentResolver();

        asyncCursor = new AsyncQuery(contentResolver, songList, this);
        asyncCursor.startQuery(1, null, uri, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        return v;
    }


    public void setRecycler(){
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recycleAdapter = new RecyclerViewAdapt((ArrayList)songList);
        recycleContainer.setLayoutManager(linearLayout);
        recycleContainer.setAdapter(recycleAdapter);

    }


}
