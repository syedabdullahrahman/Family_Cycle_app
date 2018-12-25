package com.example.user.familycyclefinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.familycyclefinal.maps.MapsActivity;

public class Contacts extends AppCompatActivity {


    GridView lv;
    String []titles = {"Doctors","NearBy Hospitals",
            "Clinics and Diagonstic center","Ambulance"};
    int [] images = {R.drawable.doctor_list,R.drawable.hospitals,
            R.drawable.clinic,R.drawable.ambulance};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);



        lv = (GridView)findViewById(R.id.idlistview);
        Contacts.MyAdapter adapter = new Contacts.MyAdapter(getApplicationContext(),titles,images);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent = new Intent(getApplicationContext(),DoctorList.class);
                    startActivity(intent);
                }

                if(i==1){
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                }
                if(i==2){
                    Intent intent = new Intent(getApplicationContext(),Clinics.class);
                    startActivity(intent);
                }
                if(i==3){
                    Intent intent = new Intent(getApplicationContext(),Ambulance.class);
                    startActivity(intent);
                }
            }
        });


    }



    class MyAdapter extends ArrayAdapter {
        int [] imageArray;
        String[] titlesArray;
        public MyAdapter(Context context, String [] titles1, int [] image1){
            super(context,R.layout.list_item,R.id.idtitle,titles1);
            this.imageArray = image1;
            this.titlesArray=titles1;
        }
        public View getView(int position, View convertView,ViewGroup parent){
            // Inflating the layout
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.list_itemgrid,parent,false);

            //get the refernces to the view object
            ImageView myImage = (ImageView)row.findViewById(R.id.idicon);
            TextView myTitle = (TextView)row.findViewById(R.id.idtitle);

            //providing the elements of an array by specifying its position
            myImage.setImageResource(imageArray[position]);
            myTitle.setText(titlesArray[position]);

            return row;
        }
    }

}
