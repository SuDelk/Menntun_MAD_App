package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.menntuneducationalapplication.ui.login.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegStudent extends AppCompatActivity {
    EditText studUn,studPwd,studBday,studPhone,studName,studEmail,studPwd2;
    Spinner studgrade;
    Button insertStud;

    DatabaseReference studDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_student);

        studUn = findViewById(R.id.studUN);
        studName = findViewById(R.id.studName);
        studEmail = findViewById(R.id.studEmailAddress);
        studBday = findViewById(R.id.studBday);
        studgrade = findViewById(R.id.spinnerGrade);
        studPhone = findViewById(R.id.studPhone);
        studPwd = findViewById(R.id.studPWD);
        studPwd2 = findViewById(R.id.studPWD2);
        insertStud = findViewById(R.id.regStudFinal);

        studDbRef = FirebaseDatabase.getInstance().getReference().child("Students");

        insertStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDBForDataAndInsert();
            }
        });
    }
    private void checkDBForDataAndInsert(){
        String un = studUn.getText().toString();
        String pwd = studPwd.getText().toString();
        String email = studEmail.getText().toString();
        String bday = studBday.getText().toString();
        String phone = studPhone.getText().toString();
        String name = studName.getText().toString();
        String pwd2 = studPwd2.getText().toString();
        String grade = studgrade.getSelectedItem().toString();

        DatabaseReference dbRefToRet = FirebaseDatabase.getInstance().getReference();
        dbRefToRet.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot ds = snapshot.child("Students").child(studUn.getText().toString());
                if(ds.child("username").getValue()!=null){
                    Toast.makeText(RegStudent.this, "Username is already in use!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(un.length()==0|| pwd.length()==0||email.length()==0||bday.length()!=10||phone.length()<9||phone.length()>12||grade.equals("Select Option")||name.length()==0||pwd2.length()==0){
                        if(un.length()==0){
                            studUn.setError("Enter a new Username");
                        }
                        else if( name.length()==0){
                            studName.setError("Enter Full Name");
                        }
                        else if( email.length()==0){
                            studEmail.setError("Enter an Email Address");
                        }
                        else if( bday.length()!=10){
                            studBday.setError("Enter your Birth Date\ndd/mm/yyyy");
                        }
                        else if(phone.length()<9||phone.length()>12){
                            studPhone.setError("Enter Valid phone number");
                        }
                        else if(grade.equals("Select Grade")){
                            Toast.makeText(RegStudent.this, "Select Grade", Toast.LENGTH_SHORT).show();
                        }
                        else if( pwd.length()==0){
                            studPwd.setError("Enter a password");
                        }
                        else if( pwd2.length()==0){
                            studPwd2.setError("Enter confirm password");
                        }
                    }
                    else if(!pwd.equals(pwd2)){
                        Toast.makeText(RegStudent.this, "Confirmation password doesn't match.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Students students = new Students(un,pwd,name,email,bday,grade,phone);

                        studDbRef.child(un).setValue(students);
                        Toast.makeText(RegStudent.this, "Student Account Created", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegStudent.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}