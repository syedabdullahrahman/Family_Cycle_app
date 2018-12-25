package com.example.user.familycyclefinal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.familycyclefinal.internet.JsonObjectRequest;
import com.example.user.familycyclefinal.internet.MySingleton;
import com.example.user.familycyclefinal.objects.MarriedCouple;
import com.example.user.familycyclefinal.objects.currentUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Married_couple_profile_edit extends AppCompatActivity {

    EditText name, spousename, ur_dob, spouse_dob, marriage_date, uremail;
    public static final int RESULT_LOAD_IMAGE1 =1 ;
    public static final int RESULT_LOAD_IMAGE2 =2 ;
    Button submit;
    ImageView urimage, spouseimage;
    AlertDialog dialog;




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode== RESULT_OK && data != null){

            if(requestCode == RESULT_LOAD_IMAGE1){
                Uri selectedimage1 = data.getData();
                urimage.setImageURI(selectedimage1);
            }

            if(requestCode == RESULT_LOAD_IMAGE2){
                Uri selectedimage2 = data.getData();
                spouseimage.setImageURI(selectedimage2);
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_married_couple_profile_edit);

        name = (EditText)findViewById(R.id.yourname);
        spousename = (EditText)findViewById(R.id.spousename);
        ur_dob = (EditText)findViewById(R.id.yourbirth);
        spouse_dob = (EditText)findViewById(R.id.spousebirth);
        marriage_date = (EditText)findViewById(R.id.marriagedate);
        uremail = (EditText)findViewById(R.id.mailaddress);

        urimage = (ImageView)findViewById(R.id.nijerpic);
        spouseimage= (ImageView)findViewById(R.id.imageView3);

        submit = (Button)findViewById(R.id.submit);
        dialog = new ProgressDialog(this);

        final MarriedCouple tmp = currentUser.getInstance().getMc();
        name.setText(tmp.getName());
        spousename.setText(tmp.getSpouse_name());
        ur_dob.setText(tmp.getDob());
        spouse_dob.setText(tmp.getSp_dob());
        marriage_date.setText(tmp.getMarriage_date());
        uremail.setText(tmp.getMcemail());

        /// married couple's class has to be changed

    urimage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE1);
        }
    });

    spouseimage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE2);
        }
    });


    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.setMessage("Updating Profile...");
            dialog.show();

            tmp.setName(name.getText().toString().trim());
            tmp.setSpouse_name(spousename.getText().toString().trim());
            tmp.setDob(ur_dob.getText().toString().trim());
            tmp.setSp_dob(spouse_dob.getText().toString().trim());
            tmp.setMarriage_date(marriage_date.getText().toString().trim());
            tmp.setMcemail(uremail.getText().toString().trim());


            String url = "https://family-cycle.herokuapp.com/FamilyAssistance/mc/updatemc";

            JSONObject json = new JSONObject();
            try {
                json.accumulate("mcusername", tmp.getMcusername());
                json.accumulate("name", tmp.getName());
                json.accumulate("spouse_name",tmp.getSpouse_name());
                json.accumulate("sp_dob", tmp.getSp_dob());
                json.accumulate("dob", tmp.getDob());
                json.accumulate("marriage_date", tmp.getMarriage_date());
                json.accumulate("password", tmp.getPassword());
                json.accumulate("mcemail",tmp.getMcemail());

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
                            AlertDialog alertDialog = new AlertDialog.Builder(Married_couple_profile_edit.this).create();
                            alertDialog.setTitle("Congratulation !!!");
                            alertDialog.setMessage("Your profile has been updated successfully.");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    Intent intent = new Intent(Married_couple_profile_edit.this,Married_Couple_Profile_Activity.class);
                                                    startActivity(intent);
                                                    Married_couple_profile_edit.this.finish();
                                                }
                                            }, 1);   //1 mili seconds
                                        }
                                    });
                            alertDialog.show();
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
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest);




























        }
    });

    }
}
