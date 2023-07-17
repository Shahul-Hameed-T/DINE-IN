  package com.smile.imagetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Police_Login extends AppCompatActivity {

    EditText policeid,policepassword;
    Button policelogin;
    String use= "Police";
    String pwd= "Police@123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police__login);

        policeid = findViewById(R.id.policeid);
        policepassword =  findViewById(R.id.policepassword);
        policelogin = findViewById(R.id.policelogin);


        policelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String PId = policeid.getText().toString();
                String PPass = policepassword.getText().toString();

                if (PId.isEmpty()) {
                    policeid.setError("Police ID");
                } else if (PPass.isEmpty()) {
                    policepassword.setError("Password");
                } else if ((use.equals(PId)) && (pwd.equals(PPass)))
                {

                    Intent book = new Intent(Police_Login.this, MainActivity.class);
                    startActivity(book);
                }
                else
                    {
                    Toast.makeText(getApplicationContext(), "Incorrect Username Password", Toast.LENGTH_SHORT).show();
                }

            }

            });

    }
}