package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity {


    EditText adminid,adminpassword;
    Button adminslogin;
    String use= "Admin";
    String pwd= "Admin@123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        adminid = findViewById(R.id.adminid);
        adminpassword =  findViewById(R.id.adminpassword);
        adminslogin = findViewById(R.id.adminslogin);

        adminslogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String AId = adminid.getText().toString();
                String APass = adminpassword.getText().toString();

                if (AId.isEmpty()) {
                    adminid.setError("Admin ID");
                } else if (APass.isEmpty()) {
                    adminpassword.setError("Admin Password");

                } else if ((use.equals(AId)) && (pwd.equals(APass)))
                {
                    Intent in=new Intent(Admin.this,Admin_main.class);
                    startActivity(in);
                    Toast.makeText(getApplicationContext(), "Successfully Admin Login", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Incorrect Username Password", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
}