package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddPastPapers extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //variable
    EditText subject;
    Spinner spinner2;
    String grade[];
    Button upload;
    Button submit;
    Spinner spinner1;
    String year[];
    EditText pdfname;



    DatabaseReference PastPaperDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_past_papers);

        spinner1 = findViewById(R.id.yearSpinner);
        subject = findViewById(R.id.subjectPP);
        spinner2 = findViewById(R.id.gradepp);
        upload = findViewById(R.id.UploadPPbtn);
        submit = findViewById(R.id.submitPP);
        pdfname=findViewById(R.id.UploadPPtext);


        PastPaperDbRef = FirebaseDatabase.getInstance().getReference().child("PastPaper");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPastPaperData();
            }
        });


        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this,R.array.year, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapter1 =  ArrayAdapter.createFromResource(this,R.array.grade, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);
        spinner2.setOnItemSelectedListener(this);

    }




    private void insertPastPaperData(){
        String Subject = subject.getText().toString();
        String year = spinner1.getSelectedItem().toString();
        String Grade = spinner2.getSelectedItem().toString();


        PastPapers PP = new PastPapers(Subject,year,Grade);
        PastPaperDbRef.push().setValue(PP);
        Toast.makeText(AddPastPapers.this,"Data Insert",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class PastPapers{
        String Subject;
        String year;
        String Grade;

        public PastPapers(String subject, String year, String grade) {
            Subject = subject;
            this.year = year;
            this.Grade = grade;
        }

        public String getSubject() {
            return Subject;
        }

        public String getYear() {
            return year;
        }

        public String getGrade() {
            return Grade;
        }
    }
}
