package com.example.user.familycyclefinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.familycyclefinal.internet.JsonObjectRequest;
import com.example.user.familycyclefinal.internet.MySingleton;
import com.example.user.familycyclefinal.objects.MarriedCouple;
import com.example.user.familycyclefinal.objects.currentUser;

import org.json.JSONObject;

public class Married_Couple_Profile_Activity extends AppCompatActivity {

    ImageView profileedit;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_married__couple_);

        profileedit = (ImageView)findViewById(R.id.profileedit);
        profileedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Married_Couple_Profile_Activity.this,Married_couple_profile_edit.class);
                startActivity(intent);
            }
        });


        dialog = new ProgressDialog(this);

        dialog.setMessage("");
        dialog.show();


        final TextView name = (TextView)findViewById(R.id.myname);
        final TextView spousename = (TextView)findViewById(R.id.spousename);
        final TextView mydob = (TextView)findViewById(R.id.mydob);
        final TextView spousedob = (TextView)findViewById(R.id.spousedob);
        final TextView marriagedate = (TextView)findViewById(R.id.marriagedate);
        final TextView emailaddress = (TextView)findViewById(R.id.emailaddress);

        final MarriedCouple m = currentUser.getInstance().getMc();
        String url = "https://family-cycle.herokuapp.com/FamilyAssistance/mc/findmc?email=" + m.getMcemail();
        //String url = "https://family-cycle.herokuapp.com/FamilyAssistance/mc/findmc?email=";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        MarriedCouple tmp = new MarriedCouple();
                        tmp.makeObject(response);
                        currentUser.getInstance().setMc(tmp);
                        name.setText(tmp.getName());
                        spousename.setText(tmp.getSpouse_name());
                        mydob.setText(tmp.getDob());
                        spousedob.setText(tmp.getSp_dob());
                        marriagedate.setText(tmp.getMarriage_date());
                        emailaddress.setText(tmp.getMcemail());


                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), "Something went wrong. Try again 11", Toast.LENGTH_SHORT).show();
                    }
                });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);



    }
}
