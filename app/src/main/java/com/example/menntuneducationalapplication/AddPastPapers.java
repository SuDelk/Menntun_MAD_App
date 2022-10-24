package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPastPapers extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText subject;
    EditText grade;
    Button upload;
    Button submit;
    Spinner spinner1;
    String year[];

    DatabaseReference PastPaperDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_past_papers);
        spinner1 = findViewById(R.id.yearSpinner);
        subject = findViewById(R.id.subjectPP);
        grade = findViewById(R.id.GradePP);
        upload = findViewById(R.id.UploadPP);
        submit = findViewById(R.id.submitPP);

        PastPaperDbRef = FirebaseDatabase.getInstance().getReference().child("PastPaper");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPastPsperData();
            }
        });


        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this,R.array.year, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

    }
    private void insertPastPsperData(){
        String Subject = subject.getText().toString();
        String year = spinner1.getSelectedItem().toString();
        String Grade = grade.getText().toString();


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
            Grade = grade;
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
