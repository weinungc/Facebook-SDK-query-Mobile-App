package com.example.user.hw9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.Arrays;

/**
 * Created by user on 2017/4/19.
 */

public class tab_result_user extends Fragment {
    String[] id;
    String[] name;
    String[] picture;
    DetailListViewAdapter adapter;
    ListView listView_user;
    Button next, prev;
    int end;
    private int currentpage = 1;
    private int totalpage;
    public tab_result_user(String[] id,String[] name, String[] picture){
        this.id = id;
        this.picture = picture;
        this.name = name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_result_user, container,false);
        listView_user =(ListView) rootView.findViewById(R.id.u_list);
        if (name.length%10 !=0){
            totalpage = name.length/10 +1;
        }else{
            totalpage = name.length/10;
        }
        Log.v("test", "user total is: "+name.length);
        Log.v("test", " total page is: "+totalpage);
        next =(Button) rootView.findViewById(R.id.u_next);
        prev=(Button) rootView.findViewById(R.id.u_prev);
        prev.setEnabled(false);
        if(totalpage ==1)
            next.setEnabled(false);
        if ((currentpage-1)*10 +10 > name.length)
            end= name.length;
        else
            end =(currentpage-1)*10 +10;
        adapter = new DetailListViewAdapter(getActivity(), Arrays.copyOfRange(name, (currentpage-1)*10,end), Arrays.copyOfRange(picture, (currentpage-1)*10,end), Arrays.copyOfRange(id, (currentpage-1)*10,end),"user");
        listView_user.setAdapter(adapter);

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentpage +=1;
                if ((currentpage-1)*10 +10 > name.length)
                    end= name.length;
                else
                    end =(currentpage-1)*10 +10;
                adapter = new DetailListViewAdapter(getActivity(), Arrays.copyOfRange(name, (currentpage-1)*10,end), Arrays.copyOfRange(picture, (currentpage-1)*10,end), Arrays.copyOfRange(id, (currentpage-1)*10,end),"user");
                listView_user.setAdapter(adapter);
                togglebtn();
                Log.v("test", "current page is:  "+currentpage);
            }
        });
        prev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentpage -=1;
                if ((currentpage-1)*10 +10 > name.length)
                    end= name.length;
                else
                    end =(currentpage-1)*10 +10;
                adapter = new DetailListViewAdapter(getActivity(), Arrays.copyOfRange(name, (currentpage-1)*10,end), Arrays.copyOfRange(picture, (currentpage-1)*10,end), Arrays.copyOfRange(id, (currentpage-1)*10,end),"user");
                listView_user.setAdapter(adapter);
                togglebtn();
                Log.v("test", "current page is:  "+currentpage);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void togglebtn(){
        if(currentpage == totalpage){
            next.setEnabled(false);
            prev.setEnabled(true);
        }else if (currentpage == 1){
            next.setEnabled(true);
            prev.setEnabled(false);
        }else{
            next.setEnabled(true);
            prev.setEnabled(true);
        }
    }
}
