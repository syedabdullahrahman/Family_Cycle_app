package com.example.user.familycyclefinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splashscreen extends AppCompatActivity {
    ImageView logo,name;
    Animation frombottom,fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        logo= (ImageView) findViewById(R.id.logo);
        name= (ImageView) findViewById(R.id.name);

        frombottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop= AnimationUtils.loadAnimation(this,R.anim.fromtop);
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {


                    logo.setAnimation(frombottom);
                    name.setAnimation(fromtop);
                    sleep(2000);
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

}

