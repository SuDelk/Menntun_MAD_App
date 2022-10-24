package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayForum extends AppCompatActivity {

    Button bt;
    LinearLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_forum);

        parent = (LinearLayout)findViewById(R.id.rootlayout);
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://menntun-4ae5e-default-rtdb.firebaseio.com/");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.child("Forums").getChildren()){
                    String forumQ=dataSnapshot.getKey();

                    bt = new Button(DisplayForum.this);
                    bt.setText(forumQ);
                    bt.setBackgroundResource(R.color.buttonOrange);
                    bt.setTextSize(20);
                    parent.addView(bt);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

