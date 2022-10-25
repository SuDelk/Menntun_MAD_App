package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AllReplies extends AppCompatActivity {

    String ForumName;
    Button bt;
    LinearLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_replies);

        ForumName=getIntent().getStringExtra("ForumName");
        //Toast.makeText(AllReplies.this, "ForumName", Toast.LENGTH_SHORT).show();
        parent=findViewById(R.id.rootlayout);
        bt=findViewById(R.id.txt);
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://menntun-4ae5e-default-rtdb.firebaseio.com/");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count=0;
                for(DataSnapshot dataSnapshot:snapshot.child("Forums").child(ForumName).child("Reply").getChildren()){
                    String replies = String.valueOf(dataSnapshot.getValue());
                    Toast.makeText(AllReplies.this,replies, Toast.LENGTH_SHORT).show();
                    count=count+1;

                    bt = new Button(AllReplies.this);
                    bt.setId(count);
                    bt.setText(replies);
                    bt.setBackgroundResource(R.color.buttonOrange);
                    bt.setTextSize(20);
                    parent.addView(bt);

                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent X=new Intent(AllReplies.this,ReplyOptions.class);
                            X.putExtra("replies",replies);
                            X.putExtra("ForumName",ForumName);
                            startActivity(X);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}