package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Newuser extends AppCompatActivity {

    SQLiteDatabase dbs;
    TextView na, age, dob, dlnum, dlexp, regnumm, adds, mobs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);

        na = findViewById(R.id.onwerna);
        age = findViewById(R.id.age);
        dob = findViewById(R.id.dob);
        dlnum = findViewById(R.id.dlnum);
        dlexp = findViewById(R.id.dlexp);
        regnumm = findViewById(R.id.regnum);
        adds = findViewById(R.id.address);
        mobs = findViewById(R.id.mobiles);

        /*SharedPreferences validate = getSharedPreferences("Number", MODE_PRIVATE);
        String value = validate.getString("Num", " ");*/

        Intent in = getIntent();
        String val = in.getStringExtra("Num");

        dbs=openOrCreateDatabase("UserSubmit.db",MODE_PRIVATE,null);
        try {
            Cursor c = dbs.rawQuery("select * from Utables where RegistrationNo ='" + val + "'", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {

                        String names = c.getString(0);
                        String ages = c.getString(1);
                        String dobs = c.getString(2);
                        String dlnums = c.getString(3);
                        String dlex = c.getString(4);
                        String regnum = c.getString(5);
                        String add = c.getString(6);
                        String mob = c.getString(7);

                        na.setText("Name: " + names);
                        age.setText("Age: " + ages);
                        dob.setText("DOB:" + dobs);
                        dlnum.setText("Licence No" + dlnums);
                        dlexp.setText("Licence Expiry Date: " + dlex);
                        regnumm.setText("Registration Number: " + regnum);
                        adds.setText("Address: " + add);
                        mobs.setText("Mobile: " + mob);

                    }
                    while (c.moveToNext());
                }

            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "No Data Here!", Toast.LENGTH_SHORT).show();
        }
    }
}
