package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Admin_main extends AppCompatActivity {


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
        setContentView(R.layout.activity_admin_main);

        list=(ListView)findViewById(R.id.list);

        viewimage();

    }

    private void viewimage() {

        db=openOrCreateDatabase("images",MODE_PRIVATE,null);

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

        CustomListAdapter c=new CustomListAdapter(getApplicationContext(),namelist,cmdlist,countlis,imglist);
        list.setAdapter(c);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.addtable:
                Toast.makeText(getApplicationContext(),"Add Table",Toast.LENGTH_LONG).show();
                Intent in=new Intent(Admin_main.this,Add_tables.class);
                startActivity(in);
                return true;
            case R.id.vieworder:
                Toast.makeText(getApplicationContext(),"Order List",Toast.LENGTH_LONG).show();
                Intent inn=new Intent(Admin_main.this,Order_List.class);
                startActivity(inn);
                return true;
            case R.id.menucard:
                Toast.makeText(getApplicationContext(),"Menu Card",Toast.LENGTH_LONG).show();
                Intent i=new Intent(Admin_main.this,Menu_card.class);
                startActivity(i);
                return true;
            case R.id.logout:
                Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_LONG).show();
                Intent logout=new Intent(Admin_main.this,Home.class);
                startActivity(logout);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}