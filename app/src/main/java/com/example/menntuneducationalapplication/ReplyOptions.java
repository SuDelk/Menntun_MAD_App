package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ReplyOptions extends AppCompatActivity {

    TextView tv;
    Button btnEdR;
    Button btnDR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_options);

        tv= findViewById(R.id.textView17);
        btnEdR =findViewById(R.id.btnEdR);
        btnDR =findViewById(R.id.btnDR);


    }
}