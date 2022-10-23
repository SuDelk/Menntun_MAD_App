package com.example.menntuneducationalapplication;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menntuneducationalapplication.ui.login.LoginActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CreateQuiz2 extends AppCompatActivity {
    TextView quizNameView,questionNumberView;
    EditText question,option1,option2,option3,option4;
    Button optionBtn1,optionBtn2,optionBtn3,optionBtn4,back,next;
    int currentPos = 0,questionCount;
    String Question,Option1,Option2,Option3,Option4,Answer,quizName;
    DatabaseReference insertQuizQuestion;
     ArrayList<QuizModel> quizModelArrayList;
    long maxId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz2);
        questionCount = parseInt(getIntent().getStringExtra("questionCount"));
        quizName = getIntent().getStringExtra("quizName");

        quizNameView = findViewById(R.id.nameOfQuiz);
        quizNameView.setText(quizName);
        questionNumberView = findViewById(R.id.idTVQuestionNum);
        questionNumberView.setText("Question " + (currentPos+1) + "/"+questionCount);

        insertQuizQuestion = FirebaseDatabase.getInstance().getReference().child("Quiz").child(quizName);
        insertQuizQuestion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxId = snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        checkDBForData();

        question = findViewById(R.id.IdTVQuestion);
        option1 = findViewById(R.id.option1Txt);
        option2 = findViewById(R.id.option2Txt);
        option3 = findViewById(R.id.option3Txt);
        option4 = findViewById(R.id.option4Txt);

        optionBtn1 = findViewById(R.id.Option1);
        optionBtn2 = findViewById(R.id.Option2);
        optionBtn3 = findViewById(R.id.Option3);
        optionBtn4 = findViewById(R.id.Option4);

        back = findViewById(R.id.backBtn);
        next = findViewById(R.id.nextBtn);

        optionBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPos != questionCount) {
                    checkDBForData();
                    Question = question.getText().toString();
                    Option1 = option1.getText().toString();
                    Option2 = option2.getText().toString();
                    Option3 = option3.getText().toString();
                    Option4 = option4.getText().toString();
                    Answer = Option1;
                    insertQuizQuestionToDB();
                }
            }
        });

        optionBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPos != questionCount) {
                    checkDBForData();
                    Question = question.getText().toString();
                    Option1 = option1.getText().toString();
                    Option2 = option2.getText().toString();
                    Option3 = option3.getText().toString();
                    Option4 = option4.getText().toString();
                    Answer = Option2;
                    insertQuizQuestionToDB();
                }
            }
        });

        optionBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPos != questionCount) {
                    checkDBForData();
                    Question = question.getText().toString();
                    Option1 = option1.getText().toString();
                    Option2 = option2.getText().toString();
                    Option3 = option3.getText().toString();
                    Option4 = option4.getText().toString();
                    Answer = Option3;
                    insertQuizQuestionToDB();
                }
            }
        });

        optionBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPos != questionCount) {
                    checkDBForData();
                    Question = question.getText().toString();
                    Option1 = option1.getText().toString();
                    Option2 = option2.getText().toString();
                    Option3 = option3.getText().toString();
                    Option4 = option4.getText().toString();
                    Answer = Option4;
                    insertQuizQuestionToDB();
                }
            }
        });
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(currentPos!=0){
                   currentPos--;
                   questionNumberView.setText("Question " + (currentPos + 1) + "/" + questionCount);
                   checkDBForData();
               }
               else{
                   finish();
               }
           }
       });
       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Question = question.getText().toString();
               Option1 = option1.getText().toString();
               Option2 = option2.getText().toString();
               Option3 = option3.getText().toString();
               Option4 = option4.getText().toString();
               if(Question.length() == 0 || Option1.length() == 0 || Option2.length() == 0 || Option3.length() == 0 || Option4.length() == 0){
                   Toast.makeText(CreateQuiz2.this, "Enter Details and Select Option", Toast.LENGTH_LONG).show();
               }else{
                   insertQuizQuestionToDB();
                   checkDBForData();
               }
           }
       });
    }
    private void showBottomSheet(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CreateQuiz2.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.created_bottom_sheet,(LinearLayout)findViewById(R.id.bottomSheet));
        TextView quizTxtStatus = bottomSheetView.findViewById(R.id.quizCreatedTxt);
        Button Done = bottomSheetView.findViewById(R.id.quizCreatedHome);
        quizTxtStatus.setText("Created a the quiz '"+ quizName.toUpperCase() + "' with " + questionCount +" questions.");
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                finish();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void insertQuizQuestionToDB(){
        if( Question.length() == 0 || Option1.length() == 0 || Option2.length() == 0 || Option3.length() == 0 || Option4.length() == 0){
            if(Question.length() == 0){
                question.setError( "Question is required!" );
            }
            else if (Option1.length() == 0){
                option1.setError( "Option 1 is required!" );
            }
            else if (Option2.length() == 0){
                option2.setError( "Option 2 is required!" );
            }
            else if (Option3.length() == 0){
                option3.setError( "Option 3 is required!" );
            }
            else if (Option4.length() == 0){
                option4.setError( "Option 4 is required!" );
            }
        }else {
            QuizModel newQuestion = new QuizModel(++currentPos, Question, Option1, Option2, Option3, Option4, Answer);
            insertQuizQuestion.child(String.valueOf(currentPos)).setValue(newQuestion);
            Toast.makeText(CreateQuiz2.this, "Question " + currentPos + " Added", Toast.LENGTH_LONG).show();
            question.setText("");
            option1.setText("");
            option2.setText("");
            option3.setText("");
            option4.setText("");
            if (currentPos != questionCount) {
                questionNumberView.setText("Question " + (currentPos + 1) + "/" + questionCount);
            } else {
                showBottomSheet();
            }
        }
    }
    private void checkDBForData(){
        DatabaseReference dbRefToRet = FirebaseDatabase.getInstance().getReference();
        dbRefToRet.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot ds = snapshot.child("Quiz").child(quizName).child(String.valueOf(currentPos+1));
                if(ds.child("question").getValue()!=null){
                    question.setText(String.valueOf(ds.child("question").getValue()));
                    option1.setText(String.valueOf(ds.child("option1").getValue()));
                    option2.setText(String.valueOf(ds.child("option2").getValue()));
                    option3.setText(String.valueOf(ds.child("option3").getValue()));
                    option4.setText(String.valueOf(ds.child("option4").getValue()));
                    Answer = String.valueOf(ds.child("answer").getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}