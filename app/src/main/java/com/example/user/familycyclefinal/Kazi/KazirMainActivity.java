package com.example.user.familycyclefinal.Kazi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.familycyclefinal.R;

public class KazirMainActivity extends AppCompatActivity {

   // private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.kaziprofile:
                    transaction.replace(R.id.container,new KaziProfileFragment()).commit();
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.registered:
                    //mTextMessage.setText(R.string.title_notifications);
                    transaction.replace(R.id.container,new RegisteredCoupleFragment()).commit();
                    return true;
                case R.id.fees:
                    //mTextMessage.setText(R.string.title_notifications);
                    transaction.replace(R.id.container,new FeesAndOthersFragment()).commit();
                    return true;
            }
            return false;
        }
    };



    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.form) {
            Intent i = new Intent(KazirMainActivity.this,RegistrationFormActivity.class);
            startActivity(i);
        }
        if (id == R.id.my_profile_cust) {
            Intent i = new Intent(KazirMainActivity.this,HomepageKazi.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kazir_main);

        // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,new KaziProfileFragment()).commit();
    }

}
