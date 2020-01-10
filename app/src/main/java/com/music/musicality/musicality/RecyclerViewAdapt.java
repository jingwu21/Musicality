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
    private OnClicker itemClick;

    public static class ViewHolders extends RecyclerView.ViewHolder {
        private TextView musicTitle;
        private TextView musicAuthor;
        private TextView musicDuration;

        public ViewHolders(@NonNull View itemView, final OnClicker itemClick) {
            super(itemView);
            musicTitle = itemView.findViewById(R.id.mtitle);
            musicAuthor = itemView.findViewById(R.id.mauthor);
            musicDuration = itemView.findViewById(R.id.duration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClick != null)
                        if(getAdapterPosition() != -1)
                            itemClick.OnClickAction(getAdapterPosition());

                }
            });
        }
    }
    public RecyclerViewAdapt(ArrayList<Song> m){
        items = m;
    }


    interface OnClicker{
         void OnClickAction(int position);
    }

    public void setOnClick(OnClicker c){
        itemClick = c;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        ViewHolders c = new ViewHolders(v, itemClick);

        return c;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders viewHolders, int i) {
        Song s = items.get(i);
        viewHolders.musicAuthor.setText(s.getTitle());
        viewHolders.musicTitle.setText(s.getAuthor());
        viewHolders.musicDuration.setText(s.getDuration());
    }



    @Override
    public int getItemCount() {
        return items.size();
    }
}
