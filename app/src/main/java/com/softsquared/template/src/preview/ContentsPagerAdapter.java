package com.softsquared.template.src.preview;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.softsquared.template.src.preview.fragment.AnyangFragment;
import com.softsquared.template.src.preview.fragment.JapanFragment;
import com.softsquared.template.src.preview.fragment.MiseMapFragment;
import com.softsquared.template.src.preview.fragment.NullSchoolFragment;

public class ContentsPagerAdapter extends FragmentStatePagerAdapter {
    public ContentsPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                MiseMapFragment miseMapFragment = new MiseMapFragment();
                return miseMapFragment;
            case 1:
                AnyangFragment anyangFragment = new AnyangFragment();
                return anyangFragment;
            case 2:
                JapanFragment japanFragment = new JapanFragment();
                return japanFragment;
            case 3:
                NullSchoolFragment nullSchoolFragment = new NullSchoolFragment();
                return nullSchoolFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
