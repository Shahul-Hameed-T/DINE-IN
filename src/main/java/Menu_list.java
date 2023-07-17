package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Menu_list extends AppCompatActivity {


    ImageView add;
    ListView list;

    SQLiteDatabase db;
    Cursor c;

    ArrayList<String> namelist=new ArrayList<String>();
    ArrayList<String> cmdlist=new ArrayList<String>();
    ArrayList<String> countlis=new ArrayList<String>();
    ArrayList<byte[]> imglist=new ArrayList<byte[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        list=(ListView)findViewById(R.id.Menucard_list);

        viewimage();
    }

    private void viewimage() {

        db = openOrCreateDatabase("Fimages", MODE_PRIVATE, null);
        try {
            c = db.rawQuery("select * from data", null);
            while (c.moveToNext()) {

                namelist.add(c.getString(0));
                cmdlist.add(c.getString(1));
                countlis.add(c.getString(2));
                imglist.add(c.getBlob(3));
            }
            db.close();
            viewlist();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    private void viewlist() {

        Customelist c=new Customelist(getApplicationContext(),namelist,cmdlist,countlis,imglist);
        list.setAdapter(c);
    }
}