package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Button forumB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //forumB=findViewById(R.id.forumBtn);
    }

    public void toForum(View view){
        Intent X=new Intent(this,Forums.class);
        startActivity(X);
    }
    public void toQuiz(View view){
        Intent i =  new Intent(this,quizzes.class);
        startActivity(i);
    }
}