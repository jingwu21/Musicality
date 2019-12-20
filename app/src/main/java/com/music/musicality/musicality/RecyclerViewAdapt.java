package com.music.musicality.musicality;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapt extends RecyclerView.Adapter<RecyclerViewAdapt.ViewHolders>{
    private ArrayList<Song> items;

    public static class ViewHolders extends RecyclerView.ViewHolder {
        private TextView musicTitle;
        private TextView musicAuthor;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            musicTitle = itemView.findViewById(R.id.mtitle);
            musicAuthor = itemView.findViewById(R.id.mauthor);
        }
    }
    public RecyclerViewAdapt(ArrayList<Song> m){
        items = m;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        ViewHolders c = new ViewHolders(v);
        return c;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders viewHolders, int i) {
        Song s = items.get(i);
        viewHolders.musicAuthor.setText(s.getTitle());
        viewHolders.musicTitle.setText(s.getAuthor());
    }



    @Override
    public int getItemCount() {
        return items.size();
    }
}
