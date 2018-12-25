package com.example.user.familycyclefinal.Kazi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.user.familycyclefinal.AboutActivity;
import com.example.user.familycyclefinal.ContactUs;
import com.example.user.familycyclefinal.LoginActivity;
import com.example.user.familycyclefinal.R;
import com.example.user.familycyclefinal.RateApp;
import com.example.user.familycyclefinal.ShareTheApp;

public class HomepageKazi extends AppCompatActivity {
    SharedPreferences pref ;
    SharedPreferences.Editor editor ;
    ImageView myprofile,about,contactus,logout,shareapp,rateus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_kazi);


        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        myprofile = (ImageView)findViewById(R.id.imageView27);
        about = (ImageView)findViewById(R.id.imageView30);
        contactus = (ImageView)findViewById(R.id.imageView31);
        logout = (ImageView)findViewById(R.id.imageView32);
        shareapp = (ImageView)findViewById(R.id.imageView33);
        rateus = (ImageView)findViewById(R.id.imageView35);


        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomepageKazi.this,RateApp.class);
                startActivity(intent);
            }
        });


        shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomepageKazi.this,ShareTheApp.class);
                startActivity(intent);
            }
        });

        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomepageKazi.this,KazirMainActivity.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomepageKazi.this,AboutActivity.class);
                startActivity(intent);
            }
        });
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomepageKazi.this,ContactUs.class);
                startActivity(intent);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.remove("userEmail"); // will delete key name
                editor.remove("userPassword"); // will delete key email
                editor.remove("role");

                editor.commit(); // commit changes
                editor.clear();
                editor.commit(); // commit changes

                Intent intent = new Intent( HomepageKazi.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);

            }
        });
    }
}
