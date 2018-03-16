package com.example.user.hw9;

/**
 * Created by user on 2017/4/21.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostListViewAdapter extends BaseAdapter {

    // Declare Variables
    String name;
    String picture;
    String[] message;
    String[] time;
    Context context;
    LayoutInflater inflater;

    public PostListViewAdapter(Context context, String name,String picture,String[] message,String[] time) {
        this.context =context;
        this.name = name;
        this.picture = picture;
        this.message = message;
        this.time = time;
    }

    public int getCount() {
        if(message !=null)
            return message.length;
        else
            return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        // Declare Variables
        TextView post_name, post_time,post_message;
        ImageView post_img;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.post_list_item, parent, false);

        // Locate the TextViews in listview_item.xml
        post_name = (TextView) itemView.findViewById(R.id.post_name);
        post_time = (TextView) itemView.findViewById(R.id.post_time);
        post_message = (TextView) itemView.findViewById(R.id.post_message);
        // Locate the ImageView in listview_item.xml
        post_img = (ImageView) itemView.findViewById(R.id.post_pic);

        Picasso.with(context).load(picture).into(post_img);
        // Capture position and set to the TextViews
        //Bitmap bmp = getBitmapFromURL(picture[position]);
        post_name.setText(name);
        post_time.setText(time[position]);
        post_message.setText(message[position]);
        return itemView;
    }


}
