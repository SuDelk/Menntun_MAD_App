package com.example.menntuneducationalapplication;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreateQuiz2 extends AppCompatActivity {
    TextView quizNameView,questionNumberView;
    EditText question,option1,option2,option3,option4;
    Button optionBtn1,optionBtn2,optionBtn3,optionBtn4;
    ArrayList<QuizModel> quizModelArrayList;
    int currentPos = 0;
    String Question,Option1,Option2,Option3,Option4,Answer;
    DatabaseReference insertQuizQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz2);
        int questionCount = parseInt(getIntent().getStringExtra("questionCount"));
        String quizName = getIntent().getStringExtra("quizName");

        quizNameView = findViewById(R.id.nameOfQuiz);
        quizNameView.setText(quizName);
        questionNumberView = findViewById(R.id.idTVQuestionNum);
        questionNumberView.setText("Question " + (currentPos+1) + "/"+questionCount);

        insertQuizQuestion = FirebaseDatabase.getInstance().getReference().child("Quiz").child(quizName);

        question = findViewById(R.id.IdTVQuestion);
        option1 = findViewById(R.id.option1Txt);
        option2 = findViewById(R.id.option2Txt);
        option3 = findViewById(R.id.option3Txt);
        option4 = findViewById(R.id.option4Txt);

        optionBtn1 = findViewById(R.id.Option1);
        optionBtn2 = findViewById(R.id.Option2);
        optionBtn3 = findViewById(R.id.Option3);
        optionBtn4 = findViewById(R.id.Option4);


        if(currentPos<questionCount) {

            optionBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Question = question.getText().toString();
                    Option1 = option1.getText().toString();
                    Option2 = option2.getText().toString();
                    Option3 = option3.getText().toString();
                    Option4 = option4.getText().toString();
                    Answer = Option1;
                    QuizModel newQuestion = new QuizModel(currentPos+1, Question, Option1, Option2, Option3, Option4, Answer);
                    insertQuizQuestion.push().setValue(newQuestion);
                    Toast.makeText(CreateQuiz2.this, "Question " + currentPos + " Added", Toast.LENGTH_LONG).show();
                    question.setText("");
                    option1.setText("");
                    option2.setText("");
                    option3.setText("");
                    option4.setText("");
                    currentPos++;
                    questionNumberView.setText("Question " + (currentPos+1) + "/"+questionCount);
                }
            });
            optionBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Question = question.getText().toString();
                    Option1 = option1.getText().toString();
                    Option2 = option2.getText().toString();
                    Option3 = option3.getText().toString();
                    Option4 = option4.getText().toString();
                    Answer = Option2;
                    QuizModel newQuestion = new QuizModel(currentPos + 1, Question, Option1, Option2, Option3, Option4, Answer);
                    insertQuizQuestion.push().setValue(newQuestion);
                    Toast.makeText(CreateQuiz2.this, "Question " + currentPos + " Added", Toast.LENGTH_LONG).show();
                    question.setText("");
                    option1.setText("");
                    option2.setText("");
                    option3.setText("");
                    option4.setText("");
                    currentPos++;
                    questionNumberView.setText("Question " + currentPos + "/"+questionCount);
                }
            });
            optionBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Question = question.getText().toString();
                    Option1 = option1.getText().toString();
                    Option2 = option2.getText().toString();
                    Option3 = option3.getText().toString();
                    Option4 = option4.getText().toString();
                    Answer = Option3;
                    QuizModel newQuestion = new QuizModel(currentPos + 1, Question, Option1, Option2, Option3, Option4, Answer);
                    insertQuizQuestion.push().setValue(newQuestion);
                    Toast.makeText(CreateQuiz2.this, "Question " + currentPos + " Added", Toast.LENGTH_LONG).show();
                    question.setText("");
                    option1.setText("");
                    option2.setText("");
                    option3.setText("");
                    option4.setText("");
                    currentPos++;
                    questionNumberView.setText("Question " + currentPos + "/"+questionCount);
                }
            });
            optionBtn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Question = question.getText().toString();
                    Option1 = option1.getText().toString();
                    Option2 = option2.getText().toString();
                    Option3 = option3.getText().toString();
                    Option4 = option4.getText().toString();
                    Answer = Option4;
                    QuizModel newQuestion = new QuizModel(currentPos + 1, Question, Option1, Option2, Option3, Option4, Answer);
                    insertQuizQuestion.push().setValue(newQuestion);
                    Toast.makeText(CreateQuiz2.this, "Question " + currentPos + " Added", Toast.LENGTH_LONG).show();
                    question.setText("");
                    option1.setText("");
                    option2.setText("");
                    option3.setText("");
                    option4.setText("");
                    currentPos++;
                    questionNumberView.setText("Question " + currentPos + "/"+questionCount);
                }
            });
        }
        else{

        }

    }

}