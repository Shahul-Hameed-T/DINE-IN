package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Vehicleinformation extends AppCompatActivity {

    TextView bbq,shree,annapoorna,hari,saravana,barista,junior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicleinformation);


        bbq = findViewById(R.id.bbq);
        shree = findViewById(R.id.shree);
        annapoorna = findViewById(R.id.annapoorna);
        hari = findViewById(R.id.hari);
        saravana = findViewById(R.id.saravana);
        barista = findViewById(R.id.barista);
        junior = findViewById(R.id.junior);

        bbq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Hotel_info.class);
                in.putExtra("H",bbq.getText().toString());
                startActivity(in);

            }
        });
        shree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Hotel_info.class);
                in.putExtra("H",shree.getText().toString());
                startActivity(in);

            }
        });
        annapoorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Hotel_info.class);
                in.putExtra("H",annapoorna.getText().toString());
                startActivity(in);

            }
        });
        hari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Hotel_info.class);
                in.putExtra("H",hari.getText().toString());
                startActivity(in);

            }
        });
        saravana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Hotel_info.class);
                in.putExtra("H",saravana.getText().toString());
                startActivity(in);

            }
        });
        barista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Hotel_info.class);
                in.putExtra("H",barista.getText().toString());
                startActivity(in);

            }
        });
        junior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Hotel_info.class);
                in.putExtra("H",junior.getText().toString());
                startActivity(in);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.usermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.userprofile:
                Toast.makeText(getApplicationContext(),"My Profile",Toast.LENGTH_LONG).show();
                Intent in=new Intent(Vehicleinformation.this,MyProfile.class);
                startActivity(in);
                return true;
            case R.id.usermenucard:
                Toast.makeText(getApplicationContext(),"Menu Card",Toast.LENGTH_LONG).show();
                Intent i=new Intent(Vehicleinformation.this,Menu_list.class);
                startActivity(i);
                return true;
            case R.id.userlogout:
                Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_LONG).show();
                Intent logout=new Intent(Vehicleinformation.this,Home.class);
                startActivity(logout);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


