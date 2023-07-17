package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.vision.L;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hand =new Handler();

        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent rah=new Intent(Splash.this, Home.class);
                startActivity(rah);
            }
        },3500);

    }
}
