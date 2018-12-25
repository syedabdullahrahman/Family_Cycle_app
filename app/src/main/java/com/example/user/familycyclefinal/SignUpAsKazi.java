package com.example.user.familycyclefinal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.familycyclefinal.internet.JsonObjectRequest;
import com.example.user.familycyclefinal.internet.MySingleton;
import com.example.user.familycyclefinal.objects.Kazi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpAsKazi extends AppCompatActivity implements View.OnClickListener{

    EditText kaziUserName,kaziPassword,kaziEmail,kaziLicenceNumber;
    Button kaziSignUp;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_as_kazi);

        kaziEmail= findViewById(R.id.kaziEmail);
        kaziLicenceNumber= findViewById(R.id.kaziLicenceNumber);
        kaziPassword= findViewById(R.id.kaziPassword);
        kaziUserName= findViewById(R.id.kaziUserName);
        kaziSignUp = findViewById(R.id.kaziSignUp);

        kaziSignUp.setOnClickListener(this);
        dialog = new ProgressDialog(this);
    }



    private boolean validate() {
        if (kaziEmail.getText().toString().trim().equals(""))
            return false;
        else if (kaziLicenceNumber.getText().toString().trim().equals(""))
            return false;
        else if (kaziPassword.getText().toString().trim().equals(""))
            return false;
        else if (kaziUserName.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.kaziSignUp:
                if (!validate())
                    Toast.makeText(getBaseContext(), "Fill the empty field!", Toast.LENGTH_SHORT).show();
                else {
                    dialog.setMessage("Signing up.....");
                    dialog.show();

                    Kazi kazi = new Kazi();
                    kazi.setKaziEmail(kaziEmail.getText().toString().trim());
                    kazi.setKaziLicenceNumber(kaziLicenceNumber.getText().toString().trim());
                    kazi.setKaziPassword(kaziPassword.getText().toString().trim());
                    kazi.setKaziUserName(kaziUserName.getText().toString().trim());
                    kazi.setAuthorised("no");
                    kazi.setName("");
                    kazi.setContact("");
                    kazi.setDob("");
                    kazi.setEduBackground("");
                    kazi.setNid("");
                    kazi.setTin("");
                    kazi.setPreferedArea("");
                    kazi.setOfficeAddress("");



                    String url = "https://family-cycle.herokuapp.com/FamilyAssistance/kazi/addkazi";
                    JSONObject json = new JSONObject();
                    try {
                        json.accumulate("kaziLicenceNumber", kazi.getKaziLicenceNumber());
                        json.accumulate("kaziUserName", kazi.getKaziUserName());
                        json.accumulate("kaziEmail",kazi.getKaziEmail());
                        json.accumulate("kaziPassword", kazi.getKaziPassword());
                        json.accumulate("name", kazi.getName());
                        json.accumulate("eduBackground", kazi.getEduBackground());
                        json.accumulate("dob", kazi.getDob());
                        json.accumulate("nid",kazi.getNid());
                        json.accumulate("tin", kazi.getTin());
                        json.accumulate("officeAddress", kazi.getOfficeAddress());
                        json.accumulate("preferedArea", kazi.getPreferedArea());
                        json.accumulate("contact", kazi.getContact());
                        json.accumulate("authorised",kazi.getAuthorised());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest postRequest = new JsonObjectRequest(url, json, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (dialog.isShowing()) {
                                    dialog.dismiss();
                                }
                                String check = (String) response.get("registration");
                                if(check.equals("Success")){
                                    //Toast.makeText(getBaseContext(), "Successfully Registered.", Toast.LENGTH_LONG).show();
                                    AlertDialog alertDialog = new AlertDialog.Builder(SignUpAsKazi.this).create();
                                    alertDialog.setTitle("Congratulation !!!");
                                    alertDialog.setMessage("Your Application has been accepted. After validity check of you licence you will be able to login.");
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {


                                                    Handler handler = new Handler();
                                                    handler.postDelayed(new Runnable() {
                                                        public void run() {
                                                            Intent intent = new Intent(SignUpAsKazi.this,LoginActivity.class);
                                                            startActivity(intent);
                                                            SignUpAsKazi.this.finish();
                                                        }
                                                    }, 1);   //1 mili seconds
                                                }
                                            });
                                    alertDialog.show();


                                }
                                else {
                                    Toast.makeText(getBaseContext(), "Licence Number exists. Enter valid licence number.", Toast.LENGTH_LONG).show();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            Toast.makeText(getBaseContext(), "Registration not successful. Please try again", Toast.LENGTH_LONG).show();
                            //VolleyLog.e("Error: ", error.getMessage());
                        }
                    }) {

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type","application/json");
                            return headers;
                        }
                    };
                    MySingleton.getInstance(this).addToRequestQueue(postRequest);
                }
                break;
        }

    }
}
