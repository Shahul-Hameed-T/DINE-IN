package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyProfile extends AppCompatActivity {


    TextView proname,proage,pronumber;
    RatingBar ratingBar;
    Button prosubmit;
    EditText comments;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        proname = findViewById(R.id.proname);
        proage = findViewById(R.id.proage);
        pronumber = findViewById(R.id.pronumber);
        ratingBar = findViewById(R.id.ratingBar);
        prosubmit = findViewById(R.id.prosubmit);
        comments = findViewById(R.id.comments);


        SharedPreferences Sp=getSharedPreferences("Pdb",MODE_PRIVATE);
        proname.setText("Name: " + Sp.getString("N"," "));
        proage.setText("Email: " + Sp.getString("E"," "));
        pronumber.setText("Number: " + Sp.getString("No"," "));

        prosubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commend = comments.getText().toString();
                String rating=String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
                finish();
                // finishAffinity();
                //System.exit(0);

            }
        });

    }
}