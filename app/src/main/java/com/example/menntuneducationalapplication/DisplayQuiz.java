package com.example.menntuneducationalapplication;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class DisplayQuiz extends AppCompatActivity {
    private TextView questionTV, questionNumberTV, quizname;
    private Button option1Btn,option2Btn,option3Btn,option4Btn;
    private ArrayList<QuizModel> quizModelArrayList;
    Random random;
    int currentScore = 0,questionCount;
    int questionAttempted = 1 ,currentPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_quiz);

        String quizName = getIntent().getStringExtra("quizName");
        quizname = findViewById(R.id.quizName2);
        questionTV = findViewById(R.id.IdTVQuestion);
        questionNumberTV = findViewById(R.id.idTVQuestionAttempted);
        option1Btn = findViewById(R.id.Option1);
        option2Btn = findViewById(R.id.Option2);
        option3Btn = findViewById(R.id.Option3);
        option4Btn = findViewById(R.id.Option4);
        quizModelArrayList = new ArrayList<>();

        quizname.setText(quizName);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                for(DataSnapshot dataSnapshot : snapshot.child("Quiz").child(quizName).getChildren()){
                    String question = String.valueOf(dataSnapshot.child("question").getValue());
                    String option1 = String.valueOf(dataSnapshot.child("option1").getValue());
                    String option2 = String.valueOf(dataSnapshot.child("option2").getValue());
                    String option3 = String.valueOf(dataSnapshot.child("option3").getValue());
                    String option4 = String.valueOf(dataSnapshot.child("option4").getValue());
                    String answer = String.valueOf(dataSnapshot.child("answer").getValue());
                    int questionNo = Integer.parseInt(String.valueOf(dataSnapshot.child("questionNo").getValue()));
                    count = count + 1;

                    quizModelArrayList.add(new QuizModel(questionNo,question,option1,option2,option3,option4,answer));
                    Toast.makeText(DisplayQuiz.this, String.valueOf(quizModelArrayList.size()), Toast.LENGTH_SHORT).show();
                }
                questionCount = quizModelArrayList.size();
                currentPos = 0;
                setDataToViews(currentPos);
                option1Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().toLowerCase())){
                            currentScore++;
                        }
                        questionAttempted++;
                        currentPos = currentPos + 1;
                        setDataToViews(currentPos);
                    }
                });
                option2Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2Btn.getText().toString().toLowerCase())){
                            currentScore++;
                        }
                        questionAttempted++;
                        currentPos = currentPos + 1;
                        setDataToViews(currentPos);
                    }
                });
                option3Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3Btn.getText().toString().toLowerCase())){
                            currentScore++;
                        }
                        questionAttempted++;
                        currentPos = currentPos + 1;
                        setDataToViews(currentPos);
                    }
                });
                option4Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4Btn.getText().toString().toLowerCase())){
                            currentScore++;
                        }
                        questionAttempted++;
                        currentPos = currentPos + 1;
                        setDataToViews(currentPos);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void showBottomSheet(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(DisplayQuiz.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet,(LinearLayout)findViewById(R.id.LLScore));
        TextView scoreTV = bottomSheetView.findViewById(R.id.idTVScore);
        Button restartQuizBtn = bottomSheetView.findViewById(R.id.idBtnRestart);
        Button doneBtn = bottomSheetView.findViewById(R.id.idBtnDone);

        scoreTV.setText("Your Score is \n" + currentScore + "/" +questionCount);
        restartQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPos = 0;
                setDataToViews(currentPos);
                questionAttempted = 1;
                currentScore = 0 ;
                bottomSheetDialog.dismiss();
            }
        });
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
    private void setDataToViews(int currentPos){
        if(questionAttempted-1==questionCount){
            showBottomSheet();
        }
        else{
            questionNumberTV.setText("Attempting Question : "+questionAttempted+ "/" + questionCount);
            questionTV.setText(quizModelArrayList.get(currentPos).getQuestion());
            option1Btn.setText(quizModelArrayList.get(currentPos).getOption1());
            option2Btn.setText(quizModelArrayList.get(currentPos).getOption2());
            option3Btn.setText(quizModelArrayList.get(currentPos).getOption3());
            option4Btn.setText(quizModelArrayList.get(currentPos).getOption4());

        }
    }
}