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

public class tab_favorite_page extends Fragment {
    String[] id;
    String[] name;
    String[] picture;
    DetailListViewAdapter adapter;
    ListView listView_page;
    Storage app;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_favorite_page, container,false);
        listView_page =(ListView) rootView.findViewById(R.id.pa_f_list);
        app = (Storage)getActivity().getApplicationContext();

        if(app.getsize("page")!=0){
            id = new String[app.getsize("page")];
            id = app.getid("page").toArray(id);
            name = new String[app.getsize("page")];
            name = app.getname("page").toArray(name);
            picture = new String[app.getsize("page")];;
            picture = app.getpicture("page").toArray(picture);
        }

        adapter = new DetailListViewAdapter(getActivity(),name,picture,id,"page");
        listView_page.setAdapter(adapter);



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
                Log.v("size","size is:"+app.getsize("page"));
                if(app.getsize("page")!=0){
                    id = new String[app.getsize("page")];
                    id = app.getid("page").toArray(id);
                    name = new String[app.getsize("page")];
                    name = app.getname("page").toArray(name);
                    picture = new String[app.getsize("page")];;
                    picture = app.getpicture("page").toArray(picture);
                }else {
                    id = null;
                    name = null;
                    picture = null;
                }

                adapter = new DetailListViewAdapter(getActivity(),name,picture,id,"page");
                listView_page.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //listView_page.invalidate();
            }
        });
    }
}
