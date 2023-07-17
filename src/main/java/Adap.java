package com.smile.imagetotext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adap extends ArrayAdapter<String> {
	
	ArrayList<String> Name;
	ArrayList<String> Mobile;
	ArrayList<String> ID;
	Context c;

	public Adap(Context context, ArrayList<String> Name, ArrayList<String> Mobile, ArrayList<String> ID) {
		super(context, R.layout.customlist, Name);
		// TODO Auto-generated constructor stub
		
		this.Name = Name;
		this.Mobile = Mobile;
		this.ID = ID;	
		this.c = context;
	}
	
	public static class ViewHolder{
    	TextView name;
    	TextView mobile;
    	TextView id;
    }
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView==null) {
			
			holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.customlist, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.View1);
            holder.mobile = (TextView) convertView.findViewById(R.id.View2);
            holder.id = (TextView) convertView.findViewById(R.id.View3);
            
            convertView.setTag(holder); // view lookup cache stored in tag
        } else {
        	holder = (ViewHolder) convertView.getTag();
        }
         
		holder.name.setText("Name: " + Name.get(position));
		holder.mobile.setText("Number: " +Mobile.get(position));
		holder.id.setText("Hotel Name: " +ID.get(position));

		return convertView;
	}
}
