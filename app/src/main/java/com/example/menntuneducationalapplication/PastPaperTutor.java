package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PastPaperTutor extends AppCompatActivity {
    Button addNewPP;
    Button b1;
    TextView s1;
    LinearLayout parent1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_paper_tutor);

        parent1 = (LinearLayout)findViewById(R.id.line);
        addNewPP = findViewById(R.id.addpp);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://menntun-4ae5e-default-rtdb.firebaseio.com/");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                for(DataSnapshot dataSnapshot : snapshot.child("PastPaper").getChildren()){
                    String key = dataSnapshot.getKey();
                    String Subject = String.valueOf(dataSnapshot.child("subject").getValue());
                    String year = String.valueOf(dataSnapshot.child("year").getValue());
                    String grade = String.valueOf(dataSnapshot.child("grade").getValue());
                    count = count +1;
                    b1 = new Button(PastPaperTutor.this);
                    b1.setId(count);
                    b1.setText(Subject+"-"+grade+"-"+year);
                    b1.setTag(count-1);
                    b1.setBackgroundResource(R.drawable.pp);
                    b1.setAllCaps(false);
                    Typeface typeface = ResourcesCompat.getFont(PastPaperTutor.this,R.font.carter_one);
                    b1.setTypeface(typeface);
                    b1.setTextSize(20);
                    parent1.addView(b1);
                    s1 = new TextView(PastPaperTutor.this);
                    s1.setText("");
                    s1.setHeight(5);
                    s1.setBackgroundResource(R.drawable.dynspac);
                    parent1.addView(s1);
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent =  new Intent(PastPaperTutor.this,EditPastPaper.class);
                            intent.putExtra("key",key);
                            intent.putExtra("Subject",Subject);
                            intent.putExtra("year",year);
                            intent.putExtra("grade",grade);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        addNewPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(PastPaperTutor.this, AddPastPapers.class);
                startActivity(intent);
            }

        });
    }
}


