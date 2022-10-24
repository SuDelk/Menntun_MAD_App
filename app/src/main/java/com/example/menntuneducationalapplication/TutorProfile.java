package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menntuneducationalapplication.ui.login.LoginActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TutorProfile extends AppCompatActivity {
    Button head;
    EditText tutUn,tutPwd,tutBday,tutPhone,tutName,tutEmail,tutPwd2;
    Button updateTut,back,deleteTut;

    DatabaseReference tutDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile);

        head = findViewById(R.id.head);
        head.setText(GlobalTutor._NAME);

        tutUn = findViewById(R.id.tutUN);
        tutName = findViewById(R.id.tutName);
        tutEmail = findViewById(R.id.tutEmailAddress);
        tutBday = findViewById(R.id.tutBday);
        tutPhone = findViewById(R.id.tutPhone);
        tutPwd = findViewById(R.id.tutPWD);
        tutPwd2 = findViewById(R.id.tutPWD2);
        updateTut = findViewById(R.id.updateTut);
        deleteTut = findViewById(R.id.deleteTut);
        back = findViewById(R.id.backTut);

        tutUn.setText(GlobalTutor._USER);
        tutUn.setEnabled(false);
        tutName.setText(GlobalTutor._NAME);
        tutEmail.setText(GlobalTutor._EMAIL);
        tutBday.setText(GlobalTutor._DOB);
        tutPhone.setText(GlobalTutor._PHONE);
        tutDbRef = FirebaseDatabase.getInstance().getReference().child("Tutors");

        updateTut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDBForDataAndInsert();
            }
        });
        deleteTut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDelete();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
            Toast.makeText(TutorProfile.this, "Confirmation password doesn't match.", Toast.LENGTH_SHORT).show();
        }
        else{
            Tutors tutor = new Tutors(un,pwd,name,email,bday,phone);

            tutDbRef.child(un).setValue(tutor);
            Toast.makeText(TutorProfile.this, "Tutor Account Updated", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(TutorProfile.this, LoginActivity.class);
            startActivity(intent);
        }
    }
    private void showBottomSheetDelete(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TutorProfile.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.deleteSheet));

        TextView deleteView = bottomSheetView.findViewById(R.id.deleteText);
        Button deleteQuizButton = bottomSheetView.findViewById(R.id.deleteButton);
        Button deleteCancel = bottomSheetView.findViewById(R.id.deleteCancel);

        deleteView.setText("This Profile will be permanently deleted \nAre you sure?");
        deleteQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutDbRef.child(GlobalTutor._USER).setValue(null);
                Toast.makeText(TutorProfile.this, "Tutor Profile Deleted", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
                Intent intent = new Intent(TutorProfile.this,LoginActivity.class);
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