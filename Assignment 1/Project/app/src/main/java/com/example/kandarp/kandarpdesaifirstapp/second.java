package com.example.kandarp.kandarpdesaifirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EditText et = (EditText) findViewById(R.id.echoText);
        Bundle b;
        b =  getIntent().getExtras();
        et.setText(b.getString("Messege"));
    }
}
