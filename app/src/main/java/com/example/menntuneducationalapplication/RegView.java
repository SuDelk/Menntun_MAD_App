package com.example.menntuneducationalapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class RegView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regview);
    }
    public void StudentRegister(View view){
        Intent X=new Intent(this, RegStudent.class);
        startActivity(X);
    }
    public void TutorRegister(View view){
        Intent i =  new Intent(this, RegTutor.class);
        startActivity(i);
    }
}


