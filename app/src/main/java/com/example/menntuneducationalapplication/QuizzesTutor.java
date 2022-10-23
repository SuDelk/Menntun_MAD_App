package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizzesTutor extends AppCompatActivity {
    Button b1,addNew;
    LinearLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes_tutor);

        parent = (LinearLayout)findViewById(R.id.lin6);
        addNew = findViewById(R.id.addQuiz);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://menntun-4ae5e-default-rtdb.firebaseio.com/");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                for(DataSnapshot dataSnapshot : snapshot.child("Quiz").getChildren()){
                    String quizName = dataSnapshot.getKey();
                    count = count +1;
                    b1 = new Button(QuizzesTutor.this);
                    b1.setId(count);
                    b1.setText(quizName);
                    b1.setTag(count-1);
                    b1.setBackgroundResource(R.color.buttonOrange);
                    b1.setTextSize(20);
                    parent.addView(b1);
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent =  new Intent(QuizzesTutor.this,DisplayQuizDif.class);
                            intent.putExtra("quizName",quizName);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizzesTutor.this,CreateQuiz1.class);
                startActivity(intent);
            }
        });
    }
}