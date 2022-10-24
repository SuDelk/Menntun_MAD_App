package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReplyForum extends AppCompatActivity {

    String ForumName,userReply;
    TextView tv;
    ImageButton imgBtn;
    EditText reply;
    long maxRep;
    DatabaseReference db,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_forum);

        ForumName = getIntent().getStringExtra("ForumName");
        tv=findViewById(R.id.textView14);
        tv.setText(ForumName);
        imgBtn= findViewById(R.id.imageButton);
        reply=findViewById(R.id.theReply);
        userReply=reply.getText().toString();

        db = FirebaseDatabase.getInstance().getReference().child("Forums");

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendReply();
            }
        });

        dbRef= FirebaseDatabase.getInstance().getReference().child("Forums").child(ForumName).child("Reply");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxRep=snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void sendReply(){
//        long temp = maxRep+1;
        String place= String.valueOf(++maxRep);
        String Q=reply.getText().toString();
        db.child(ForumName).child("Reply").child(place).setValue(Q);

        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }
}