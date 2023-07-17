package com.smile.imagetotext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.firebase.ml.vision.text.RecognizedLanguage;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView img, capture, detect;
    private final static int requst = 1888;
    Bitmap Picture;
    final Context context = this;
    AlertDialog.Builder builder;
    SQLiteDatabase db;
    String resultText;
    ImageView refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.imageView);
        capture = findViewById(R.id.imageView2);
        detect = findViewById(R.id.imageView3);
        refresh = findViewById(R.id.refresh);

    }

    @Override
    protected void onResume() {
        super.onResume();

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, requst);


            }
        });

        detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent bk = new Intent(MainActivity.this, Newuser.class);
                bk.putExtra("Num",resultText );
                startActivity(bk);

                //generateRandomNumber ();

            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                startdetect();
                new Handler().postDelayed(new  Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        /*Intent intent = new Intent(getApplicationContext(),Newuser.class);
                        startActivity(intent);*/

                        progressDialog.dismiss();



                    }
                }, 3000);
            }
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==requst) {

            Bundle extras = data.getExtras();
            Picture = (Bitmap) extras.get("data");
            img.setImageBitmap(Picture);

        }
    }

    public void startdetect()
    {
        FirebaseVisionImage fimage = FirebaseVisionImage.fromBitmap(Picture);
        FirebaseVisionTextRecognizer detect = FirebaseVision.getInstance().getOnDeviceTextRecognizer();

        detect.processImage(fimage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText result) {

                resultText = result.getText();

                Toast.makeText(MainActivity.this, resultText, Toast.LENGTH_SHORT).show();

                for (FirebaseVisionText.TextBlock block: result.getTextBlocks()) {
                    String blockText = block.getText();
                    Float blockConfidence = block.getConfidence();
                    List<RecognizedLanguage> blockLanguages = block.getRecognizedLanguages();
                    Point[] blockCornerPoints = block.getCornerPoints();
                    Rect blockFrame = block.getBoundingBox();
                    for (FirebaseVisionText.Line line: block.getLines()) {
                        String lineText = line.getText();
                        Float lineConfidence = line.getConfidence();
                        List<RecognizedLanguage> lineLanguages = line.getRecognizedLanguages();
                        Point[] lineCornerPoints = line.getCornerPoints();
                        Rect lineFrame = line.getBoundingBox();
                        for (FirebaseVisionText.Element element: line.getElements()) {
                            String elementText = element.getText();
                            Float elementConfidence = element.getConfidence();
                            List<RecognizedLanguage> elementLanguages = element.getRecognizedLanguages();
                            Point[] elementCornerPoints = element.getCornerPoints();
                            Rect elementFrame = element.getBoundingBox();
                        }
                    }
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

   /* public int generateRandomNumber() {
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
    }*/
    }

//OTP

    /*LayoutInflater li = LayoutInflater.from(context);
    View promptsView = li.inflate(R.layout.garbage_weight, null);


    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptsView);

final EditText otpp = (EditText) promptsView.findViewById(R.id.weight);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        alertDialogBuilder
        .setCancelable(true)
        .setPositiveButton("Submit",
        new DialogInterface.OnClickListener() {
public void onClick(DialogInterface dialog, int id) {

        String num = otpp.getText().toString();


        SharedPreferences sharedPreferences = getSharedPreferences("OTP", MODE_PRIVATE);
        String nu = sharedPreferences.getString("ot", " ");

        if (num.equals(nu)) {

        SharedPreferences validate = getSharedPreferences("Number", MODE_PRIVATE);
        value = validate.getString("Num", " ");
        db = openOrCreateDatabase("Vehicle.db", MODE_PRIVATE, null);
        try {
        Cursor c = db.rawQuery("select * from cardetails where REG_NO ='" + value + "'", null);
        if (c != null) {
        if (c.moveToFirst()) {
        do {

        String name = c.getString(1);
        String age = c.getString(2);
        String dob = c.getString(3);
        String licno = c.getString(4);
        String licex = c.getString(5);
        String regn = c.getString(6);
        String regdat = c.getString(7);
        String mode = c.getString(8);
        String addd = c.getString(9);
        String mobno = c.getString(10);
        String mails = c.getString(11);


        SharedPreferences sps = getSharedPreferences("OP", MODE_PRIVATE);
        SharedPreferences.Editor edi = sps.edit();
        edi.putString("ot", name);
        edi.putString("ag", age);
        edi.putString("db", dob);
        edi.putString("lino", licno);
        edi.putString("lx", licex);
        edi.putString("rn", regn);
        edi.putString("rd", regdat);
        edi.putString("vhname", mode);
        edi.putString("ad", addd);
        edi.putString("mn", mobno);
        edi.putString("eml", mails);
        edi.commit();




        } while (c.moveToNext());
        }

        }
         }
         catch (Exception e)
         {
        Toast.makeText(getApplicationContext(), "No Data Here!", Toast.LENGTH_SHORT).show();
        }
        }
         else

          {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("8428375380", null, "Your OTP is mismatch enter correctly", null, null);

        }

        }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();*/
