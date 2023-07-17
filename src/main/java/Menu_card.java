package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Menu_card extends AppCompatActivity {

    String foodtype[] = {"Select","Fine Dining","Casual Dining","Contemporary Casua","Contemporary Casua","South Indian","North Indian","Chinese Food"};
    Spinner foodtyp;
    private Bitmap photo;
    private final static int requst=1888;
    SQLiteDatabase db;
    EditText Hotelname,foodname,foodcost;
    ImageView foodimg;
    Button addfood;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_card);

        addfood = findViewById(R.id.addfood);
        foodtyp = findViewById(R.id.food_type);
        Hotelname = findViewById(R.id.Hotel_Name);
        foodname = findViewById(R.id.Food_name);
        foodcost = findViewById(R.id.Food_cost);
        foodimg = findViewById(R.id.Food_img);


        db = openOrCreateDatabase("Fimages", MODE_PRIVATE, null);
        db.execSQL("create table if not exists data(name varchar(30),comd varchar(30),count varhcar(50),image blob)");

        final ArrayAdapter<String> ge = new ArrayAdapter<String>(this, R.layout.spinnerdesig, R.id.spindesig, foodtype) {
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        foodtyp.setAdapter(ge);

        foodtyp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
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

            foodimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent in=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(in, requst);

                }
            });

            addfood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        db = openOrCreateDatabase("Fimages", MODE_PRIVATE, null);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        photo.compress(Bitmap.CompressFormat.PNG, 0, bos);
                        byte[] imgpath = bos.toByteArray();

                        ContentValues val = new ContentValues();
                        val.put("name", Hotelname.getText().toString());
                        val.put("comd", foodname.getText().toString());
                        val.put("count", foodcost.getText().toString());
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
            foodimg.setImageBitmap(photo);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}