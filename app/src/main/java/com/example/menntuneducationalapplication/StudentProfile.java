package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menntuneducationalapplication.ui.login.LoginActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentProfile extends AppCompatActivity {
    Button head;
    EditText studUn,studPwd,studBday,studPhone,studName,studEmail,studPwd2;
    Spinner studgrade;
    Button updateStud,back,deleteStud;

    DatabaseReference studDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        head = findViewById(R.id.head);
        head.setText(GlobalStudent._NAME);

        studUn = findViewById(R.id.studUN);
        studName = findViewById(R.id.studName);
        studEmail = findViewById(R.id.studEmailAddress);
        studBday = findViewById(R.id.studBday);
        studgrade = findViewById(R.id.spinnerGrade);
        studPhone = findViewById(R.id.studPhone);
        studPwd = findViewById(R.id.studPWD);
        studPwd2 = findViewById(R.id.studPWD2);
        updateStud = findViewById(R.id.updateStd);
        deleteStud = findViewById(R.id.deleteStd);
        back = findViewById(R.id.backStd);

        studUn.setText(GlobalStudent._USER);
        studUn.setEnabled(false);
        studName.setText(GlobalStudent._NAME);
        studEmail.setText(GlobalStudent._EMAIL);
        studBday.setText(GlobalStudent._DOB);
        studPhone.setText(GlobalStudent._PHONE);
        studDbRef = FirebaseDatabase.getInstance().getReference().child("Students");

        updateStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDBForDataAndInsert();
            }
        });
        deleteStud.setOnClickListener(new View.OnClickListener() {
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
        String un = studUn.getText().toString();
        String pwd = studPwd.getText().toString();
        String email = studEmail.getText().toString();
        String bday = studBday.getText().toString();
        String phone = studPhone.getText().toString();
        String name = studName.getText().toString();
        String pwd2 = studPwd2.getText().toString();
        String grade = studgrade.getSelectedItem().toString();


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
                            Toast.makeText(StudentProfile.this, "Select Grade", Toast.LENGTH_SHORT).show();
                        }
                        else if( pwd.length()==0){
                            studPwd.setError("Enter a password");
                        }
                        else if( pwd2.length()==0){
                            studPwd2.setError("Enter confirm password");
                        }
                    }
                    else if(!pwd.equals(pwd2)){
                        Toast.makeText(StudentProfile.this, "Confirmation password doesn't match.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Students students = new Students(un,pwd,name,email,bday,grade,phone);

                        studDbRef.child(un).setValue(students);
                        Toast.makeText(StudentProfile.this, "Student Account Updated", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(StudentProfile.this, LoginActivity.class);
                        startActivity(intent);
                    }
    }
    private void showBottomSheetDelete(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(StudentProfile.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.deleteSheet));

        TextView deleteView = bottomSheetView.findViewById(R.id.deleteText);
        Button deleteQuizButton = bottomSheetView.findViewById(R.id.deleteButton);
        Button deleteCancel = bottomSheetView.findViewById(R.id.deleteCancel);

        deleteView.setText("This Profile will be permanently deleted \nAre you sure?");
        deleteQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studDbRef.child(GlobalStudent._USER).setValue(null);
                Toast.makeText(StudentProfile.this, "Student Profile Deleted", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
                Intent intent = new Intent(StudentProfile.this,LoginActivity.class);
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