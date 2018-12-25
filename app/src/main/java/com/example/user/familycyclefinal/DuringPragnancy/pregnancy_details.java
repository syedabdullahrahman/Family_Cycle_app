package com.example.user.familycyclefinal.DuringPragnancy;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.user.familycyclefinal.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.text.DateFormat.FULL;

public class pregnancy_details extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnancy_details);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DatePickerFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });
    }
    @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String currentdatestring = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setText(currentdatestring);

/// first trimester
        Calendar f = Calendar.getInstance();
        f.set(Calendar.YEAR,year);
        f.set(Calendar.MONTH,month);
        f.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String firsttrimester = DateFormat.getDateInstance(DateFormat.FULL).format(f.getTime());

        TextView textViewf = (TextView) findViewById(R.id.textView32);

        textViewf.setText(firsttrimester);


        /// second trimester
        Calendar s = Calendar.getInstance();
        s.set(Calendar.YEAR,year);
        s.set(Calendar.MONTH,month+3);
        s.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String secondtrimester = DateFormat.getDateInstance(DateFormat.FULL).format(s.getTime());

        TextView textViews = (TextView) findViewById(R.id.textView33);

        textViews.setText(secondtrimester);



        /// third trimester
        Calendar t = Calendar.getInstance();
        t.set(Calendar.YEAR,year);
        t.set(Calendar.MONTH,month+6);
        t.set(Calendar.DAY_OF_MONTH,dayOfMonth+14);

        String thirdtrimester = DateFormat.getDateInstance(DateFormat.FULL).format(t.getTime());

        TextView textViewt = (TextView) findViewById(R.id.textView35);

        textViewt.setText(thirdtrimester);


 // delivery
        Calendar d = Calendar.getInstance();
        d.set(Calendar.YEAR,year);
        d.set(Calendar.MONTH,month+10);
        d.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String delivery = DateFormat.getDateInstance(DateFormat.FULL).format(d.getTime());

        TextView textVieww = (TextView) findViewById(R.id.textView2);

        textVieww.setText(delivery);

    }



}
