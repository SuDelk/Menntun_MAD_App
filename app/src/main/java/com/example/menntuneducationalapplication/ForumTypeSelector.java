package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ForumTypeSelector extends AppCompatActivity {

    TextView tv;
    Button bb;
    String subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_type_selector2);

        subject = getIntent().getStringExtra("subject");
        bb= findViewById(R.id.button8);
        tv= findViewById(R.id.textView16);

        tv.setText(subject);

        Toast.makeText(this, "Hello "+subject, Toast.LENGTH_SHORT).show();
    }

    public void intoForumPost(View view){
        Intent X = new Intent(this,CreateForums.class);
        X.putExtra("Subject",subject);
        startActivity(X);
    }

    public void intoForumDiscussion(View view){
        Intent X = new Intent(this,DisplayForum.class);
        startActivity(X);
    }
}