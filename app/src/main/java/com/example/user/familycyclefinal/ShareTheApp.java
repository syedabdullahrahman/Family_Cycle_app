package com.example.user.familycyclefinal;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ShareTheApp extends AppCompatActivity {

    ImageView gmail,facebook,whatsapp,messenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_the_app);

    gmail = (ImageView)findViewById(R.id.imageView9);
    facebook = (ImageView)findViewById(R.id.imageView5);
    whatsapp = (ImageView)findViewById(R.id.imageView8);
    messenger = (ImageView)findViewById(R.id.imageView10);

    gmail.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(Intent.ACTION_SEND);
            String[] recipients={"The_mail_address_you_want_to_send@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
            intent.putExtra(Intent.EXTRA_TEXT,"Apk file of the app here...");
            intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent, "Send mail"));
        }
    });

    facebook.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String YourPageURL = "https://www.facebook.com/";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));

            startActivity(browserIntent);
        }
    });

    whatsapp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            PackageManager pm=getPackageManager();
            try {

                Intent waIntent = new Intent(Intent.ACTION_SEND);
                waIntent.setType("text/plain");
                String text = "Share tha app (Download link from the google playstore)";

                PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                //Check if package exists or not. If not then code
                //in catch block will be called
                waIntent.setPackage("com.whatsapp");

                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(waIntent, "Share with"));

            } catch (PackageManager.NameNotFoundException e) {
                //Toast.makeText(this, "WhatsApp not Installed", LENGTH_LONG).show();
            }


        }
    });

    messenger.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", "Enter mobile number");
            smsIntent.putExtra("sms_body","Google playstore link of the app");
            startActivity(smsIntent);
        }
    });





    }
}
