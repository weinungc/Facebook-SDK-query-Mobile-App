package com.example.user.hw9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Arrays;

/**
 * Created by user on 2017/4/19.
 */

public class tab_favorite_user extends Fragment {
    String[] id;
    String[] name;
    String[] picture;
    DetailListViewAdapter adapter;
    ListView listView_user;
    Storage app;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_favorite_user, container,false);
        listView_user =(ListView) rootView.findViewById(R.id.u_f_list);
        app = (Storage)getActivity().getApplicationContext();

        if(app.getsize("user")!=0){
            id = new String[app.getsize("user")];
            id = app.getid("user").toArray(id);
            name = new String[app.getsize("user")];
            name = app.getname("user").toArray(name);
            picture = new String[app.getsize("user")];;
            picture = app.getpicture("user").toArray(picture);
        }

        adapter = new DetailListViewAdapter(getActivity(),name,picture,id,"user");
        listView_user.setAdapter(adapter);



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
                Log.v("size","size is:"+app.getsize("user"));
                if(app.getsize("user")!=0){
                    id = new String[app.getsize("user")];
                    id = app.getid("user").toArray(id);
                    name = new String[app.getsize("user")];
                    name = app.getname("user").toArray(name);
                    picture = new String[app.getsize("user")];;
                    picture = app.getpicture("user").toArray(picture);
                }else {
                    id = null;
                    name = null;
                    picture = null;
                }

                adapter = new DetailListViewAdapter(getActivity(),name,picture,id,"user");
                listView_user.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //listView_user.invalidate();
            }
        });
    }
}
