package com.example.user.hw9;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017/4/21.
 */

public class AlbumAdapter extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String,List<String >> albums;
    private List<String> albums_name;

    public AlbumAdapter(Context ctx, HashMap<String,List<String >> albums, List<String> albums_name){
        this.albums = albums;
        this.ctx =ctx;
        this.albums_name =albums_name;
    }



    @Override
    public int getGroupCount() {
        if(albums_name !=null)
            return albums_name.size();
        else
            return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return albums.get(albums_name.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return albums_name.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return albums.get(albums_name.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group_title =(String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater =(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.detail_album_parent,parent,false);
        }
        TextView parent_textview = (TextView) convertView.findViewById(R.id.album_name);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String child_url = (String) getChild(groupPosition,childPosition);
        if(convertView == null){
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.detail_album_child,parent,false);
            ImageView child_img =(ImageView) convertView.findViewById(R.id.pic);
            Picasso.with(ctx).load(child_url).into(child_img);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
