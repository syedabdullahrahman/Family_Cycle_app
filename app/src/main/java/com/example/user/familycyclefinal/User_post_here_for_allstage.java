package com.example.user.familycyclefinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.familycyclefinal.After_Marriage.AfterMarriageActivity;
import com.example.user.familycyclefinal.BabyCare.BabyCareActivity;
import com.example.user.familycyclefinal.DuringPragnancy.DuringPragnancyActivity;
import com.example.user.familycyclefinal.Family_Planning.FamilyPlanningActivity;
import com.example.user.familycyclefinal.internet.JsonObjectRequest;
import com.example.user.familycyclefinal.internet.MySingleton;
import com.example.user.familycyclefinal.objects.MarriedCouple;
import com.example.user.familycyclefinal.objects.Post;
import com.example.user.familycyclefinal.objects.currentUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class User_post_here_for_allstage extends AppCompatActivity {

    public static final int RESULT_LOAD_IMAGE =1 ;
    ImageView uploadimg;


    EditText p_title, p_description;
    Button submit;
    ProgressDialog dialog;
    CheckBox af, fp, dp, bc;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_LOAD_IMAGE && resultCode== RESULT_OK && data != null){
            Uri selectedimage = data.getData();
            uploadimg.setImageURI(selectedimage);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post_here_for_allstage);


        uploadimg = (ImageView)findViewById(R.id.uploadpropic);

        uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });





        p_title = (EditText)findViewById(R.id.titledeo);
        p_description = (EditText)findViewById(R.id.descriptiondeo);
        submit = (Button)findViewById(R.id.submit);
        af = (CheckBox)findViewById(R.id.checkaf);
        fp = (CheckBox)findViewById(R.id.checkfp);
        dp = (CheckBox)findViewById(R.id.checkdp);
        bc = (CheckBox)findViewById(R.id.checkbc);

        dialog = new ProgressDialog(this);




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validate().equals("ok")) {
                    Toast.makeText(getApplicationContext(), validate(), Toast.LENGTH_SHORT).show();
                } else {



                    dialog.setMessage("Posting the note. Please wait.....");
                    dialog.show();

                    String ptitle = p_title.getText().toString().trim();
                    String pdescription = p_description.getText().toString().trim();
                    String pstage = "";

                    if(af.isChecked()== true){
                        pstage = "After Marriage";
                    }
                    if(fp.isChecked()== true){
                        pstage = "Family Planning";
                    }
                    if(dp.isChecked()== true){
                        pstage = "During Planning";
                    }
                    if(bc.isChecked()== true){
                        pstage = "Baby Care";
                    }

                    final MarriedCouple mc = currentUser.getInstance().getMc();


                    Post pr = new Post(ptitle,pdescription,pstage,mc.getMcemail());

                    String url = "https://family-cycle.herokuapp.com/FamilyAssistance/post/addpost";
                    JSONObject json = new JSONObject();

                    try {
                        json.accumulate("title", ptitle);
                        json.accumulate("description", pdescription);
                        json.accumulate("stage",pstage);
                        json.accumulate("user_id",mc.getMcemail());


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
                                if (check.equals("Success")) {
                                    Toast.makeText(getBaseContext(), "Successfully Note has posted.", Toast.LENGTH_LONG).show();
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {

                                            if (af.isChecked() == true){
                                                Intent intent = new Intent(User_post_here_for_allstage.this, AfterMarriageActivity.class);
                                                User_post_here_for_allstage.this.finish();
                                                startActivity(intent);
                                            }
                                            if (fp.isChecked() == true){
                                                Intent intent = new Intent(User_post_here_for_allstage.this, FamilyPlanningActivity.class);
                                                User_post_here_for_allstage.this.finish();
                                                startActivity(intent);
                                            }
                                            if (dp.isChecked() == true){
                                                Intent intent = new Intent(User_post_here_for_allstage.this, DuringPragnancyActivity.class);
                                                User_post_here_for_allstage.this.finish();
                                                startActivity(intent);
                                            }

                                            if (bc.isChecked() == true){
                                                Intent intent = new Intent(User_post_here_for_allstage.this, BabyCareActivity.class);
                                                User_post_here_for_allstage.this.finish();
                                                startActivity(intent);
                                            }


                                        }
                                    }, 1000);   //1 seconds

                                } else {
                                    //Toast.makeText(getBaseContext(), " ", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(getBaseContext(), "Error occurred. Please try again.", Toast.LENGTH_LONG).show();
                            //VolleyLog.e("Error: ", error.getMessage());
                        }
                    }) {

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type", "application/json");
                            return headers;
                        }
                    };
                    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest);
                }
            }
        });




    }


    private String validate() {
        if (p_title.getText().toString().trim().equals(""))
            return "Please Enter Note Title";
        else if (p_description.getText().toString().trim().equals(""))
            return "Please Enter Note Description";
        else
            return "ok";
    }



}
