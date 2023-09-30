package com.example.bt1_th_tuan_2_3application;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<NhanVien> {
     Activity context = null;
     ArrayList<NhanVien> myArray = null;
     int layoutId;

    public MyArrayAdapter(Activity context, int textViewResourceId, ArrayList<NhanVien> objects) {
        super(context, textViewResourceId, objects);

        this.context = context;
        this.layoutId = textViewResourceId;
        this.myArray = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        convertView = layoutInflater.inflate(layoutId, null);

        if(myArray.size() > 0 && position >= 0)
        {
            final TextView txtDisplay = convertView.findViewById(R.id.tv_item);
            final NhanVien nv = myArray.get(position);
            txtDisplay.setText(nv.toString());
            final ImageView imgitem = convertView.findViewById(R.id.img_item);
            if(nv.isGender())
            {
                imgitem.setImageResource(R.drawable.man);
            }
            else
            {
                imgitem.setImageResource(R.drawable.woman);
            }
        }
        return convertView;
    }
}
