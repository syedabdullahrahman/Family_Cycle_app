package com.example.user.familycyclefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.user.familycyclefinal.After_Marriage.AfterMarriageActivity;
import com.example.user.familycyclefinal.BabyCare.BabyCareActivity;
import com.example.user.familycyclefinal.DuringPragnancy.DuringPragnancyActivity;
import com.example.user.familycyclefinal.Family_Planning.FamilyPlanningActivity;

public class SelectStage extends AppCompatActivity {
    ImageView aftermarriage,familyplanning,duringpragnancy,babycare;
    Button contacts, help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stage);

        aftermarriage = (ImageView)findViewById(R.id.imageView17);
        familyplanning = (ImageView)findViewById(R.id.imageView18);
        duringpragnancy = (ImageView)findViewById(R.id.imageView19);
        babycare = (ImageView)findViewById(R.id.imageView20);
        contacts = (Button)findViewById(R.id.button6);
        help = (Button)findViewById(R.id.button4);

        aftermarriage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectStage.this,AfterMarriageActivity.class);
                startActivity(intent);
            }
        });

        familyplanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectStage.this, FamilyPlanningActivity.class);
                startActivity(intent);
            }
        });

        duringpragnancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectStage.this, DuringPragnancyActivity.class);
                startActivity(intent);
            }
        });

        babycare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectStage.this, BabyCareActivity.class);
                startActivity(intent);
            }
        });
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectStage.this, Contacts.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectStage.this, HelpSupport.class);
                startActivity(intent);
            }
        });



    }
}
