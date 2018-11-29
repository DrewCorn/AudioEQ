package com.example.drewk.audioeq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProfileAdapter extends BaseAdapter {

    List<Profile> profiles;
    Context context;

    public ProfileAdapter(List<Profile> list, Context c) {
        this.profiles = list;
        this.context =c;
    }

    public void setData(List<Profile> profiles) {
        this.profiles = profiles;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return profiles.size();
    }

    @Override
    public Object getItem(int position) {
        return profiles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, null);
        TextView tv = v.findViewById(android.R.id.text1);
        tv.setText(profiles.get(position).getName());
        return v;
    }
}
