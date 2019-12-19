package com.music.musicality.musicality;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;


public class ViewPagerAdapt extends FragmentPagerAdapter {


    public ViewPagerAdapt(FragmentManager c){
        super(c);
    }
    @Override
    public Fragment getItem(int i) {

        MusicListFragment x = new MusicListFragment();
        i = i + 1;
        Bundle c = new Bundle();
        c.putString("yo", "Hello, Mr.Wu");
        x.setArguments(c);
        return x;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        position += 1;
        switch(position){
            case 1:
                return "Tracks";
            case 2:
                return "Player";
            default:
                break;
        }
        return null;
    }
}
