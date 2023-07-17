package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Submit_Info extends AppCompatActivity {

    EditText nameU,ageU,DOBU,numberL,numberLE,numberReg,numberAdd,MobU;
    Button submitU;
    SQLiteDatabase dbs;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit__info);

        dbs=openOrCreateDatabase("UserSubmit.db",MODE_PRIVATE,null);
        dbs.execSQL("create table if not exists Utables(NAME TEXT,AGE TEXT,DOB TEXT,LicenceNo TEXT,LicenceEX TEXT,RegistrationNo TEXT,Address TEXT,Mobile TEXT);");
        dbs.close();

        nameU = findViewById(R.id.nameU);
        ageU = findViewById(R.id.ageU);
        DOBU = findViewById(R.id.DOBU);
        numberL = findViewById(R.id.numberL);
        numberLE = findViewById(R.id.numberLE);
        numberReg = findViewById(R.id.numberReg);
        numberAdd = findViewById(R.id.numberAdd);
        MobU = findViewById(R.id.MobU);
        submitU = findViewById(R.id.submitU);


        submitU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String na = nameU.getText().toString();
                String ag = ageU.getText().toString();
                String dob = DOBU.getText().toString();
                String nl = numberL.getText().toString();
                String nle = numberLE.getText().toString();
                String nr = numberReg.getText().toString();
                String nad = numberAdd.getText().toString();
                String mob = MobU.getText().toString();

                if (na.isEmpty())
                {
                    nameU.setError("Name");
                }
                else if(ag.isEmpty())
                {
                    ageU.setError("Age");
                }
                else if(dob.isEmpty())
                {
                    DOBU.setError("D.O.B");
                }
                else if(nl.isEmpty())
                {
                    numberL.setError("Licence");
                }
                else if(nle.isEmpty())
                {
                    numberLE.setError("Expiry Date");
                }
                else if(nr.isEmpty())
                {
                    numberReg.setError("Registration Date");
                }
                else if(nad.isEmpty())
                {
                    numberAdd.setError("Address");
                }
                else if(mob.isEmpty())
                {
                    MobU.setError("Mobile Number");
                }
                else{

                    dbs=openOrCreateDatabase("UserSubmit.db",MODE_PRIVATE,null);
                    dbs.execSQL("insert into Utables values('" + na + "','" + ag + "','" + dob + "','" + nl + "','" + nle + "','" + nr + "','"+ nad +"','" + mob + "');");
                    finishAffinity();
                    /*SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(mob, null,  "Dear, " + na + " You are Successfully Registered in Vehicle Finder",  null, null);
                */}
            }
        });



    }
}