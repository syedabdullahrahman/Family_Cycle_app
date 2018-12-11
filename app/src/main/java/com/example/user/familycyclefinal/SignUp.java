package com.example.user.familycyclefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SignUp extends AppCompatActivity {

    ImageView kazi,marriedcoupple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        kazi = (ImageView)findViewById(R.id.imageView14);
        marriedcoupple = (ImageView)findViewById(R.id.imageView16);

        kazi.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SignUp.this,SignUpAsKazi.class);
                        startActivity(intent);

                    }
                }
        );

        marriedcoupple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,signupAsMarriedcouple.class);
                startActivity(intent);
            }
        });
    }
}
