package com.aruana.florianopolisroutes.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aruana.florianopolisroutes.R;

import java.util.List;

/**
 * Created by aruana on 01/06/15.
 */
public class RoutesDetailFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private Context context;

    public RoutesDetailFragmentAdapter(FragmentManager fm, List<Fragment> fragments, Context context) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }


    @Override
    public int getCount() {

        return this.fragments.size();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.stops);
            case 1:
                return context.getResources().getString(R.string.departures_weekday);
            case 2:
                return context.getResources().getString(R.string.departures_saturday);
            case 3:
                return context.getResources().getString(R.string.departures_sunday);
        }

        return null;
    }
}