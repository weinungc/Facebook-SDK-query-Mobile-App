package com.example.user.hw9;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by user on 2017/4/21.
 */

public class tab_detail_post extends Fragment {
    String name, picture;
    String[] message, time;
    PostListViewAdapter adapter;
    ListView post_listview;


    public tab_detail_post(String name, String picture,String[] message,String[] time){
        this.name =name;
        this.picture = picture;
        this.message = message;
        this.time = time;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;



        if(message !=null){
            rootView  = inflater.inflate(R.layout.tab_detail_post, container,false);
            post_listview =(ListView) rootView.findViewById(R.id.post_list);
            adapter = new PostListViewAdapter(getActivity(),name,picture,message,time);
            post_listview.setAdapter(adapter);
        }else{
            rootView  = inflater.inflate(R.layout.tab_detail_post_null, container,false);
        }

        return rootView;
    }
}
