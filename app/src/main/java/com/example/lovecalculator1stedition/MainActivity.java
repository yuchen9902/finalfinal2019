package com.example.lovecalculator1stedition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LoveCalculator1stEdition:Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.calculate).setOnClickListener(v -> configurebutton());
    }

    String getTextIn(final int editor) {
        return ((EditText) findViewById(editor)).getText().toString();
    }

    private void configurebutton() {
        Button button = findViewById(R.id.calculate);

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

        intent.putExtra("first", getTextIn(R.id.firstname));
        intent.putExtra("second", getTextIn(R.id.secondname));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}