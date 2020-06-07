package com.techno.techntecky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class rating extends AppCompatActivity {

    RatingBar ratingBar;
    Button btn;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rating);
        ActivityCompat.requestPermissions(rating.this,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        ratingBar = findViewById(R.id.rating);
        btn = findViewById(R.id.button);
        editText = findViewById(R.id.editTextTextMultiLine);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(ratingBar.getRating());
                if(s.equals("0.0"))
                {
                    Toast.makeText(rating.this, "Please Give Some Rating ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(editText.getText().toString().equals(""))
                    {
                        SmsManager mysmsmanager=SmsManager.getDefault();
                        mysmsmanager.sendTextMessage("+919897512806",null,"Rating is "+s,null,null);
                    }
                    else
                    {
                        SmsManager  mysmsmanager=SmsManager.getDefault();
                        mysmsmanager.sendTextMessage("+919897512806",null,"Rating is "+s+" and Feedback is "+editText.getText().toString(),null,null);

                    }

                    Intent i=new Intent(rating.this,MainActivity.class);
                    startActivity(i);
                    Toast.makeText(rating.this, "Thanks for Feedback = " + s, Toast.LENGTH_LONG).show();
                    editText.setText("");

                }


            }
        });
    }
}
