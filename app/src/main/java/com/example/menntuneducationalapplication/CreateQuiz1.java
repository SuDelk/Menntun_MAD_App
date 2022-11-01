package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

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

        DatabaseReference checkQuiz = FirebaseDatabase.getInstance().getReference().child("Quiz");

        createQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkQuiz.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int count = 0;
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            if(Objects.equals(dataSnapshot.getKey(), quizName.getText().toString())){
                                count++;
                            }
                        }
                        if(count == 1){
                            quizName.setError("A Quiz with this name already exist!");
                        }
                        else if(quizName.getText().toString().length()==0 || questionCount.getText().toString().length()==0){
                            if(quizName.getText().toString().length()==0){
                                quizName.setError("Enter Quiz Name!");
                            }
                            else{
                                questionCount.setError("Enter Question Count!");
                            }
                        }
                        else{
                            Intent intent = new Intent(CreateQuiz1.this,CreateQuiz2.class);
                            intent.putExtra("quizName",quizName.getText().toString());
                            intent.putExtra("questionCount",questionCount.getText().toString());
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

}