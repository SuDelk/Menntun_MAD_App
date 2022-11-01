package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menntuneducationalapplication.ui.login.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegTutor extends AppCompatActivity {
    EditText tutUn,tutPwd,tutBday,tutPhone,tutName,tutEmail,tutPwd2;
    Button insertTut;

    DatabaseReference tutDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_tutor);

        tutUn = findViewById(R.id.tutUN);
        tutName = findViewById(R.id.tutName);
        tutEmail = findViewById(R.id.tutEmailAddress);
        tutBday = findViewById(R.id.tutBday);
        tutPhone = findViewById(R.id.tutPhone);
        tutPwd = findViewById(R.id.tutPWD);
        tutPwd2 = findViewById(R.id.tutPWD2);
        insertTut = findViewById(R.id.regTutFinal);

        tutDbRef = FirebaseDatabase.getInstance().getReference().child("Tutors");

        insertTut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDBForDataAndInsert();
            }
        });
    }
    private void checkDBForDataAndInsert(){
        String un = tutUn.getText().toString();
        String pwd = tutPwd.getText().toString();
        String email = tutEmail.getText().toString();
        String bday = tutBday.getText().toString();
        String phone = tutPhone.getText().toString();
        String name = tutName.getText().toString();
        String pwd2 = tutPwd2.getText().toString();

        DatabaseReference dbRefToRet = FirebaseDatabase.getInstance().getReference();
        dbRefToRet.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot ds = snapshot.child("Tutors").child(tutUn.getText().toString());
                if(ds.child("username").getValue()!=null){
                    Toast.makeText(RegTutor.this, "Username is already in use!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(un.length()==0|| pwd.length()==0||email.length()==0||bday.length()!=10||phone.length()<9||phone.length()>12||name.length()==0||pwd2.length()==0){
                        if(un.length()==0){
                            tutUn.setError("Enter a new Username");
                        }
                        else if( name.length()==0){
                            tutName.setError("Enter Full Name");
                        }
                        else if( email.length()==0){
                            tutEmail.setError("Enter an Email Address");
                        }
                        else if( bday.length()!=10){
                            tutBday.setError("Enter your Birth Date\ndd/mm/yyyy");
                        }
                        else if(phone.length()<9||phone.length()>12){
                            tutPhone.setError("Enter Valid phone number");
                        }
                        else if( pwd.length()==0){
                            tutPwd.setError("Enter a password");
                        }
                        else if( pwd2.length()==0){
                            tutPwd2.setError("Enter confirm password");
                        }
                    }
                    else if(!pwd.equals(pwd2)){
                        Toast.makeText(RegTutor.this, "Confirmation password doesn't match.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Tutors tutors = new Tutors(un,pwd,name,email,bday,phone);

                        tutDbRef.child(un).setValue(tutors);
                        Toast.makeText(RegTutor.this, "Tutor Account Created", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegTutor.this, LoginActivity.class);
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