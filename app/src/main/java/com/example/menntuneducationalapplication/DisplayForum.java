package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
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

    Button bt,head;
    TextView ss;
    LinearLayout parent;
    String sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_forum);

        parent = (LinearLayout)findViewById(R.id.rootlayout);
        head= findViewById(R.id.txt);

        sub=getIntent().getStringExtra("Subject");
        head.setText(sub);
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
                        bt.setBackgroundResource(R.drawable.dynamic);
                        bt.setTextSize(20);
                        Typeface typeface = ResourcesCompat.getFont(DisplayForum.this,R.font.carter_one);
                        bt.setTypeface(typeface);
                        parent.addView(bt);

                        ss = new TextView(DisplayForum.this);
                        ss.setText("");
                        ss.setHeight(5);
                        ss.setBackgroundResource(R.drawable.dynspac);
                        parent.addView(ss);

                        bt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent X = new Intent(DisplayForum.this,ForumOption.class);
                                X.putExtra("Q",forumQ);
                                X.putExtra("Z",question);
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

