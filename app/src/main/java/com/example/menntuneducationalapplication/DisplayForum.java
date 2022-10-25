package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayForum extends AppCompatActivity {

    Button bt;
    LinearLayout parent;
    String sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_forum);

        parent = (LinearLayout)findViewById(R.id.rootlayout);
        sub=getIntent().getStringExtra("Subject");
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://menntun-4ae5e-default-rtdb.firebaseio.com/");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.child("Forums").getChildren()){
                    String subjectFilter = String.valueOf(dataSnapshot.child("subject").getValue());
                    String forumQ = dataSnapshot.getKey();
                    String question = String.valueOf(dataSnapshot.child("question").getValue());

                    //Toast.makeText(DisplayForum.this, subjectFilter, Toast.LENGTH_SHORT).show();

                    if(subjectFilter.equals(sub)) {

                        bt = new Button(DisplayForum.this);
                        bt.setText(question);
                        bt.setBackgroundResource(R.color.buttonOrange);
                        bt.setTextSize(20);
                        parent.addView(bt);

                        bt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent X = new Intent(DisplayForum.this,ForumOption.class);
                                X.putExtra("Q",forumQ);
                                startActivity(X);
                            }
                        });
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Toast.makeText(this, "Hello "+sub, Toast.LENGTH_SHORT).show();

    }
}

