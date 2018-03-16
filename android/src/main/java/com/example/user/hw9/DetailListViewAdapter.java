package com.example.user.hw9;

/**
 * Created by user on 2017/4/21.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    String[] name;
    String[] picture;
    String[] id;
    String type;
    LayoutInflater inflater;

    public DetailListViewAdapter(Context context, String[] name, String[] picture, String[] id, String type) {
        this.context = context;
        this.name = name;
        this.picture = picture;
        this.id = id;
        this.type = type;
    }

    public int getCount() {
        if(name == null )
            return 0;
        else
            return name.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        // Declare Variables
        TextView textname;
        ImageView img, detail,star;
        Storage app = (Storage) context.getApplicationContext();

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View itemView = inflater.inflate(R.layout.result_list_item, parent, false);
        star =(ImageView) itemView.findViewById(R.id.star);
        if(app.hasid(type, id[position])){
            star.setImageResource(R.drawable.favorites_on);
        }else{
            star.setImageResource(R.drawable.favorites_off);
        }

        // Locate the TextViews in listview_item.xml
        textname = (TextView) itemView.findViewById(R.id.name);
        // Locate the ImageView in listview_item.xml
        img = (ImageView) itemView.findViewById(R.id.img);
        detail =(ImageView) itemView.findViewById(R.id.detail) ;
        detail.setClickable(true);
        detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String url = "https://graph.facebook.com/v2.8/" +id[position]+"?fields=id,name,picture.width(700).height(700),albums.limit(5){name,photos.limit(2){name,picture}},posts.limit(5)&access_token=EAATUSbkln9gBAAvfyZBwoENEKKuDVJPGJltFfqZCoNk0nJZBnfykTdTrO6Aie8w6EGBhxvDUwmG4hpbDcvuGLx5DnM513lpGIoUjZCg59NZAFsUN9Xrp3owRfQeGVmAjRB9SN7LTYGSisfVrtcQqq5KqEtTvjmtQZD";
                Log.v("test",url);
//                Log.v("test","id[0]:"+id[0]);
//                Log.v("test","id[1]:"+id[1]);
                //url[1] store id
                String[] url2 = new String[]{url, id[position],name[position],picture[position]};
                new GetDetailJSON().execute(url2);

            }
        });
        //img.setTag(picture[position]);
        Picasso.with(context).load(picture[position]).into(img);
        // Capture position and set to the TextViews
        //Bitmap bmp = getBitmapFromURL(picture[position]);
        textname.setText(name[position]);

        // Capture position and set to the ImageView
        //img.setImageBitmap(bmp);
        //new DownloadImageTask.execute(name[position],img );
        return itemView;
    }

    class GetDetailJSON extends AsyncTask<String,Void,String[]>{
        @Override
        protected String[] doInBackground(String... params) {
            String[] result = new String[5];
            result[0] = GET(params[0]);
            result[1] = params[1];
            result[2] = params[2];
            result[3] = params[3];
            return result;
        }

        @Override
        protected void onPostExecute(String[] result) {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("json",result);
            intent.putExtra("type",type);
            Log.v("test","JOSN: "+result[0]);
            context.startActivity(intent);
        }

        private String GET(String url){
            HttpURLConnection c = null;
            try {
                URL u = new URL(url);
                c = (HttpURLConnection) u.openConnection();
                c.connect();
                int status = c.getResponseCode();
                switch (status) {
                    case 200:
                    case 201:
                        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line+"\n");
                        }
                        br.close();
                        return sb.toString().trim();
                }

            }catch (Exception e){
                Log.d("InputStream", e.getLocalizedMessage());
            }finally {
                if (c != null) {
                    try {
                        c.disconnect();
                    } catch (Exception ex) {
                        //disconnect error
                    }
                }
            }
            return null;
        }

    }

//    public class DownloadImagesTask extends AsyncTask<ImageView, Void, Bitmap> {
//
//        ImageView imageView = null;
//
//        @Override
//        protected Bitmap doInBackground(ImageView... imageViews) {
//            this.imageView = imageViews[0];
//            return getBitmapFromURL((String) imageView.getTag());
//        }
//
//        @Override
//        protected Bitmap doInBackground(ImageView... params) {
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap result) {
//            imageView.setImageBitmap(result);
//        }
//
//
//        private Bitmap getBitmapFromURL(String src) {
//            try {
//                URL url = new URL(src);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream input = connection.getInputStream();
//                Bitmap myBitmap = BitmapFactory.decodeStream(input);
//                return myBitmap;
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }
//    public Bitmap getBitmapFromURL(String src){
//        try{
//            URL url = new URL(src);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//            return myBitmap;
//        }catch(Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
}
