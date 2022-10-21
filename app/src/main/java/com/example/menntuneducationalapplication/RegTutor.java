package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegTutor extends AppCompatActivity {
    EditText tutUn ;
    EditText tutPwd ;
    Button insertTut;

    DatabaseReference tutDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_tutor);

        tutUn = findViewById(R.id.tutUN);
        tutPwd = findViewById(R.id.tutPWD);
        insertTut = findViewById(R.id.regTutFinal);

        tutDbRef = FirebaseDatabase.getInstance().getReference().child("Tutors");

        insertTut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTutorData();
            }
        });
    }
    private void insertTutorData(){
        String un = tutUn.getText().toString();
        String pwd = tutPwd.getText().toString();
        
        Tutors tutors = new Tutors(un,pwd);
        
        tutDbRef.push().setValue(tutors);
        Toast.makeText(RegTutor.this, "Tutor Account Created", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,MainActivityStudent.class);
        startActivity(intent);
    }
}