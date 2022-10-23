package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PastPaperTutor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_paper_tutor);}


        public void onClick(View view){
        Intent p =  new Intent(PastPaperTutor.this, AddPastPapers.class);
            startActivity(p);
        }
    }
