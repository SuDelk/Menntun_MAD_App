package com.example.menntuneducationalapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menntuneducationalapplication.CreateQuiz1;
import com.example.menntuneducationalapplication.MainActivityStudent;
import com.example.menntuneducationalapplication.MainActivityTutor;
import com.example.menntuneducationalapplication.R;
import com.example.menntuneducationalapplication.RegView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Button stdLogin,tutLogin,Register;
    EditText usName,pwd;
    DatabaseReference dbRefToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usName = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        stdLogin = findViewById(R.id.studentLog);
        tutLogin = findViewById(R.id.tutorLog);
        Register = findViewById(R.id.registerBtn);

        dbRefToLogin = FirebaseDatabase.getInstance().getReference();

        tutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usName.getText().toString();
                String password = pwd.getText().toString();

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please Enter Credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                    dbRefToLogin.child("Tutors").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(username)){
                                String gotPwd = snapshot.child(username).child("password").getValue(String.class);
                                if(gotPwd.equals(password)){
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(LoginActivity.this,MainActivityTutor.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(LoginActivity.this, "Invalid credentials, Try Again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
    public void StudentLogin(View view){
        Intent X=new Intent(this, MainActivityStudent.class);
        startActivity(X);
    }
    public void TutorLogin(View view){
        Intent i =  new Intent(this, MainActivityTutor.class);
        startActivity(i);
    }
    public void Register(View view){
        Intent i =  new Intent(this, RegView.class);
        startActivity(i);
    }

}