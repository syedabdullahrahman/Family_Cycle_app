package com.example.user.familycyclefinal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Chatbot_AllStageActivity extends AppCompatActivity {


    private final int REQ_CODE_SPEECH_INPUT = 100;
    AutoCompleteTextView autoCompleteTextView;
    Button submit;
    ImageView mic;
    TextView answer;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot__all_stage);
        String [] questions ={"Why he/she doesn,t treat me well ?",
                "he/she doesn't try to understand me ?",
                "Why he/she always critisize me ?",
                "Why she/he doesn't talk to me very often ?",
                "Why he/she doesn't share things with me ?",
                "why he/she doesn't trust me much ?",
                "why he/she always says \"it's mine, it's mine\" ? ",
                "why he/she always argues/disagrees with me ?",
                "why he/she has changed so much ?",
                "why he/she does/doesn't want to extend our family ?",
                "why he/she careless about children care ?",
                "How can i recover him from technology addiction ?",
                "what will be the next ovalution date ? ",
                "what will be next period time ? ",
                "Can i avoid pregnancy using natural method ?",
                "How can predict boy/girl ?",
                "Can you tell me symptoms of pregnancy ?",
                "Why bleeding ?",
                "what will be the preparation for Pregnancy ?",
                "when labor will start ?",
                "suggest me some nutrition food?",
                "as a father what can i do ?",
                "suggest some healty food for pregnant mother .",
                "how much calories do a pregnant woman need a day?",
                "how much weight I have gain ?",
                "what are the baby foods?",
                "what is the best food for baby ?",
                "what are the necessary vitamins & minerals for baby?",
                "what food will provide enough minerals and vitamins ?",
                "what vaccination are needed for baby ?",
                "what are the diseases of baby?",
                "why my baby catch cold/fever/vomiting ?",
                "why my babies screen get rashes?",
                "why my baby always cry, without reason ?",
                "Why my baby don't want to eat ?",
                "why my baby is having breathing problem ?",
                "how much water baby needs in day ?",
                "when / how often baby need bath ?",
                "what way should be followed to bathe a baby?",
                "How to ensure baby is getting enough food and nutrition ?",
                "Babies cry a lot, what should i do ? ",
                "How long baby take sleep ?",
                "what should i gift a baby ? "
        };

        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.textlikho);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,questions);
        autoCompleteTextView.setAdapter(adapter);

        submit = (Button)findViewById(R.id.submit);
        mic= (ImageView)findViewById(R.id.micro);
        answer = (TextView)findViewById(R.id.answerappear);

    mic.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            promptSpeechInput();
        }
    });

    }

    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Say Something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn\\'t support speech input",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String userQuery=result.get(0);
                    autoCompleteTextView.setText(userQuery);
                    RetrieveFeedTask task=new RetrieveFeedTask();
                    task.execute(userQuery);
                }
                break;
            }
        }
    }

    public String GetText(String query) {

        String text = "";
        BufferedReader reader = null;

        // Send data
        try {

            // Defined URL  where to send data
            URL url = new URL("https://api.api.ai/v1/query?v=20150910");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Authorization", "Bearer "+"3f03ffe7ffe240619bb968634a75ff82");
            conn.setRequestProperty("Content-Type", "application/json");

            //Create JSONObject here
            JSONObject jsonParam = new JSONObject();
            JSONArray queryArray = new JSONArray();
            queryArray.put(query);
            jsonParam.put("query", queryArray);
//          jsonParam.put("name", "order a medium pizza");
            jsonParam.put("lang", "en");
            jsonParam.put("sessionId", "1234567890");


            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            Log.d("karma", "after conversion is " + jsonParam.toString());
            wr.write(jsonParam.toString());
            wr.flush();
            Log.d("karma", "json is " + jsonParam);

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }

            text = sb.toString();

            JSONObject object1 = new JSONObject(text);
            JSONObject object = object1.getJSONObject("result");
            JSONObject fulfillment = null;
            String speech = null;
//            if (object.has("fulfillment")) {
            fulfillment = object.getJSONObject("fulfillment");
//                if (fulfillment.has("speech")) {
            speech = fulfillment.optString("speech");
//                }
//            }

            Log.d("karma ", "response is " + text);
            return speech;

        } catch (Exception ex) {
            Log.d("karma", "exception at last " + ex);
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

        return null;
    }

    public void querybuttonClicked(View view) {
        String q = autoCompleteTextView.getText().toString().trim();

        RetrieveFeedTask task=new RetrieveFeedTask();
        task.execute(q);

    }

    public void buttonClicked(View view) {

    }


    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {
            String s = null;
            s = GetText(voids[0]);
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            answer.setText(s);
        }
    }







}
