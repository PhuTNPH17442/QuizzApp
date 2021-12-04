package com.example.quizzapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0 :
                return  new CreditsFragment();
            case 1 :
                return  new CreditsFragment2();
            case 2 :
                return  new CreditsFragment3();
            default:
               return new CreditsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
