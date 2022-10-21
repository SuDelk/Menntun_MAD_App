package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateForums extends AppCompatActivity {

    EditText edt;
    Button btn;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_forums);

        edt = findViewById(R.id.question);
        btn = findViewById(R.id.raise);

        db = FirebaseDatabase.getInstance().getReference().child("Forums");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertIntoForumDB();
            }
        });
    }

    public void insertIntoForumDB(){

        String Q=edt.getText().toString();
        Forum ff= new Forum(Q);

        db.push().setValue(ff);
        Toast.makeText(this,"Question Raised",Toast.LENGTH_SHORT).show();

    }
}