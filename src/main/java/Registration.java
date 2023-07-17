 package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText name,mail,pass,num;
    Button submit;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.Name);
        mail = findViewById(R.id.Email);
        pass = findViewById(R.id.Password);
        num = findViewById(R.id.Number);
        submit = findViewById(R.id.Submit);


        db = openOrCreateDatabase ("User Details.db",MODE_PRIVATE,null);
        db.execSQL("create table if not exists Dtables(Name TEXT,Email TEXT,Password TEXT,Number TEXT);");
        db.close();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String names=name.getText().toString();
                String passs=pass.getText().toString();
                String mails=mail.getText().toString();
                String nums=num.getText().toString();

                if (names.isEmpty())
                {
                    name.setError("Username");
                }
                else if (mails.isEmpty())
                {
                    mail.setError("Email");
                }
                else if (passs.isEmpty())
                {
                    pass.setError("Password");
                }
                else if (nums.isEmpty())
                {
                    num.setError("Number");
                }
                else
                {
                    db = openOrCreateDatabase ("User Details.db",MODE_PRIVATE,null);
                    db.execSQL("insert into Dtables values('" + names + "','" + mails + "','" + passs + "','" + nums + " ');");
                    Toast.makeText(getApplicationContext(),"Register Successfully",Toast.LENGTH_SHORT).show();
                    db.close();


                    SharedPreferences Sp=getSharedPreferences("Pdb",MODE_PRIVATE);
                    SharedPreferences.Editor edit=Sp.edit();
                    edit.putString("N", names);
                    edit.putString("E", mails);
                    edit.putString("No", nums);
                    edit.commit();


                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(nums, null,  "Dear, " + names + " You are Successfully Registered in DINE-IN Application",  null, null);
                    Intent find=new Intent(getApplicationContext(),Login.class);
                    startActivity(find);
                    finish();

                }

            }
        });

    }
}