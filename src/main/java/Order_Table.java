package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Order_Table extends AppCompatActivity {

    TextView loadname,loadno,loadcount;
    EditText Oname,Onum,Ocomments;
    Button order;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__table);

        loadname = findViewById(R.id.loadname);
        loadno =  findViewById(R.id.loadno);
        loadcount  = findViewById(R.id.loadcount);
        Oname = findViewById(R.id.Oname);
        Onum = findViewById(R.id.Onum);
        Ocomments = findViewById(R.id.Ocomments);
        order = findViewById(R.id.order);

        db = openOrCreateDatabase ("Order Details.db",MODE_PRIVATE,null);
        db.execSQL("create table if not exists Dtables(HotelName TEXT,TableNo TEXT,Count TEXT,OName TEXT,ONumber TEXT,OComments TEXT);");
        db.close();

        final Intent in = getIntent();
        loadname.setText("Hotel Name: " + in.getStringExtra("Hna"));
        loadno.setText("Table No: " + in.getStringExtra("Tno"));
        loadcount.setText("Person Count: " + in.getStringExtra("Pcount"));


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String names=in.getStringExtra("Hna");
                String passs=in.getStringExtra("Tno");
                String mails=in.getStringExtra("Pcount");
                String Ona = Oname.getText().toString();
                String Onu = Onum.getText().toString();
                String Ocm = Ocomments.getText().toString();


                if (Ona.isEmpty())
                {
                    Oname.setError("Username");
                }
                else if (Onu.isEmpty())
                {
                    Onum.setError("Number");
                }
                else if (Ocm.isEmpty())
                {
                    Ocomments.setError("Comments");
                }
                else {

                    db = openOrCreateDatabase ("Order Details.db",MODE_PRIVATE,null);
                    db.execSQL("insert into Dtables values('" + names + "','" + passs + "','" + mails + "','" + Ona + "','" + Onu + "','" + Ocm + "');");
                    Toast.makeText(getApplicationContext(),"Order Successfully",Toast.LENGTH_SHORT).show();
                    db.close();
                    Intent find=new Intent(getApplicationContext(),Payment.class);
                    startActivity(find);
                    finish();

                }


            }
        });
    }
}