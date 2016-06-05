package com.example.administrator.bluetooth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public class Adapter extends BaseAdapter {
    private List<Info> list;
    private Context context;
    public Adapter(Context context,List<Info> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.name,null);
        TextView t=(TextView)view.findViewById(R.id.textView);
        t.setText(list.get(position).getName()+"\n"+list.get(position).getMac());

        return view;
    }
}
