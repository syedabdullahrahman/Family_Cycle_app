package com.example.user.familycyclefinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.familycyclefinal.internet.JsonObjectRequest;
import com.example.user.familycyclefinal.internet.MySingleton;
import com.example.user.familycyclefinal.objects.MarriedCouple;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signupAsMarriedcouple extends AppCompatActivity implements View.OnClickListener {

    EditText kaziUserName,kaziPassword,kaziEmail;
    Button kaziSignUp;
    ProgressDialog dialog;
    Button selectstage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_as_marriedcouple);

        kaziEmail= findViewById(R.id.kaziEmail);
        kaziPassword= findViewById(R.id.kaziPassword);
        kaziUserName= findViewById(R.id.kaziUserName);
        kaziSignUp = findViewById(R.id.button5);

        kaziSignUp.setOnClickListener(this);
        dialog = new ProgressDialog(this);

        selectstage = findViewById(R.id.stagepopup);

        selectstage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(signupAsMarriedcouple.this,selectstage);
                popupMenu.getMenuInflater().inflate(R.menu.popup_stage,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(getApplication(),"Item clicked: "+ menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private boolean validate() {
        if (kaziEmail.getText().toString().trim().equals(""))
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
            case R.id.button5:
                if (!validate())
                    Toast.makeText(getBaseContext(), "Fill the empty field!", Toast.LENGTH_SHORT).show();
                else {
                    dialog.setMessage("Signing up.....");
                    dialog.show();

                    MarriedCouple mc = new MarriedCouple();
                    mc.setMcemail(kaziEmail.getText().toString().trim());
                    mc.setPassword(kaziPassword.getText().toString().trim());
                    mc.setMcusername(kaziUserName.getText().toString().trim());



                    String url = "https://family-cycle.herokuapp.com/FamilyAssistance/mc/addmc";
                    JSONObject json = new JSONObject();
                    try {
                        json.accumulate("mcusername", mc.getMcusername());
                        json.accumulate("mcemail", mc.getMcemail());
                        json.accumulate("password", mc.getPassword());

                        json.accumulate("name", mc.getName());
                        json.accumulate("spouse_name", mc.getSpouse_name());
                        json.accumulate("dob", mc.getDob());
                        json.accumulate("sp_dob", mc.getSp_dob());
                        json.accumulate("marriage_date", mc.getMarriage_date());


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
                                    Toast.makeText(getBaseContext(), "Successfully Registered.", Toast.LENGTH_LONG).show();
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            Intent intent = new Intent(signupAsMarriedcouple.this,LoginActivity.class);
                                            startActivity(intent);
                                            signupAsMarriedcouple.this.finish();
                                        }
                                    }, 1000);   //1 seconds

                                }
                                else {
                                    Toast.makeText(getBaseContext(), "Same User Exists", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(getBaseContext(), "Registered not successful. Try again", Toast.LENGTH_LONG).show();
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

