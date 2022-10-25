package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PastPaperTutor extends AppCompatActivity {
    Button addNewPP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_paper_tutor);

        addNewPP = findViewById(R.id.addpp);
        addNewPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(PastPaperTutor.this, AddPastPapers.class);
                startActivity(intent);
            }

        });
    }
}


