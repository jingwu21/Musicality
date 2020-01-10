package com.music.musicality.musicality;

import android.content.ContentResolver;
import android.content.Intent;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MusicListFragment extends Fragment {

    private AsyncQuery asyncCursor;
    private RecyclerView.Adapter recycleAdapter;
    private List<Song> songList;
    private RecyclerViewAdapt.OnClicker clickInterface;
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
        ((RecyclerViewAdapt)recycleAdapter).setOnClick(new RecyclerViewAdapt.OnClicker() {
            @Override
            public void OnClickAction(int position) {
                String songDuration = songList.get(position).getDuration();
                String songPath = songList.get(position).getPath();
                String songTitle = songList.get(position).getTitle();
                String songAuthor = songList.get(position).getAuthor();
                Intent gotToPlayer = new Intent(getActivity(), MusicPlayerActivity.class);

                Bundle storage = new Bundle();
                storage.putSerializable("mlist", (Serializable)songList);
                storage.putString("duration", songDuration);
                storage.putString("path", songPath);
                storage.putString("title", songTitle);
                storage.putString("author", songAuthor);
                storage.putInt("pos", position);
                gotToPlayer.putExtras(storage);

                startActivity(gotToPlayer);
            }
        });
    }


}
