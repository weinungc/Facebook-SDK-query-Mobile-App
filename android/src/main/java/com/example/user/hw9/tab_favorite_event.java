package com.example.user.hw9;

/**
 * Created by user on 2017/4/19.
 */
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class tab_favorite_event extends Fragment {
    String[] id;
    String[] name;
    String[] picture;
    DetailListViewAdapter adapter;
    ListView listView_event;
    Storage app;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_favorite_event, container,false);
        listView_event =(ListView) rootView.findViewById(R.id.e_f_list);
        app = (Storage)getActivity().getApplicationContext();

        if(app.getsize("event")!=0){
            id = new String[app.getsize("event")];
            id = app.getid("event").toArray(id);
            name = new String[app.getsize("event")];
            name = app.getname("event").toArray(name);
            picture = new String[app.getsize("event")];;
            picture = app.getpicture("event").toArray(picture);
        }

        adapter = new DetailListViewAdapter(getActivity(),name,picture,id,"event");
        listView_event.setAdapter(adapter);



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
                Log.v("size","size is:"+app.getsize("event"));
                if(app.getsize("event")!=0){
                    id = new String[app.getsize("event")];
                    id = app.getid("event").toArray(id);
                    name = new String[app.getsize("event")];
                    name = app.getname("event").toArray(name);
                    picture = new String[app.getsize("event")];;
                    picture = app.getpicture("event").toArray(picture);
                }else {
                    id = null;
                    name = null;
                    picture = null;
                }

                adapter = new DetailListViewAdapter(getActivity(),name,picture,id,"event");
                listView_event.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //listView_event.invalidate();
            }
        });
    }
}
