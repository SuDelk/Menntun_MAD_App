package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayQuizDif extends AppCompatActivity {
    Button HeadBtn,Delete,Edit,Test;
    String quizName,questionCount;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_quiz_dif);

        quizName = getIntent().getStringExtra("quizName");
        questionCount = getIntent().getStringExtra("questionCount");
        HeadBtn = findViewById(R.id.headingBtn);
        Delete = findViewById(R.id.deleteQuiz);
        Edit = findViewById(R.id.editQuiz);
        Test =findViewById(R.id.viewQuiz);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Quiz");

        HeadBtn.setText(quizName);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDelete();
            }
        });
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayQuizDif.this,CreateQuiz2.class);
                intent.putExtra("quizName",quizName);
                intent.putExtra("questionCount",questionCount);
                startActivity(intent);
            }
        });
        Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayQuizDif.this,DisplayQuiz.class);
                intent.putExtra("quizName",quizName);
                intent.putExtra("questionCount",questionCount);
                startActivity(intent);
            }
        });
    }
    private void showBottomSheetDelete(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(DisplayQuizDif.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.deleteSheet));

        TextView deleteView = bottomSheetView.findViewById(R.id.deleteText);
        Button deleteQuizButton = bottomSheetView.findViewById(R.id.deleteButton);
        Button deleteCancel = bottomSheetView.findViewById(R.id.deleteCancel);

        deleteView.setText("This Quiz will be permanently deleted \nAre you sure?");
        deleteQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(quizName).setValue(null);
                Toast.makeText(DisplayQuizDif.this, "Quiz Deleted", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
                Intent intent = new Intent(DisplayQuizDif.this,QuizzesTutor.class);
                startActivity(intent);
            }
        });
        deleteCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}
