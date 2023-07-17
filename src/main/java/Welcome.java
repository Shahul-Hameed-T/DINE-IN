package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Welcome extends AppCompatActivity {

    ImageView exuser,newuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        exuser = findViewById(R.id.exuser);
        newuser = findViewById(R.id.newuser);

        exuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent bk =new Intent(Welcome.this,MainActivity.class);;
                startActivity(bk);
            }
        });
       /* newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent bk =new Intent(Welcome.this,Newuser.class);;
                startActivity(bk);
            }
        });*/
    }
}
