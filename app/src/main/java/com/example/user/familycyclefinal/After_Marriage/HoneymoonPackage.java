package com.example.user.familycyclefinal.After_Marriage;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.user.familycyclefinal.R;

public class HoneymoonPackage extends AppCompatActivity {

    ImageSwitcher imageSwitcher;
    Button previous, next;
    private static final int [] images = {R.drawable.hp1,R.drawable.hp2,R.drawable.hp3,R.drawable.hp4, R.drawable.hp5,R.drawable.hp6};
    private int imageposition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honeymoon_package);

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
