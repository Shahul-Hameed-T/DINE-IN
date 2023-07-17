package com.smile.imagetotext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.SecureRandom;

public class Final_Pay extends AppCompatActivity {


    EditText name,num,amout,upipin;
    Button pay;
    final Context context = this;
    ProgressDialog pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final__pay);

        name = findViewById(R.id.payname);
        num = findViewById(R.id.paynumber);
        pay = findViewById(R.id.pafconf);
        upipin = findViewById(R.id.upipin);

        generateRandomNumber ();

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String Otpname = name.getText().toString();
                final String Otppass = num.getText().toString();
              //  String Upi = upipin.getText().toString();

                if (Otpname.isEmpty())
                {
                    name.setError("Name");

                } else if (Otppass.isEmpty())
                {
                    num.setError("Number");
                }
                else
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("OTP", MODE_PRIVATE);
                    final String otpn = sharedPreferences.getString("ot", " ");


                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(Otppass, null, "Dear, " + Otpname + " Your OTP is " + otpn, null, null);

                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.garbage_weight, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    alertDialogBuilder.setView(promptsView);

                    final EditText otpp = (EditText) promptsView.findViewById(R.id.weight);

                    AlertDialog.Builder builder = new AlertDialog.Builder(Final_Pay.this);
                    builder.setCancelable(false);
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Submit",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            String num = otpp.getText().toString();

                                            pb = new ProgressDialog(Final_Pay.this);
                                            pb.setMessage("Loading");
                                            pb.setTitle("Please Wait!");
                                            pb.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                            pb.show();
                                            pb.setCancelable(false);
                                            new Thread(new Runnable() {
                                                public void run() {
                                                    try {
                                                        Thread.sleep(10000);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                    pb.dismiss();
                                                }
                                            }).start();

                                            if (num.equals(otpn)) {
                                                SmsManager sms = SmsManager.getDefault();
                                                sms.sendTextMessage(Otppass, null, "Your Payment was Complected Successfully ", null, null);
                                                Intent login = new Intent(getApplicationContext(), Home.class);
                                                startActivity(login);
                                                finish();
                                            } else {
                                                SmsManager sms = SmsManager.getDefault();
                                                sms.sendTextMessage(Otppass, null, "Your OTP is mismatch enter correctly", null, null);

                                            }
                                        }
                                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });
    }

    private int generateRandomNumber() {
        int randomNumber;
        int range = 9;
        int length = 4;
        SecureRandom secureRandom = new SecureRandom();
        String s = "";
        for (int i = 0; i < 4; i++) {
            int number = secureRandom.nextInt(range);
            if (number == 0 && i == 0)
            {
                i = -1;
                continue;
            }
            s = s + number;
        }
        randomNumber = Integer.parseInt(s);

        SharedPreferences sharedPreferences = getSharedPreferences("OTP" , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ot" , s);
        editor.commit();
        return randomNumber;


    }
}