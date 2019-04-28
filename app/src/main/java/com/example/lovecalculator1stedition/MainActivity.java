package com.example.lovecalculator1stedition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;


import unirest.HttpResponse;
import unirest.JsonNode;
import unirest.Unirest;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://love-calculator.p.rapidapi.com/getPercentage?fname=John&sname=Alice";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        Log.d("Debug", response);
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
                params.put("X-RapidAPI-Key", "ddb2ddfd21msh545fdbc2222d20dp1ba2f6jsn6d9725535c4a");
                return params;
            }
        };
        queue.add(stringRequest);
    }


    EditText firstName,firstname;
    TextView tv;
    String result;
    public void showResult(View v) {
        firstName = (EditText) findViewById(R.id.editText);
        firstname = (EditText) findViewById(R.id.editText2);
        tv = (TextView)findViewById(R.id.editText3);
        String f = firstName.getText().toString();
        String s = firstname.getText().toString();
        String sum = s + f;
        sum = sum.toLowerCase();
        int value = sum.hashCode();
        Random random = new Random(value);
        result = (random.nextInt(100) + 1) + "%";
        tv.setText(result);

    }
}
