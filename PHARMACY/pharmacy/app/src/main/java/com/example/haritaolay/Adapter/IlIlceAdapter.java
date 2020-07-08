package com.example.haritaolay.Adapter;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.haritaolay.Model.Il;
import com.example.haritaolay.R;

import java.util.ArrayList;

public class IlIlceAdapter extends BaseAdapter {
    Context context;
    ArrayList<Il> ililceler;
    LayoutInflater layoutInflater;
    public IlIlceAdapter (Activity activity,ArrayList<Il> ililceler){
        this.context=activity;
        this.ililceler=ililceler;
        this.layoutInflater=(LayoutInflater)context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
    }

    @Override

    public int getCount() {
        return ililceler.size();
    }

    @Override
    public Object getItem(int position) {
        return ililceler.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =layoutInflater.inflate(R.layout.denemegorunum,null);
        ListView listView=(ListView)view.findViewById(R.id.list_item);
        return  view;
    }
}
