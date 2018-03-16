package com.example.user.hw9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by user on 2017/4/19.
 */

public class tab_favorite_place extends Fragment {
    String[] id;
    String[] name;
    String[] picture;
    DetailListViewAdapter adapter;
    ListView listView_place;
    Storage app;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_favorite_place, container,false);
        listView_place =(ListView) rootView.findViewById(R.id.pl_f_list);
        app = (Storage)getActivity().getApplicationContext();

        if(app.getsize("place")!=0){
            id = new String[app.getsize("place")];
            id = app.getid("place").toArray(id);
            name = new String[app.getsize("place")];
            name = app.getname("place").toArray(name);
            picture = new String[app.getsize("place")];;
            picture = app.getpicture("place").toArray(picture);
        }

        adapter = new DetailListViewAdapter(getActivity(),name,picture,id,"place");
        listView_place.setAdapter(adapter);



        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //do your modifications here

                // for example
                Log.v("size","size is:"+app.getsize("place"));
                if(app.getsize("place")!=0){
                    id = new String[app.getsize("place")];
                    id = app.getid("place").toArray(id);
                    name = new String[app.getsize("place")];
                    name = app.getname("place").toArray(name);
                    picture = new String[app.getsize("place")];;
                    picture = app.getpicture("place").toArray(picture);
                }else {
                    id = null;
                    name = null;
                    picture = null;
                }

                adapter = new DetailListViewAdapter(getActivity(),name,picture,id,"place");
                listView_place.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //listView_place.invalidate();
            }
        });
    }
}
