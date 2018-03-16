package com.example.user.hw9;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    JSONObject user_json,page_json,event_json,place_json,group_json;
    JSONArray user_jsonarray,page_jsonarray,event_jsonarray,place_jsonarray,group_jsonarray;
    //ContactAdapter contactAdapter_user, contactAdapter_page, contactAdapter_event, contactAdapter_place, contactAdapter_group;
    //ListView listView_user,listView_page,listView_event,listView_place,listView_group;

    String[] id_user;
    String[] name_user;
    String[] picture_user;
    String[] id_page;
    String[] name_page;
    String[] picture_page;
    String[] id_event;
    String[] name_event;
    String[] picture_event;
    String[] id_place;
    String[] name_place;
    String[] picture_place;
    String[] id_group;
    String[] name_group;
    String[] picture_group;
    MyAdapter adapter;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.v("test","hi");



//        listView_user =(ListView) findViewById(R.id.u_list);
//        listView_user.setAdapter(contactAdapter_user);
//        listView_page =(ListView) findViewById(R.id.pa_list);
//        listView_page.setAdapter(contactAdapter_page);
//        listView_event =(ListView) findViewById(R.id.e_list);
//        listView_event.setAdapter(contactAdapter_event);
//        listView_place =(ListView) findViewById(R.id.pl_list);
//        listView_place.setAdapter(contactAdapter_place);
//        listView_group =(ListView) findViewById(R.id.g_list);
//        listView_group.setAdapter(contactAdapter_group);

        Bundle extras = getIntent().getExtras();
        String[] json = extras.getStringArray("json");
        //Log.v("test",json[3]);
        try {
            user_json = new JSONObject(json[0]);
            page_json = new JSONObject(json[1]);
            event_json = new JSONObject(json[2]);
            place_json = new JSONObject(json[3]);
            group_json = new JSONObject(json[4]);

            user_jsonarray = user_json.getJSONArray("data");
            page_jsonarray = page_json.getJSONArray("data");
            event_jsonarray = event_json.getJSONArray("data");
            place_jsonarray = place_json.getJSONArray("data");
            group_jsonarray = group_json.getJSONArray("data");

            //init array
            id_user = new String[user_jsonarray.length()];
            name_user = new String[user_jsonarray.length()];
            picture_user =new String[user_jsonarray.length()];
            id_page = new String[page_jsonarray.length()];
            name_page = new String[page_jsonarray.length()];
            picture_page =new String[page_jsonarray.length()];
            id_event = new String[event_jsonarray.length()];
            name_event = new String[event_jsonarray.length()];
            picture_event =new String[event_jsonarray.length()];
            id_place = new String[place_jsonarray.length()];
            name_place = new String[place_jsonarray.length()];
            picture_place =new String[place_jsonarray.length()];
            id_group = new String[group_jsonarray.length()];
            name_group = new String[group_jsonarray.length()];
            picture_group =new String[group_jsonarray.length()];

            int count = 0;
            String name, picture;
            int id;
            //user json parse
            while(count < user_jsonarray.length()){
                JSONObject temp = user_jsonarray.getJSONObject(count);
                id_user[count] =temp.getString("id");
                name_user[count] = temp.getString("name");
                picture_user[count] = temp.getJSONObject("picture").getJSONObject("data").getString("url");
                //Contacts contacts = new Contacts(id,name,picture);
                //contactAdapter_user.add(contacts);
                count++;

            }
            count =0;
            while(count < page_jsonarray.length()){
                JSONObject temp = page_jsonarray.getJSONObject(count);
                id_page[count] = temp.getString("id");
                name_page[count] = temp.getString("name");
                picture_page[count] = temp.getJSONObject("picture").getJSONObject("data").getString("url");
                //Contacts contacts = new Contacts(id,name,picture);
                //contactAdapter_page.add(contacts);
                count++;
            }

            count =0;
            while(count < event_jsonarray.length()){
                JSONObject temp = event_jsonarray.getJSONObject(count);
                id_event[count] = temp.getString("id");
                name_event[count] = temp.getString("name");
                picture_event[count] = temp.getJSONObject("picture").getJSONObject("data").getString("url");
                //Contacts contacts = new Contacts(id,name,picture);
                //contactAdapter_event.add(contacts);
                count++;
            }

            count =0;
            while(count < place_jsonarray.length()){
                JSONObject temp = place_jsonarray.getJSONObject(count);
                id_place[count] = temp.getString("id");
                name_place[count] = temp.getString("name");
                picture_place[count] = temp.getJSONObject("picture").getJSONObject("data").getString("url");
                //Contacts contacts = new Contacts(id,name,picture);
                //contactAdapter_place.add(contacts);
                count++;
            }
            count =0;
            while(count < group_jsonarray.length()){
                JSONObject temp = group_jsonarray.getJSONObject(count);
                id_group[count] = temp.getString("id");
                name_group[count] = temp.getString("name");
                picture_group[count] = temp.getJSONObject("picture").getJSONObject("data").getString("url");
                //Contacts contacts = new Contacts(id,name,picture);
                //contactAdapter_group.add(contacts);
                count++;
            }



        }catch (JSONException e){
            e.printStackTrace();
        }



//        Log.v("test",json[0]);
//        Log.v("test",json[1]);
//        Log.v("test",json[2]);
//        Log.v("test",json[3]);
//        Log.v("test",json[4]);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        adapter =new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(mViewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.users);
                tabLayout.getTabAt(1).setIcon(R.drawable.pages);
                tabLayout.getTabAt(2).setIcon(R.drawable.events);
                tabLayout.getTabAt(3).setIcon(R.drawable.places);
                tabLayout.getTabAt(4).setIcon(R.drawable.groups);

            }
        });




        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    protected void onResume() {
        Log.v("test","Onresume");
        super.onResume();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //do your modifications here

                tabLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        tabLayout.setupWithViewPager(mViewPager);
                        tabLayout.getTabAt(0).setIcon(R.drawable.users);
                        tabLayout.getTabAt(1).setIcon(R.drawable.pages);
                        tabLayout.getTabAt(2).setIcon(R.drawable.events);
                        tabLayout.getTabAt(3).setIcon(R.drawable.places);
                        tabLayout.getTabAt(4).setIcon(R.drawable.groups);

                    }
                });
                // for example
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    class MyAdapter extends FragmentPagerAdapter{
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new tab_result_user(id_user,name_user,picture_user);
                case 1 : return new tab_result_page(id_page,name_page,picture_page);
                case 2 : return new tab_result_event(id_event,name_event,picture_event);
                case 3 : return new tab_result_place(id_place,name_place,picture_place);
                case 4 : return new tab_result_group(id_group,name_group,picture_group);
            }
            return null;
        }

        @Override
        public int getCount() {

            return 5;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Users";
                case 1 :
                    return "Pages";
                case 2 :
                    return "Events";
                case 3 :
                    return "Places";
                case 4 :
                    return "Groups";
            }
            return null;
        }
    }
}
