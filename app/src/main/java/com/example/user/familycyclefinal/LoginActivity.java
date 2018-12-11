package com.example.user.familycyclefinal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.familycyclefinal.Kazi.KazirMainActivity;
import com.example.user.familycyclefinal.internet.JsonObjectRequest;
import com.example.user.familycyclefinal.internet.MySingleton;
import com.example.user.familycyclefinal.objects.Kazi;
import com.example.user.familycyclefinal.objects.MarriedCouple;
import com.example.user.familycyclefinal.objects.currentUser;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences pref ;
    SharedPreferences.Editor editor ;

    TextView signUp,forgetpass;
    CheckBox keep_me_logged_in;

    EditText email, password;
    Button login;

    String userEmail ;
    String userPassword ;

    ImageView loginmarriedcouple,loginkazi;
    ProgressDialog dialog;
    private String TAG;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.signup);
        forgetpass = findViewById(R.id.textView20);
        keep_me_logged_in = findViewById(R.id.checkBox);
        login = findViewById(R.id.login);
        dialog = new ProgressDialog(this);

        loginmarriedcouple = findViewById(R.id.button12);
        loginkazi= findViewById(R.id.button13);

        signUp.setPaintFlags(signUp.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        forgetpass.setPaintFlags(forgetpass.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        keep_me_logged_in.setPaintFlags(keep_me_logged_in.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        dialog.setMessage("Continuing previous session...");
        dialog.show();
        int c=check_session();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validate().equals("ok")) {
                    Toast.makeText(getApplicationContext(), validate(), Toast.LENGTH_SHORT).show();
                } else {

                    dialog.setMessage("Logging in...");
                    dialog.show();

                    userEmail = email.getText().toString().trim();
                    userPassword = password.getText().toString().trim();

                    String url = "https://family-cycle.herokuapp.com/FamilyAssistance/kazi/login/" + userEmail + "/" + userPassword;
                    String url2 = "https://family-cycle.herokuapp.com/FamilyAssistance/mc/login/" + userEmail + "/" + userPassword;


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String userEmail1 = (String) response.get("kaziEmail");
                                        if (!userEmail1.equals("") && response.get("authorised").equals("yes")) {
                                            if (dialog.isShowing()) {
                                                dialog.dismiss();
                                            }
                                            Intent intent = new Intent(LoginActivity.this, KazirMainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                            Kazi k = new Kazi();
                                            k.makeObject(response);
                                            currentUser.getInstance().setKazi(k);

                                            if(keep_me_logged_in.isChecked())
                                            {
                                                editor.putString("userEmail",userEmail); // Storing userEmail
                                                editor.putString("userPassword",userPassword); // Storing password
                                                editor.putString("role","kazi"); // Storing password
                                                editor.commit();
                                            }

                                            finish();
                                            startActivity(intent);
                                        } else {

                                            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                                            alertDialog.setTitle("Sorry");
                                            alertDialog.setMessage("You haven't registered yet or Your email and password is still in progress of validity checking.");
                                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            Handler handler = new Handler();
                                                            handler.postDelayed(new Runnable() {
                                                                public void run() {
                                                                    Intent intent = new Intent(LoginActivity.this,LoginActivity.class);
                                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                    startActivity(intent);
                                                                    LoginActivity.this.finish();
                                                                }
                                                            }, 1);   //1 mili seconds
                                                        }
                                                    });
                                            alertDialog.show();
                                            //Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {

                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    if (dialog.isShowing()) {
                                        dialog.dismiss();
                                        }
                                }
                            });

                    JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest
                            (Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String userEmail1 = (String) response.get("mcemail");
                                        if (!userEmail1.equals("")) {
                                            if (dialog.isShowing()) {
                                                dialog.dismiss();
                                            }
                                            Intent intent = new Intent(LoginActivity.this, SelectStage.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                            MarriedCouple m = new MarriedCouple();
                                            m.makeObject(response);
                                            currentUser.getInstance().setMc(m);

                                            if(keep_me_logged_in.isChecked())
                                            {
                                                editor.putString("userEmail",userEmail); // Storing userEmail
                                                editor.putString("userPassword",userPassword); // Storing password
                                                editor.putString("role","marriedcouple"); // Storing password
                                                editor.commit();
                                            }

                                            finish();
                                            startActivity(intent);
                                        } else {

                                        }
                                    } catch (JSONException e) {

                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    if (dialog.isShowing()) {
                                        dialog.dismiss();
                                    }
                                    //Toast.makeText(getApplicationContext(), "Something went wrong. Try again 22", Toast.LENGTH_SHORT).show();

                                }
                            });
                    // Access the RequestQueue through your singleton class.
                    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
                    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest2);
                }

            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });


        loginmarriedcouple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(LoginActivity.this,SelectStage.class);
                //startActivity(intent);
            }
        });


        loginkazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(LoginActivity.this,KazirMainActivity.class);
                //startActivity(intent);
            }
        });

    }

    private int check_session() {
        userEmail = pref.getString("userEmail", "");
        userPassword =  pref.getString("userPassword", "");
        if(userEmail.equals("") && dialog.isShowing())
        {
            dialog.dismiss();
            return 0;
        }

        String role = pref.getString("role", null);
        if(role.equals("kazi"))
        {

            String url = "https://family-cycle.herokuapp.com/FamilyAssistance/kazi/login/" + userEmail + "/" + userPassword;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String userEmail1 = (String) response.get("kaziEmail");
                                if (!userEmail1.equals("") && response.get("authorised").equals("yes")) {
                                    if (dialog.isShowing()) {
                                        dialog.dismiss();
                                    }
                                    Intent intent = new Intent(LoginActivity.this, KazirMainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    Kazi k = new Kazi();
                                    k.makeObject(response);
                                    currentUser.getInstance().setKazi(k);

                                    editor.putString("userEmail",userEmail); // Storing userEmail
                                    editor.putString("userPassword",userPassword); // Storing password
                                    editor.putString("role","kazi"); // Storing password

                                    editor.commit();

                                    finish();
                                    startActivity(intent);
                                } else {

                                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                                    alertDialog.setTitle("Sorry");
                                    alertDialog.setMessage("You haven't registered yet or Your email and password is still in progress of validity checking.");
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Handler handler = new Handler();
                                                    handler.postDelayed(new Runnable() {
                                                        public void run() {
                                                            Intent intent = new Intent(LoginActivity.this,LoginActivity.class);
                                                            startActivity(intent);
                                                            LoginActivity.this.finish();
                                                        }
                                                    }, 1);   //1 mili seconds
                                                }
                                            });
                                    alertDialog.show();
                                    //Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }
                    });

            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

        }
        else
        {

            String url2 = "https://family-cycle.herokuapp.com/FamilyAssistance/mc/login/" + userEmail + "/" + userPassword;

            JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest
                    (Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String userEmail1 = (String) response.get("mcemail");
                                if (!userEmail1.equals("")) {
                                    if (dialog.isShowing()) {
                                        dialog.dismiss();
                                    }
                                    Intent intent = new Intent(LoginActivity.this, SelectStage.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    MarriedCouple m = new MarriedCouple();
                                    m.makeObject(response);
                                    currentUser.getInstance().setMc(m);

                                    editor.putString("userEmail",userEmail); // Storing userEmail
                                    editor.putString("password",userPassword); // Storing password
                                    editor.putString("role","marriedcouple"); // Storing password
                                    editor.commit();

                                    finish();
                                    startActivity(intent);
                                } else {

                                }
                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            //Toast.makeText(getApplicationContext(), "Something went wrong. Try again 22", Toast.LENGTH_SHORT).show();

                        }
                    });
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest2);
        }
        return 0;

    }

    private String validate() {
        if (email.getText().toString().trim().equals(""))
            return "Enter Email";
        else if (password.getText().toString().trim().equals(""))
            return "Enter Password";
        else
            return "ok";
    }
}
