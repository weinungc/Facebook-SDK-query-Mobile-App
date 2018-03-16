package com.example.user.hw9;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String q;
    double longitude =0.0;
    double latitude =0.0;
    private TrackGPS gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void clearOnClick(View v){

        EditText editText = (EditText)findViewById(R.id.editText);
        editText.setText("");
    }

    public void submitOnClick(View v){
        EditText editText = (EditText)findViewById(R.id.editText);
        String[] urls = new String[5];
        //not empty
        if (editText.getText().toString().matches("")){
            Toast.makeText(this, "Please enter a keyword!",Toast.LENGTH_SHORT).show();
            return;
        }else{
            q = editText.getText().toString();
            //location
            gps = new TrackGPS(MainActivity.this);
            if(gps.canGetLocation()){
                longitude = gps.getLongitude();
                latitude = gps.getLatitude();
            }
            urls[0] ="http://sample-env-2.nmijtt2ymb.us-west-2.elasticbeanstalk.com/?q=" +q+"&type=user&longitude="+longitude+"&latitude="+latitude;
            urls[1] ="http://sample-env-2.nmijtt2ymb.us-west-2.elasticbeanstalk.com/?q=" +q+"&type=page&longitude="+longitude+"&latitude="+latitude;
            urls[2] ="http://sample-env-2.nmijtt2ymb.us-west-2.elasticbeanstalk.com/?q=" +q+"&type=event&longitude="+longitude+"&latitude="+latitude;
            if(longitude == 0.0 ||latitude==0.0){
                urls[3] ="http://sample-env-2.nmijtt2ymb.us-west-2.elasticbeanstalk.com/?q=" +q+"&type=place";
                //Log.v("place",urls[3]);
            }else{
                urls[3] ="http://sample-env-2.nmijtt2ymb.us-west-2.elasticbeanstalk.com/?q=" +q+"&type=place&longitude="+longitude+"&latitude="+latitude;
                //Log.v("place",urls[3]);
            }
            urls[4] ="http://sample-env-2.nmijtt2ymb.us-west-2.elasticbeanstalk.com/?q=" +q+"&type=group&longitude="+longitude+"&latitude="+latitude;

            new GetResultJSON().execute(urls);
        }

    }

    class GetResultJSON extends AsyncTask<String,Void,String[]>{
        @Override
        protected String[] doInBackground(String... params) {
            String[] result = new String[5];

            for(int i =0; i<params.length;i++){
                result[i] = GET(params[i]);
            }
            return result;
        }

        @Override
        protected void onPostExecute(String[] result) {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("json",result);
            startActivity(intent);


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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_home) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
            setTitle("Search on FB");
        } else if (id == R.id.nav_favorites) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new FavoriteFragment()).commit();
            setTitle("Favorites");
        }else if (id == R.id.nav_about_me){
//            fragmentManager.beginTransaction().replace(R.id.content_frame, new About_meFragment()).commit();
//            setTitle("About me");
            Intent intent = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


