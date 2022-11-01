package com.example.menntuneducationalapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class RegView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regview);

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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


