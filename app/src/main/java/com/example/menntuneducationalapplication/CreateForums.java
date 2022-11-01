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

public class CreateForums extends AppCompatActivity {

    EditText edt;
    Button btn;
    Button btn2;
    String qName,sub;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_forums);
        sub=getIntent().getStringExtra("Subject");
        edt = findViewById(R.id.question);
        btn = findViewById(R.id.raise);
        btn2 = findViewById(R.id.viewForums);
        qName = edt.getText().toString();

        db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://menntun-4ae5e-default-rtdb.firebaseio.com/").child("Forums");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertIntoForumDB();
            }
        });

    }

    private void insertIntoForumDB(){
        String Q=edt.getText().toString();
        Forum ff= new Forum(Q,sub);
        if(Q.isEmpty()){
            Toast.makeText(this,"Can not raise empty field",Toast.LENGTH_SHORT).show();
            edt.setError("Can not raise an empty question");
        }else {

            db.child(Q).setValue(ff);
            Toast.makeText(this, "Question Raised", Toast.LENGTH_SHORT).show();
        }
    }

    public void intoForums(View view){
        Intent X = new Intent(this,DisplayForum.class);
        X.putExtra("Subject",sub);
        startActivity(X);
    }
}