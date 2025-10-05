package com.example.elaboratocookaroo.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.elaboratocookaroo.Fragment.ViewPager.FirstFragment;
import com.example.elaboratocookaroo.Fragment.ViewPager.SecondFragment;
import com.example.elaboratocookaroo.Fragment.ViewPager.ThirdFragment;

public class IntroAdapter extends FragmentPagerAdapter {
    public IntroAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
}
