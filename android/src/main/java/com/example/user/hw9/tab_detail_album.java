package com.example.user.hw9;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017/4/21.
 */

public class tab_detail_album extends Fragment {
    HashMap<String, List<String>> albums;
    List<String> albums_name;
    ExpandableListView exp_album;
    AlbumAdapter adapter;

    public tab_detail_album(HashMap<String, List<String>> albums){
        this.albums =albums;
        if(albums !=null) {
            albums_name = new ArrayList<String>(albums.keySet());
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        if(albums_name != null){
            rootView= inflater.inflate(R.layout.tab_detail_album, container,false);
            exp_album =(ExpandableListView) rootView.findViewById(R.id.exp_list);
            adapter = new AlbumAdapter(getActivity(),albums,albums_name);
            exp_album.setAdapter(adapter);
        }else{
            rootView= inflater.inflate(R.layout.tab_detail_album_null, container,false);
        }
        return rootView;
    }
}
