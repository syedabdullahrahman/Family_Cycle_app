package com.example.user.familycyclefinal.DuringPragnancy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.familycyclefinal.User_post_here_for_allstage;
import com.example.user.familycyclefinal.HomePage_AllStageActivity;
import com.example.user.familycyclefinal.R;

public class DuringPragnancyActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {

                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    transaction.replace(R.id.container,new HealthTipsFragment_dp()).commit();
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    transaction.replace(R.id.container,new FeaturesFragment_dp()).commit();
                    return true;
                case R.id.camera_launch:
                    //mTextMessage.setText(R.string.title_notifications);
                    transaction.replace(R.id.container,new CamraFragment_dp()).commit();
                    return true;
                case R.id.navigation_query:
                    //mTextMessage.setText(R.string.title_notifications);
                    transaction.replace(R.id.container,new QueryFragment_dp()).commit();
                    return true;
                case R.id.chat_bot:
                    //mTextMessage.setText(R.string.title_notifications);
                    transaction.replace(R.id.container,new ChatbotFragment_dp()).commit();
                    return true;
            }
            return false;
        }
    };




    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.my_profile_cust) {
            Intent i = new Intent(DuringPragnancyActivity.this,HomePage_AllStageActivity.class);
            startActivity(i);
        }

        if (id == R.id.userpost) {
            Intent i = new Intent(DuringPragnancyActivity.this,User_post_here_for_allstage.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }












    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_during_pragnancy);

       // mTextMessage = (TextView) findViewById(R.id.message);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,new HealthTipsFragment_dp()).commit();
    }

}
