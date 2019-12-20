package com.music.musicality.musicality;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MusicListFragment extends Fragment {

    private RecyclerView.Adapter recycleAdapter;
    private ArrayList<Song> songList;
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
        songList.add(new Song("Nightcore", "Night", "4:00"));
        songList.add(new Song("Here comes the boom", "Unknown", "5:32"));
        songList.add(new Song("Born for greatness", "Roach", "4:22"));
        recycleContainer = v.findViewById(R.id.recyclelist);
        recycleAdapter = new RecyclerViewAdapt(songList);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recycleContainer.setLayoutManager(linearLayout);
        recycleContainer.setAdapter(recycleAdapter);

        return v;
    }


}
