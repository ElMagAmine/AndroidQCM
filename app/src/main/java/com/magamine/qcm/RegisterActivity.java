package com.magamine.qcm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private static final String TAG = RegisterActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEmailView.getText().length() > 4 && mPasswordView.getText().length() > 4)postUser();
            }
        });
    }
    private void postUser() {
        // Tag used to cancel the request
        String tag_string_req = "req_LoadEvents";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Loading Response: " + response.toString());
                try {

                    JSONObject jObj= new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    JSONArray jsonArray;
                    // Check for error node in json
                    if (!error)
                    {
                        /*ArrayList<MyAppSingleton.QuestionForm> quest = new ArrayList<MyAppSingleton.QuestionForm>();

                        //["1","ABCD est un carre de centre O. La distance de C a la droite (DB) est :","1","0","cd,co,cb,ca"]
                        jsonArray = jObj.getJSONArray("Rows");
                        JSONArray row;
                        for(int i = 0;i<jsonArray.length();i++)
                        {
                            row = jsonArray.getJSONArray(i);
                            //int score = row.getInt(1);

                            //quest.add(new MyAppSingleton.QuestionForm(question,answers,correct,imgRes));
                            //String DateDeb = row.getString((5));
                            //String DateFin = row.getString((6));
                            //EventsFragment.itemsList.add(new EventListItem(Titre,Description,Lieu,Type,DateDeb,DateFin));
                            String str = "fhdjh";
                        }*/
                        Toast.makeText(RegisterActivity.this,
                                "Registered .. please log in", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        //EndThis(quest);
                    }
                    else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(RegisterActivity.this,
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    //hideDialog();
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Loading Error: " + error.getMessage());
                Toast.makeText(RegisterActivity.this,
                        error.getMessage(), Toast.LENGTH_LONG).show();
                //hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", mEmailView.getText().toString());
                params.put("password", mPasswordView.getText().toString());
                return params;
            }

        };

        // Adding request to request queue
        MyAppSingleton.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


}
