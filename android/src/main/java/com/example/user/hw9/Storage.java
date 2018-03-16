package com.example.user.hw9;

import android.app.Application;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/4/21.
 */

public class Storage extends Application {
    private ArrayList<String> u_id;
    private ArrayList<String> pa_id;
    private ArrayList<String> e_id;
    private ArrayList<String> pl_id;
    private ArrayList<String> g_id;
    private ArrayList<String> u_name;
    private ArrayList<String> pa_name;
    private ArrayList<String> e_name;
    private ArrayList<String> pl_name;
    private ArrayList<String> g_name;
    private ArrayList<String> u_picture;
    private ArrayList<String> pa_picture;
    private ArrayList<String> e_picture;
    private ArrayList<String> pl_picture;
    private ArrayList<String> g_picture;


    public void add(String type,String id,String name,String picture){
        Log.v("test","type:" +type);
        if (type.equals("user")){
            if(u_id ==null){
                u_id = new ArrayList<String>();
                u_name = new ArrayList<String>();
                u_picture = new ArrayList<String>();
            }
            u_id.add(id);
            u_name.add(name);
            u_picture.add(picture);

        }else if (type.equals("page")){
            Log.v("test","page here");
            if(pa_id ==null){
                Log.v("test","page null here");
                pa_id = new ArrayList<String>();
                pa_name = new ArrayList<String>();
                pa_picture = new ArrayList<String>();
            }
            Log.v("test","Array size"+pa_id.size());
            pa_id.add(id);
            pa_name.add(name);
            pa_picture.add(picture);
            Log.v("test","array size: "+pa_id.size());

        }else if (type.equals("event")){

            if(e_id ==null){
                e_id = new ArrayList<String>();
                e_name = new ArrayList<String>();
                e_picture = new ArrayList<String>();
            }
            e_id.add(id);
            e_name.add(name);
            e_picture.add(picture);
        }else if (type.equals("place")){
            if(pl_id ==null){
                pl_id = new ArrayList<String>();
                pl_name = new ArrayList<String>();
                pl_picture = new ArrayList<String>();
            }
            pl_id.add(id);
            pl_name.add(name);
            pl_picture.add(picture);
        }else if (type.equals("group")){
            if(g_id ==null){
                g_id = new ArrayList<String>();
                g_name = new ArrayList<String>();
                g_picture = new ArrayList<String>();
            }
            g_id.add(id);
            g_name.add(name);
            g_picture.add(picture);
        }


    }

    public ArrayList<String> getid(String type){
        if (type.equals("user")){
            return u_id;
        }else if (type.equals("page")){
            return pa_id;
        }else if (type.equals("event")){
            return e_id;
        }else if (type.equals("place")){
            return pl_id;
        }else{
            return g_id;
        }

    }

    public ArrayList<String> getname(String type){
        if (type.equals("user")){
            return u_name;
        }else if (type.equals("page")){
            return pa_name;
        }else if (type.equals("event")){
            return e_name;
        }else if (type.equals("place")){
            return pl_name;
        }else{
            return g_name;
        }

    }
    public ArrayList<String> getpicture(String type)
    {
        if (type.equals("user")){
            return u_picture;
        }else if (type.equals("page")){
            return pa_picture;
        }else if (type.equals("event")){
            return e_picture;
        }else if (type.equals("place")){
            return pl_picture;
        }else{
            return g_picture;
        }

    }
    public boolean hasid(String type, String id){
        if (type.equals("user")){
            if(u_id != null)
                return u_id.contains(id);
            else
                return false;
        }else if (type.equals("page")){
            if(pa_id !=null)
                return pa_id.contains(id);
            else
                return false;
        }else if (type.equals("event")){
            if(e_id != null)
                return e_id.contains(id);
            else
                return false;
        }else if (type.equals("place")){
            if(pl_id != null)
                return pl_id.contains(id);
            else
                return false;
        }else{
            if (g_id !=null)
                return g_id.contains(id);
            else
                return false;
        }
    }

    public int getsize(String type){
        if (type.equals("user")){
            if(u_id!=null)
                return u_id.size();
            else
                return 0;
        }else if (type.equals("page")){
            if(pa_id!=null)
                return pa_id.size();
            else
                return 0;
        }else if (type.equals("event")){
            if(e_id!=null)
                return e_id.size();
            else
                return 0;
        }else if (type.equals("place")){
            if(pl_id!=null)
                return pl_id.size();
            else
                return 0;
        }else{
            if(g_id!=null)
                return g_id.size();
            else
                return 0;
        }

    }
    public String getitemid(String type,int index){
        if (type.equals("user")){
            return u_id.get(index);
        }else if (type.equals("page")){
            return pa_id.get(index);
        }else if (type.equals("event")){
            return e_id.get(index);
        }else if (type.equals("place")){
            return pl_id.get(index);
        }else{
            return g_id.get(index);
        }

    }

    public String getitemname(String type, int index){
        if (type.equals("user")){
            return u_name.get(index);
        }else if (type.equals("page")){
            return pa_name.get(index);
        }else if (type.equals("event")){
            return e_name.get(index);
        }else if (type.equals("place")){
            return pl_name.get(index);
        }else{
            return g_name.get(index);
        }

    }

    public String getitempicture(String type, int index){
        if (type.equals("user")){
            return u_picture.get(index);
        }else if (type.equals("page")){
            return pa_picture.get(index);
        }else if (type.equals("event")){
            return e_picture.get(index);
        }else if (type.equals("place")){
            return pl_picture.get(index);
        }else{
            return g_picture.get(index);
        }

    }
    public void removeitem(String type, String id){
        if (type.equals("user")){
            if(u_id.contains(id)){
                int index = u_id.indexOf(id);
                u_id.remove(index);
                u_name.remove(index);
                u_picture.remove(index);
            }
        }else if (type.equals("page")){
            if(pa_id.contains(id)){
                int index = pa_id.indexOf(id);
                pa_id.remove(index);
                pa_name.remove(index);
                pa_picture.remove(index);
            }
        }else if (type.equals("event")){
            if(e_id.contains(id)){
                int index = e_id.indexOf(id);
                e_id.remove(index);
                e_name.remove(index);
                e_picture.remove(index);
            }
        }else if (type.equals("place")){
            if(pl_id.contains(id)){
                int index = pl_id.indexOf(id);
                pl_id.remove(index);
                pl_name.remove(index);
                pl_picture.remove(index);
            }
        }else if (type.equals("group")){
            if(g_id.contains(id)){
                int index = g_id.indexOf(id);
                g_id.remove(index);
                g_name.remove(index);
                g_picture.remove(index);
            }
        }

    }

}
