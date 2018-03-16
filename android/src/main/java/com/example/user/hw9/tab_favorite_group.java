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

public class tab_favorite_group extends Fragment {
    String[] id;
    String[] name;
    String[] picture;
    DetailListViewAdapter adapter;
    ListView listView_group;
    Storage app;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_favorite_group, container,false);
        listView_group =(ListView) rootView.findViewById(R.id.g_f_list);
        app = (Storage)getActivity().getApplicationContext();

        if(app.getsize("group")!=0){
            id = new String[app.getsize("group")];
            id = app.getid("group").toArray(id);
            name = new String[app.getsize("group")];
            name = app.getname("group").toArray(name);
            picture = new String[app.getsize("group")];;
            picture = app.getpicture("group").toArray(picture);
        }

        adapter = new DetailListViewAdapter(getActivity(),name,picture,id,"group");
        listView_group.setAdapter(adapter);



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
                Log.v("size","size is:"+app.getsize("group"));
                if(app.getsize("group")!=0){
                    id = new String[app.getsize("group")];
                    id = app.getid("group").toArray(id);
                    name = new String[app.getsize("group")];
                    name = app.getname("group").toArray(name);
                    picture = new String[app.getsize("group")];;
                    picture = app.getpicture("group").toArray(picture);
                }else {
                    id = null;
                    name = null;
                    picture = null;
                }

                adapter = new DetailListViewAdapter(getActivity(),name,picture,id,"group");
                listView_group.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //listView_group.invalidate();
            }
        });
    }
}
