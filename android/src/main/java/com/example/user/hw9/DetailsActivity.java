package com.example.user.hw9;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    //data
    HashMap<String, List<String>> albums = new HashMap<String, List<String>>();

    String detail_id;
    String name, picture;
    String[] message;
    String[] time;
    String type;
    TabLayout tabLayout;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String json_string;
        JSONArray json_array;
        JSONObject json_object;
        Bundle extras = getIntent().getExtras();
        String[] temp = extras.getStringArray("json");
        type = extras.getString("type");


        json_string = temp[0];
        detail_id = temp[1];
        name = temp[2];
        picture =temp[3];

        if(json_string == null){
            albums = null;
            message = null;
            time = null;

        }else{
            try{
                json_object = new JSONObject(json_string);
//                name = json_object.getString("name");
//                picture = json_object.getJSONObject("picture").getJSONObject("data").getString("url");


                json_array = json_object.getJSONObject("albums").getJSONArray("data");
                int count =0;
                while (count <json_array.length()){
                    JSONObject temp_obj = json_array.getJSONObject(count);

                    List<String> album_pic = new ArrayList<String>();
                    JSONArray temp_array = temp_obj.getJSONObject("photos").getJSONArray("data");
                    int count2 =0;
                    //add pics
                    while (count2 < temp_array.length()){
                        JSONObject album_pic_temp = temp_array.getJSONObject(count2);
                        Log.v("test","pic_url: "+album_pic_temp.getString("picture") );
                        album_pic.add("https://graph.facebook.com/v2.9/"+album_pic_temp.getString("id")+"/picture?access_token=EAAUkamWGdZAkBAPF0xNMjT8qQF4NPaTY3joDSZAiNAOKXoxw52e1EcKsbkrktcwNwIwtL2RVBkhuNYS7Vs41EHsaNsI6FCZAoI4fZCjG8opBZATZAXzeP4KDfJnA0UMs4KZBMUbv8NQDfEdd6ZCE4RcChE3FcYqksbwZD");

                        count2++;
                    }
                    //add pics to album
                    Log.v("test","album name:"+temp_obj.getString("name"));
                    albums.put(temp_obj.getString("name"),album_pic);
                    count++;
                }
                //posts
                json_array = json_object.getJSONObject("posts").getJSONArray("data");
                count = 0;
                message = new String[json_array.length()];
                time = new String[json_array.length()];

                while(count < json_array.length()){
                    JSONObject temp_json = json_array.getJSONObject(count);
                    message[count] = temp_json.getString("message");
                    time[count] =temp_json.getString("created_time");
                    count++;
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(mViewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.albums);
                tabLayout.getTabAt(1).setIcon(R.drawable.posts);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Storage app = (Storage)getApplicationContext();
        getMenuInflater().inflate(R.menu.menu_details, menu);
        if(app.hasid(type,detail_id)){
            menu.findItem(R.id.favorite).setTitle("Remove from favorites");
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Storage app = (Storage)getApplicationContext();

        //noinspection SimplifiableIfStatement
        if (id == R.id.favorite) {
            Log.v("test","favorite click!!");
            if(app.hasid(type,detail_id)){
                Log.v("test","have");
                app.removeitem(type,detail_id);
                item.setTitle("Add to favorites");
            }else{
                Log.v("test"," don't have");
                Log.v("test","type:"+type+",id:"+detail_id+",name: "+name+",picture:"+picture);
                app.add(type,detail_id,name,picture);
                Log.v("test ","size is: "+ app.getsize(type));
                item.setTitle("Remove from favorites");
            }
            return true;
        }
        if (id == R.id.share) {


            Toast.makeText(this, "Sharing "+name+"!!", Toast.LENGTH_SHORT).show();
            ShareDialog shareDialog = new ShareDialog(this);
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle(name)
                    .setContentDescription(
                            "FB SEARCH FROM USC CSCI571...")
                    .setImageUrl(Uri.parse(picture))
                    .setContentUrl(Uri.parse("http://sample-env.nmijtt2ymb.us-west-2.elasticbeanstalk.com/"))
                    .build();
            if (ShareDialog.canShow(ShareLinkContent.class) == true){

                shareDialog.show(linkContent);

            }




            return true;
        }
        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 : return new tab_detail_album(albums);
                case 1 : return new tab_detail_post(name,picture,message,time);

            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Albums";
                case 1:
                    return "Posts";
            }
            return null;
        }
    }
}
