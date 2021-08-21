package com.example.dynamicgraph;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    ArrayList<Value> list;
    Activity act;

    public ListAdapter(Activity act,ArrayList<Value> list) {
        this.act = act;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }
    private class ViewHolder{
        TextView yeartxt;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        final ViewHolder vh;
        LayoutInflater inflater=act.getLayoutInflater();

        if (view==null){
            view=inflater.inflate(R.layout.list_row,null);
            vh=new ViewHolder();
            vh.yeartxt=(TextView) view.findViewById(R.id.value_txt);
            view.setTag(vh);
        }else {
            vh=(ViewHolder) view.getTag();
        }
        vh.yeartxt.setText(list.get(i).getXvalue());
        return view;
    }
}
