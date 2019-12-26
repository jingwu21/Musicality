package com.music.musicality.musicality;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;


public class ViewPagerAdapt extends FragmentPagerAdapter {

    private Fragment currentFragment;

    public ViewPagerAdapt(FragmentManager c){
        super(c);
    }
    @Override
    public Fragment getItem(int i) {

        MusicListFragment x = new MusicListFragment();
        i = i + 1;
        return x;
    }

    public Fragment getCurrentFragment(){
        return currentFragment;
    }

    @Override
    public void setPrimaryItem (ViewGroup container, int position, Object object){
        if(currentFragment != object){
            currentFragment = (Fragment)object;
        }
        super.setPrimaryItem(container, position, object);
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
