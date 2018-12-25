
package com.example.user.familycyclefinal.Kazi;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.familycyclefinal.R;
import com.example.user.familycyclefinal.internet.JsonObjectRequest;
import com.example.user.familycyclefinal.internet.MySingleton;
import com.example.user.familycyclefinal.internet.VolleyMultipartRequest;
import com.example.user.familycyclefinal.objects.Kazi;
import com.example.user.familycyclefinal.objects.currentUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KaziProfileEdit extends AppCompatActivity {

    public static final int RESULT_LOAD_IMAGE =1 ;

    EditText name,edu,dob,nid,tin,email,licen,office,pref,cont;
    ImageView uploadpro;
    Button submit;
    AlertDialog  dialog;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_LOAD_IMAGE && resultCode== RESULT_OK && data != null){
            Uri selectedimage = data.getData();
            uploadpro.setImageURI(selectedimage);
            //getting the image Uri
            Uri imageUri = data.getData();
            try {
                //getting bitmap object from uri
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                //calling the method uploadBitmap to upload image
                uploadBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadBitmap(final Bitmap bitmap) {

        //our custom volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST,"https://family-cycle.herokuapp.com/FamilyAssistance/file/upload",
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            /*
             * If you want to add more parameters with the image
             * you can do it here
             * here we have only one parameter with the image
             * which is tags
             * */

            /*@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tags", );
                return params;
            }*/

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(volleyMultipartRequest);
    }
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kazi_profile_edit);

        uploadpro = (ImageView)findViewById(R.id.uploadpropic);
        name = (EditText)findViewById(R.id.kazi_name);
        edu = (EditText)findViewById(R.id.kazi_edu);
        dob = (EditText)findViewById(R.id.kazi_dob);
        nid = (EditText)findViewById(R.id.kazi_nid);
        tin = (EditText)findViewById(R.id.kazi_tin);
        email = (EditText)findViewById(R.id.kazi_email);
        //licen = (EditText)findViewById(R.id.kazi_license);
        office = (EditText)findViewById(R.id.kazi_office);
        pref = (EditText)findViewById(R.id.kazi_parea);
        cont = (EditText)findViewById(R.id.kazi_cnumber);
        submit = (Button)findViewById(R.id.submit);
        dialog = new ProgressDialog(this);

        final Kazi tmp = currentUser.getInstance().getKazi();
        name.setText(tmp.getName());
        edu.setText(tmp.getEduBackground());
        dob.setText(tmp.getDob());
        nid.setText(tmp.getNid());
        tin.setText(tmp.getTin());
        email.setText(tmp.getKaziEmail());
        //licen.setText(tmp.getKaziLicenceNumber());
        office.setText(tmp.getOfficeAddress());
        pref.setText(tmp.getPreferedArea());
        cont.setText(tmp.getContact());

        uploadpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Updating Profile...");
                dialog.show();

                tmp.setName(name.getText().toString().trim());
                tmp.setEduBackground(edu.getText().toString().trim());
                tmp.setDob(dob.getText().toString().trim());
                tmp.setNid(nid.getText().toString().trim());
                tmp.setTin(tin.getText().toString().trim());
                tmp.setKaziEmail(email.getText().toString().trim());
                tmp.setOfficeAddress(office.getText().toString().trim());
                tmp.setPreferedArea(pref.getText().toString().trim());
                tmp.setContact(cont.getText().toString().trim());


                String url = "https://family-cycle.herokuapp.com/FamilyAssistance/kazi/updatekazi";
                JSONObject json = new JSONObject();
                try {
                    json.accumulate("kaziLicenceNumber", tmp.getKaziLicenceNumber());
                    json.accumulate("kaziUserName", tmp.getKaziUserName());
                    json.accumulate("kaziEmail",tmp.getKaziEmail());
                    json.accumulate("kaziPassword", tmp.getKaziPassword());
                    json.accumulate("name", tmp.getName());
                    json.accumulate("eduBackground", tmp.getEduBackground());
                    json.accumulate("dob", tmp.getDob());
                    json.accumulate("nid",tmp.getNid());
                    json.accumulate("tin", tmp.getTin());
                    json.accumulate("officeAddress", tmp.getOfficeAddress());
                    json.accumulate("preferedArea", tmp.getPreferedArea());
                    json.accumulate("contact", tmp.getContact());
                    json.accumulate("authorised",tmp.getAuthorised());

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
                                AlertDialog alertDialog = new AlertDialog.Builder(KaziProfileEdit.this).create();
                                alertDialog.setTitle("Congratulation !!!");
                                alertDialog.setMessage("Your profile has been updated successfully.");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Handler handler = new Handler();
                                                handler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        Intent intent = new Intent(KaziProfileEdit.this,KazirMainActivity.class);
                                                        startActivity(intent);
                                                        KaziProfileEdit.this.finish();
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

