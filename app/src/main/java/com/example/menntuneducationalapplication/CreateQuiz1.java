package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateQuiz1 extends AppCompatActivity {
    EditText quizName;
    EditText questionCount;
    Button createQuizBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz1);

        quizName = findViewById(R.id.quizName1);
        questionCount = findViewById(R.id.noOfQues);
        createQuizBtn = findViewById(R.id.createQuizBtn);


        createQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateQuiz1.this,CreateQuiz2.class);
                intent.putExtra("quizName",quizName.getText().toString());
                intent.putExtra("questionCount",questionCount.getText().toString());
                startActivity(intent);
            }
        });

    }

}