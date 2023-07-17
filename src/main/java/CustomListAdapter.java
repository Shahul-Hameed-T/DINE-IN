package com.smile.imagetotext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    Context con;
    ArrayList<String> nmlis = new ArrayList<String>();
    ArrayList<String> cmdlis = new ArrayList<String>();
    ArrayList<String> countlis = new ArrayList<String>();
    ArrayList<byte[]> imglis = new ArrayList<byte[]>();

    LayoutInflater ly;

    public CustomListAdapter(Context c, ArrayList<String> nam, ArrayList<String> cmd, ArrayList<String> cunt,
            ArrayList<byte[]> img) {
        this.con = c;
        this.nmlis = nam;
        this.cmdlis = cmd;
        this.countlis = cunt;
        this.imglis = img;

        ly = (LayoutInflater.from(c));
    }

    @Override
    public int getCount() {
        return nmlis.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup parent) {

        v = ly.inflate(R.layout.cusomlist, null);

        TextView name = (TextView) v.findViewById(R.id.name1);
        TextView cmmd = (TextView) v.findViewById(R.id.cmd1);
        TextView counnt = (TextView) v.findViewById(R.id.counts);
        ImageView img1 = (ImageView) v.findViewById(R.id.image);

        name.setText("Hotel Name: " + nmlis.get(i));
        cmmd.setText("Table No: " + cmdlis.get(i));
        counnt.setText("Person Count: " + countlis.get(i));
        Bitmap bmp = BitmapFactory.decodeByteArray(imglis.get(i), 0, imglis.get(i).length);

        img1.setImageBitmap(bmp);
        return v;
    }
}
