package com.music.musicality.musicality;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;


public class ViewPagerAdapt extends FragmentPagerAdapter {


    public ViewPagerAdapt(FragmentManager c){
        super(c);
    }
    @Override
    public Fragment getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
