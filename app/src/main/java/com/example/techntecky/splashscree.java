package com.example.techntecky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class splashscree extends AppCompatActivity {
    Animation topanimation,bottomanimation;
    ImageView logo;
    GifImageView gifImageView;
    TextView logotxt,slogantxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window=getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscree);

        topanimation= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomanimation= AnimationUtils.loadAnimation(this,R.anim.botm_anim);
        logo=findViewById(R.id.imageView2);
        gifImageView=findViewById(R.id.gifImageView);
        logotxt=findViewById(R.id.textView);
        slogantxt=findViewById(R.id.textView2);
        logo.setAnimation(topanimation);
        logotxt.setAnimation(bottomanimation);
        slogantxt.setAnimation(bottomanimation);

        getSupportActionBar().hide();
        Thread splashthread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        splashthread.start();
    }
}