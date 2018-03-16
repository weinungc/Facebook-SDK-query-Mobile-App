package com.example.user.hw9;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by user on 2017/4/19.
 */

public class FavoriteFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items =5;
    MyAdapter adapter;

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.favorite_layout, container, false);
        tabLayout = (TabLayout) myView.findViewById(R.id.f_tabs);
        viewPager = (ViewPager) myView.findViewById(R.id.viewpager);
        adapter =new MyAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.users);
                tabLayout.getTabAt(1).setIcon(R.drawable.pages);
                tabLayout.getTabAt(2).setIcon(R.drawable.events);
                tabLayout.getTabAt(3).setIcon(R.drawable.places);
                tabLayout.getTabAt(4).setIcon(R.drawable.groups);

            }
        });

        return myView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //do your modifications here
                tabLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        tabLayout.setupWithViewPager(viewPager);
                        tabLayout.getTabAt(0).setIcon(R.drawable.users);
                        tabLayout.getTabAt(1).setIcon(R.drawable.pages);
                        tabLayout.getTabAt(2).setIcon(R.drawable.events);
                        tabLayout.getTabAt(3).setIcon(R.drawable.places);
                        tabLayout.getTabAt(4).setIcon(R.drawable.groups);

                    }
                });
                // for example
                adapter.notifyDataSetChanged();
            }
        });
    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new tab_favorite_user();
                case 1 : return new tab_favorite_page();
                case 2 : return new tab_favorite_event();
                case 3 : return new tab_favorite_place();
                case 4 : return new tab_favorite_group();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Users";
                case 1 :
                    return "Pages";
                case 2 :
                    return "Events";
                case 3 :
                    return "Places";
                case 4 :
                    return "Groups";
            }
            return null;
        }
    }
}
