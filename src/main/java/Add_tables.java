package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Add_tables extends AppCompatActivity {

    String type[] = {"Choose","Family","Friends","Party","Meeting"};
    Spinner tabletyp;
    EditText tableid,tableno,personcount;
    ImageView img1,img2,img3,img4,img5,img6;
    private Bitmap photo;
    private final static int requst=1888;
    SQLiteDatabase db;
    Button addtab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tables);

        addtab= findViewById(R.id.add_tab);
        tableid = findViewById(R.id.table_id);
        tableno = findViewById(R.id.table_no);
        personcount = findViewById(R.id.person_count);
        tabletyp = findViewById(R.id.tab_type);
        img1 = findViewById(R.id.img1);

        db=openOrCreateDatabase("images",MODE_PRIVATE,null);

        db.execSQL("create table if not exists data(name varchar(30),comd varchar(30),count varhcar(50),image blob)");

        final ArrayAdapter<String> ge = new ArrayAdapter<String>(this,R.layout.spinnerdesig,R.id.spindesig,type){
            public boolean isEnabled(int position) {
                if(position==0){
                    return false;
                }else{
                    return true;
                }
            }
        };
        tabletyp.setAdapter(ge);

        tabletyp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
                // TODO Auto-generated method stub
               String studeg = ge.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(in, requst);

            }
        });

        addtab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    db = openOrCreateDatabase("images", MODE_PRIVATE, null);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.PNG, 0, bos);
                    byte[] imgpath = bos.toByteArray();

                    ContentValues val = new ContentValues();
                    val.put("name", tableid.getText().toString());
                    val.put("comd", tableno.getText().toString());
                    val.put("count", personcount.getText().toString());
                    val.put("image", imgpath);
                    db.insert("data", null, val);

                    Toast.makeText(getApplicationContext(),"succesfull save",Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if(requestCode==requst)
        {
            photo=(Bitmap)data.getExtras().get("data");
            img1.setImageBitmap(photo);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}