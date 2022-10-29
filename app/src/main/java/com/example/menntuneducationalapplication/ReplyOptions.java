package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReplyOptions extends AppCompatActivity {

    EditText tv;
    Button btnEdR;
    Button btnDR;
    String replies,ForumName;
    long count=0;
    int j = 0;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_options);

        tv= findViewById(R.id.editTextTextPersonName);
        btnEdR =findViewById(R.id.btnEdR);
        btnDR =findViewById(R.id.btnDR);
        replies=getIntent().getStringExtra("replies");
        ForumName=getIntent().getStringExtra("ForumName");
        tv.setText(replies);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Forums").child(ForumName).child("Reply");


        btnDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getConfirmation();
            }
        });

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    count=snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnEdR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateReply();
                Toast.makeText(ReplyOptions.this, "Reply Updated", Toast.LENGTH_SHORT).show();
                Intent lol = new Intent(ReplyOptions.this,SubjectSelectorForForums.class);
            }
        });
    }

    public void updateReply(){
        getPosition();
        String TV = tv.getText().toString();
        dbRef.child(String.valueOf(count)).setValue(TV);
    }

    public void getPosition(){
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot ds=snapshot;

                for(long i=0;i==count;i++) {
                    if(replies==ds.child(String.valueOf(count)).getValue()){
                        j++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getConfirmation(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.deleteSheet));

        TextView deleteView = bottomSheetView.findViewById(R.id.deleteText);
        Button deleteForumButton = bottomSheetView.findViewById(R.id.deleteButton);
        Button deleteCancel = bottomSheetView.findViewById(R.id.deleteCancel);

        deleteView.setText("This Reply will be permanently deleted \nAre you sure?");
        deleteForumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                getPosition();
                                dbRef.child(String.valueOf(count)).setValue(null);
                                Toast.makeText(ReplyOptions.this,"Content Deleted ",Toast.LENGTH_SHORT).show();
                                if(GlobalStudent._USER == null){
                                    Intent X = new Intent(ReplyOptions.this,MainActivityTutor.class);
                                    startActivity(X);
                                }else{
                                    Intent X = new Intent(ReplyOptions.this,MainActivityStudent.class);
                                    startActivity(X);
                                }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
        deleteCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}