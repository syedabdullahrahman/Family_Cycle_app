package com.example.user.familycyclefinal.Kazi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.familycyclefinal.DuringPragnancy.DatePickerFragment;
import com.example.user.familycyclefinal.R;
import com.example.user.familycyclefinal.internet.JsonObjectRequest;
import com.example.user.familycyclefinal.internet.MySingleton;
import com.example.user.familycyclefinal.objects.MarriageRegistration;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegistrationFormActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    EditText g_name, g_dob, g_bg, g_fn, g_mn, g_a;
    EditText b_name, b_dob, b_bg, b_fn, b_mn, b_a;
    EditText date;
    EditText w1_name, w2_name, w1_addr, w2_addr;
    EditText denmahar, reg_num, kazi_license;
    Button submit;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        g_name = (EditText) findViewById(R.id.groom_name);
        g_dob = (EditText) findViewById(R.id.groom_birth);
        g_bg = (EditText) findViewById(R.id.groom_blood);
        g_fn = (EditText) findViewById(R.id.groom_father);
        g_mn = (EditText) findViewById(R.id.groom_mother);
        g_a = (EditText) findViewById(R.id.groom_address);

       // date = (EditText) findViewById(R.id.date_of_marriage);
        b_name = (EditText) findViewById(R.id.bride_name);
        b_dob = (EditText) findViewById(R.id.bride_birth);
        b_bg = (EditText) findViewById(R.id.bride_blood);
        b_fn = (EditText) findViewById(R.id.bride_father);
        b_mn = (EditText) findViewById(R.id.bride_mother);
        b_a = (EditText) findViewById(R.id.bride_address);

        w1_name = (EditText) findViewById(R.id.witness1);
        w1_addr = (EditText) findViewById(R.id.witness1_address);
        w2_name = (EditText) findViewById(R.id.witness2);
        w2_addr = (EditText) findViewById(R.id.witness2_address);


        denmahar = (EditText) findViewById(R.id.denmahar);
        reg_num = (EditText) findViewById(R.id.registartion_number);
        kazi_license = (EditText) findViewById(R.id.kazi_license);

        dialog = new ProgressDialog(this);
        submit = (Button) findViewById(R.id.submit);



        TextView dateofmarriage = (TextView)findViewById(R.id.dom);
        dateofmarriage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker2");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validate().equals("ok")) {
                    Toast.makeText(getApplicationContext(), validate(), Toast.LENGTH_SHORT).show();
                } else {

                    AlertDialog alertDialog = new AlertDialog.Builder(RegistrationFormActivity.this).create();

                    if(g_bg.getText().toString().trim().equals(b_bg.getText().toString().trim()) || (g_bg.getText().toString().trim().endsWith("+") && b_bg.getText().toString().trim().endsWith("-")) || (g_bg.getText().toString().trim().endsWith("-") && b_bg.getText().toString().trim().endsWith("+")))
                    {
                        alertDialog.setTitle("Caution !!!");
                        alertDialog.setMessage("Go ahead and get married but Rh diseases like, Thalassemia may occur. Consult with Doctors for early care.");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogg, int which) {
                                        register();
                                    }
                                });
                        alertDialog.show();

                    }
                    else
                    {
                        register();
                    }
                }
            }
        });
    }

    public void register(){
        dialog.setMessage("Registering Marriage. Please wait.....");
        dialog.show();

        String gname = g_name.getText().toString().trim();
        String gdob = g_dob.getText().toString().trim();
        String gblood = g_bg.getText().toString().trim();
        String gfather = g_fn.getText().toString().trim();
        String gmother = g_mn.getText().toString().trim();
        String gaddress = g_a.getText().toString().trim();
        String dateOfMarriage = date.getText().toString().trim();
        String bname = b_name.getText().toString().trim();
        String bdob = b_dob.getText().toString().trim();
        String bblood = b_bg.getText().toString().trim();
        String bfather = b_fn.getText().toString().trim();
        String bmother = b_mn.getText().toString().trim();
        String baddress = b_a.getText().toString().trim();
        String w1name = w1_name.getText().toString().trim();
        String w1addeess = w1_addr.getText().toString().trim();
        String w2name = w2_name.getText().toString().trim();
        String w2address = w2_addr.getText().toString().trim();
        String denmaharr = denmahar.getText().toString().trim();
        String registrationnumber = reg_num.getText().toString().trim();
        String kazilicense = kazi_license.getText().toString().trim();

        MarriageRegistration mr = new MarriageRegistration(dateOfMarriage, gname, bname, gaddress, baddress, gdob, bdob, gfather, gmother, bfather, bmother, gblood, bblood, w1name, w1addeess, w2name, w2address, registrationnumber, kazilicense, Integer.parseInt(denmaharr));

        String url = "https://family-cycle.herokuapp.com/FamilyAssistance/marriageRegistration/addmr";
        JSONObject json = new JSONObject();

        try {
            json.accumulate("gName", gname);
            json.accumulate("bName", bname);
            json.accumulate("gAddress", gaddress);
            json.accumulate("bAddress", baddress);
            json.accumulate("gDob", gdob);
            json.accumulate("bDob", bdob);
            json.accumulate("gFather", gfather);
            json.accumulate("gMother", gmother);
            json.accumulate("bFather", bfather);
            json.accumulate("bMother", bmother);
            json.accumulate("gBlood", gblood);
            json.accumulate("bBlood", bblood);
            json.accumulate("wit1name", w1name);
            json.accumulate("wit1address", w1addeess);
            json.accumulate("wit2name", w2name);
            json.accumulate("wit2address", w2address);
            json.accumulate("date", dateOfMarriage);
            json.accumulate("regNumber", registrationnumber);
            json.accumulate("kaziLicence", kazilicense);
            json.accumulate("Denmohor", Integer.parseInt(denmaharr));

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
                        Toast.makeText(getBaseContext(), "Successfully Registered.", Toast.LENGTH_LONG).show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                Intent intent = new Intent(RegistrationFormActivity.this,RegisteredCoupleFragment.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }, 10);   //1 seconds

                    } else {
                        Toast.makeText(getBaseContext(), "Same Registration Number Exists", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getBaseContext(), "Registered not successful. Try again please.", Toast.LENGTH_LONG).show();
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

    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        Calendar m = Calendar.getInstance();
        m.set(Calendar.YEAR, year);
        m.set(Calendar.MONTH, month);
        m.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String dateofmarriage = DateFormat.getDateInstance(DateFormat.FULL).format(m.getTime());
        date = (EditText) findViewById(R.id.date_of_marriage);
        date.setText(dateofmarriage);
    }

    private String validate() {
        if (g_name.getText().toString().trim().equals(""))
            return "Please Enter Groom name";
        else if (g_dob.getText().toString().trim().equals(""))
            return "Please Enter Groom's Date of Birth";
        else if (g_bg.getText().toString().trim().equals(""))
            return "Please Enter Groom's Blood Group";
        else if (g_fn.getText().toString().trim().equals(""))
            return "Please Enter Groom's Father name";
        else if (g_mn.getText().toString().trim().equals(""))
            return "Please Enter Groom's Mother name";
        else if (g_a.getText().toString().trim().equals(""))
            return "Please Enter Groom's Address";

        else if (b_name.getText().toString().trim().equals(""))
            return "Please Enter Bride name";
        else if (b_dob.getText().toString().trim().equals(""))
            return "Please Enter Bride's Date of Birth";
        else if (b_bg.getText().toString().trim().equals(""))
            return "Please Enter Bride's Blood Group";
        else if (b_fn.getText().toString().trim().equals(""))
            return "Please Enter Bride's Father name";
        else if (b_mn.getText().toString().trim().equals(""))
            return "Please Enter Bride's Mother name";
        else if (b_a.getText().toString().trim().equals(""))
            return "Please Enter Bride's Address";

        else if (w1_name.getText().toString().trim().equals(""))
            return "Please Enter the name of witness1";
        else if (w1_addr.getText().toString().trim().equals(""))
            return "Please Enter the address of witness1";
        else if (w2_name.getText().toString().trim().equals(""))
            return "Please Enter the name of witness2";
        else if (w2_addr.getText().toString().trim().equals(""))
            return "Please Enter the address of witness2";

        else if (denmahar.getText().toString().trim().equals(""))
            return "Please Enter the Amount of Denmahar";
        else if (reg_num.getText().toString().trim().equals(""))
            return "Please Enter Registration Number";

         else if (kazi_license.getText().toString().trim().equals(""))
            return "Please Enter Kazi's License Number";
        else
            return "ok";
    }

}
