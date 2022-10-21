package com.example.menntuneducationalapplication;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CreateQuiz2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz2);
        int questionCount = parseInt(getIntent().getStringExtra("questionCount"));
        String quizName = getIntent().getStringExtra("quizName");

        TextView txt = (TextView) findViewById(R.id.txt);
        txt.setText(quizName);
    }
}