package com.smile.imagetotext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Payment extends AppCompatActivity {


    ImageView gpay,phonepay,dirctpay;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        gpay = findViewById(R.id.gpay);
        phonepay = findViewById(R.id.phonepe);
        dirctpay = findViewById(R.id.diirectpay);
        builder = new AlertDialog.Builder(this);

        gpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent find=new Intent(getApplicationContext(),Final_Pay.class);
                startActivity(find);
            }
        });
        phonepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent find=new Intent(getApplicationContext(),Final_Pay.class);
                startActivity(find);
            }
        });
        dirctpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setMessage("Your Bill Pay to Direct Shop")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Your are Paid Successfully", Toast.LENGTH_SHORT).show();
                                Intent vieww=new Intent(Payment.this,Home.class);;
                                startActivity(vieww);
                            }
                        });
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("DINI-IN");
                alert.show();
            }
        });

    }
}