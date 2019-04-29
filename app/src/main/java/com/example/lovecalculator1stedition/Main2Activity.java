package com.example.lovecalculator1stedition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {


    private static final String TAG = "LoveCalculator1stEdition:Main";
    private RequestQueue requestQueue;
    private TextView percentage;
    private TextView afraid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(this);
        percentage = findViewById(R.id.percentage);

        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String firstname = intent.getStringExtra("first");
        String secondname = intent.getStringExtra("second");
        afraid = findViewById(R.id.afraid);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //String url = "https://love-calculator.p.rapidapi.com/getPercentage?fname=John&sname=Alice";
                String url = "https://love-calculator.p.rapidapi.com/getPercentage?fname=" + secondname + "&sname=" + firstname;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            public void onResponse(String response) {
                                try {
                                    afraid = findViewById(R.id.afraid);
                                    percentage = findViewById(R.id.percentage);
                                    JsonParser parser = new JsonParser();
                                    JsonObject result = parser.parse(response).getAsJsonObject();
                                    String percentages = result.get("percentage").getAsString() + '%';
                                    String outcome = result.get("result").getAsString();
                                    //String resultPercentage = percentages.getAsString();
                                    //percentage.setVisibility(View.VISIBLE);
                                    percentage.setText(percentages);
                                    afraid.setText(outcome);
                                    Log.d("Debug", response);
                                } catch (final Exception e) {
                                    Log.e(TAG, "no json");
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Debug", error.toString());
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("X-RapidAPI-Host", "love-calculator.p.rapidapi.com");
                        params.put("X-RapidAPI-Key", "d5cc839b0amshba8d8fcbbdc615cp1f6908jsn48f5b1162ff2");
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}
