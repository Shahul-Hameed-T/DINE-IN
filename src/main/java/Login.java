package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button login,signup;
    SQLiteDatabase db;

    int backpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password =  findViewById(R.id.passwords);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pname=username.getText().toString();
                String ppass=password.getText().toString();

                if (pname.isEmpty())
                {
                    username.setError("Email");
                }
                else if (ppass.isEmpty())
                {
                    password.setError("Password");
                }
                else

                {
                    db = openOrCreateDatabase ("User Details.db",MODE_PRIVATE,null);
                    try {
                        Cursor c = db.rawQuery("select * from Dtables where Email = '" + pname + "' and Password = '" + ppass + "'", null);
                        if (c.moveToNext())
                        {

                            Toast.makeText(getApplicationContext(), "Successs",Toast.LENGTH_SHORT).show();

                            Intent login = new Intent(getApplicationContext(), Vehicleinformation.class);
                            startActivity(login);
                            finish();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Incorrect email or password",Toast.LENGTH_SHORT).show();


                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Create a new Account", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent login = new Intent(getApplicationContext(), Registration.class);
                startActivity(login);

            }
        });
    }

}