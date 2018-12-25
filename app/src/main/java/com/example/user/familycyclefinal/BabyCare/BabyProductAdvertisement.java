package com.example.user.familycyclefinal.BabyCare;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.user.familycyclefinal.R;

public class BabyProductAdvertisement extends AppCompatActivity {

    ImageSwitcher imageSwitcher;
    Button previous, next;
    private static final int [] images = {R.drawable.bp1,R.drawable.bp2,R.drawable.bp3,R.drawable.bp4, R.drawable.bp5,
            R.drawable.bp6,R.drawable.bp7,R.drawable.bp8,R.drawable.bp9,R.drawable.bp10};
    private int imageposition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_product_advertisement);

        imageSwitcher = (ImageSwitcher)findViewById(R.id.idimageswitcher);
        previous = (Button)findViewById(R.id.buttonprevious);
        next = (Button)findViewById(R.id.buttonnext);


        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT));

                return imageView;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageposition < images.length - 1){
                    imageposition = imageposition + 1;
                    imageSwitcher.setBackgroundResource(images[imageposition]);

                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageposition > 0) {
                    imageposition = imageposition - 1;
                    imageSwitcher.setBackgroundResource(images[imageposition]);

                }
            }
        });

    }
}
