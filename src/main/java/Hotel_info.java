package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Hotel_info extends AppCompatActivity {

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
        setContentView(R.layout.activity_hotel_info);

        list=(ListView)findViewById(R.id.Table_List);

        viewimage();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String HName = namelist.get(position);
                String Tno = cmdlist.get(position);
                String Pcount = countlis.get(position);
                Intent in = new Intent(getApplicationContext(),Order_Table.class);
                in.putExtra("Hna",HName);
                in.putExtra("Tno",Tno);
                in.putExtra("Pcount", Pcount);
                startActivity(in);
                //Toast.makeText(getApplicationContext(),HName + Tno + Pcount ,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void viewimage() {
        db=openOrCreateDatabase("images",MODE_PRIVATE,null);
        Intent in = getIntent();
        String DADA = in.getStringExtra("H");

        try {
            c = db.rawQuery("select * from data where name = '" + DADA + "' ", null);
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
}