package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Order_List extends AppCompatActivity {

    ListView Customerlist;
    ArrayList<String> Name, Num, ID;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__list);

        Customerlist = (ListView) findViewById(R.id.Customerlist);
        Name = new ArrayList<String>();
        Num = new ArrayList<String>();
        ID = new ArrayList<String>();

        db = openOrCreateDatabase ("Order Details.db",MODE_PRIVATE,null);
        try {
            Cursor c = db.rawQuery("select * from Dtables", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        String name = c.getString(3);
                        String age = c.getString(4);
                        String num = c.getString(0);


                        Name.add(name);
                        Num.add(age);
                        ID.add(num);


                    } while (c.moveToNext());
                }
                Adap ad = new Adap(Order_List.this, Name, Num, ID);
                Customerlist.setAdapter(ad);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No Data Here!", Toast.LENGTH_SHORT).show();
        }

    }
}