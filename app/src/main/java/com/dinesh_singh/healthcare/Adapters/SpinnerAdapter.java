package com.dinesh_singh.healthcare.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dinesh_singh.healthcare.R;

public class SpinnerAdapter extends ArrayAdapter<String>{
    private Context mContext;
    private String[] mStringData;

     public SpinnerAdapter( Context context, int resource, String[] objects ) {
            super(context, resource,objects);
            this.mContext=context;
            this.mStringData=objects;
        }
        public View getCustomView( int position, View convertView, ViewGroup parent ) {
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=inflater.inflate(R.layout.custom_spinner,parent,false);
            TextView text=view.findViewById(R.id.my_custom_spinner);
            text.setText(mStringData[position]);
            text.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
            text.setTextSize(15);
            return view;
        }
        @Override
        public View getDropDownView( int position,View convertView,ViewGroup parent ) {
            return getCustomView(position, convertView, parent);
        }
        @Override
        public View getView( int position,View convertView,ViewGroup parent ) {
            return getCustomView(position, convertView, parent);
        }







}
