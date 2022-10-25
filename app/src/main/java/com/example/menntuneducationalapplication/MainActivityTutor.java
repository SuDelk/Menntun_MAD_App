package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityTutor extends AppCompatActivity {

    TextView name;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tutor);

        name = findViewById(R.id.nameOfTut);
        logout = findViewById(R.id.logOut3);

        name.setText(GlobalTutor._NAME);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityTutor.this,MainActivity.class));
            }
        });
    }

    public void toForum(View view){
        Intent X=new Intent(this, SubjectSelectorForForums.class);
        startActivity(X);
    }
    public void toQuiz(View view){
        Intent i =  new Intent(this, QuizzesTutor.class);
        startActivity(i);
    }

    public void toPassPapers(View view){
        Intent z =  new Intent(this, PastPaperTutor.class);
        startActivity(z);
    }
    public void toProfile(View view){
        Intent z =  new Intent(this, TutorProfile.class);
        startActivity(z);
    }
}