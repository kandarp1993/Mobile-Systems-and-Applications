package com.example.kandarp.kandarpdesaifirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //public Button btn;

    /*public void onClickButton(){

        btn = (Button)findViewById(R.id.button_echo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent echo = new Intent(MainActivity.this,second.class);
                startActivity(echo);
            }
        });
    }*/
    public void EchoMsg(View v){
        EditText et = (EditText)findViewById(R.id.editText3);
        Intent echo = new Intent(MainActivity.this,second.class);
        echo.putExtra("Messege",et.getText().toString());
        startActivity(echo);
    }
    public void cleanText(View v){
        EditText myTextView = (EditText) findViewById(R.id.editText3);
        myTextView.setText("");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //onClickButton();

    }
}
