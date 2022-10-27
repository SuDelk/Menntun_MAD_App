package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityStudent extends AppCompatActivity {

    TextView name,grade,un;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);

        name = findViewById(R.id.nameOfStd);
        grade = findViewById(R.id.gradeOfStd);
        un =findViewById(R.id.userOfStd);
        logout = findViewById(R.id.logOut1);

        name.setText(GlobalStudent._NAME);
        grade.setText(GlobalStudent._GRADE);
        un.setText("Username: "+GlobalStudent._USER);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityStudent.this,MainActivity.class));
            }
        });
    }

    public void toForum(View view){
        Intent X=new Intent(this, SubjectSelectorForForums.class);
        startActivity(X);
    }
    public void toQuiz(View view){
        Intent i =  new Intent(this, Quizzes.class);
        startActivity(i);
    }

    public void toPassPapers(View view){
        Intent z =  new Intent(this, PastPapers.class);
        startActivity(z);
    }
    public void toProfile(View view){
        Intent z =  new Intent(this, StudentProfile.class);
        startActivity(z);
    }
}