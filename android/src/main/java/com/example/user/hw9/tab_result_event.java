package com.example.user.hw9;

/**
 * Created by user on 2017/4/19.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.Arrays;

public class tab_result_event extends Fragment {
    String[] id;
    String[] name;
    String[] picture;
    Button next, prev;
    int end;
    ListView listView_event;
    DetailListViewAdapter adapter;
    private int currentpage = 1;
    private int totalpage;
    public tab_result_event(String[] id, String[] name, String[] picture){
        this.id = id;
        this.picture = picture;
        this.name = name;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_result_event, container,false);
        listView_event =(ListView) rootView.findViewById(R.id.e_list);
        if (name.length%10 !=0)
            totalpage = name.length/10 +1;
        else
            totalpage = name.length/10;
        next =(Button) rootView.findViewById(R.id.e_next);
        prev=(Button) rootView.findViewById(R.id.e_prev);
        prev.setEnabled(false);
        if(totalpage ==1)
            next.setEnabled(false);
        if ((currentpage-1)*10 +10 > name.length)
            end= name.length;
        else
            end =(currentpage-1)*10 +10;
        adapter = new DetailListViewAdapter(getActivity(), Arrays.copyOfRange(name, (currentpage-1)*10,end), Arrays.copyOfRange(picture, (currentpage-1)*10,end), Arrays.copyOfRange(id, (currentpage-1)*10,end),"event");
        listView_event.setAdapter(adapter);

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentpage +=1;
                if ((currentpage-1)*10 +10 > name.length)
                    end= name.length;
                else
                    end =(currentpage-1)*10 +10;
                adapter = new DetailListViewAdapter(getActivity(), Arrays.copyOfRange(name, (currentpage-1)*10,end), Arrays.copyOfRange(picture, (currentpage-1)*10,end), Arrays.copyOfRange(id, (currentpage-1)*10,end),"event");
                listView_event.setAdapter(adapter);
                togglebtn();
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
                adapter = new DetailListViewAdapter(getActivity(), Arrays.copyOfRange(name, (currentpage-1)*10,end), Arrays.copyOfRange(picture, (currentpage-1)*10,end), Arrays.copyOfRange(id, (currentpage-1)*10,end),"event");
                listView_event.setAdapter(adapter);
                togglebtn();
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
