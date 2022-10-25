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

public class ForumOption extends AppCompatActivity {

    String ForumName;
    Button BtV,BtU,BtD,BtR;
    EditText tv;

    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_option);

        ForumName = getIntent().getStringExtra("Q");
        BtR = findViewById(R.id.rep);
        BtV = findViewById(R.id.seeReplies);
        BtU = findViewById(R.id.Edit);
        BtD = findViewById(R.id.Del);
        tv = findViewById(R.id.forumQues);
        tv.setText(ForumName);

        dbRef= FirebaseDatabase.getInstance().getReference().child("Forums");
        String sub=String.valueOf(dbRef.child(ForumName).child("subject"));

        BtD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getConfirmation();
            }
        });
        BtR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toReplying();
            }
        });
        BtV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seeAllReplies();
            }
        });
        BtU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateForum();
                Toast.makeText(ForumOption.this, "Forum Updated", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void updateForum(){
        String TV=tv.getText().toString();
        dbRef.child(ForumName).child("question").setValue(TV);

    }




    public void seeAllReplies(){
        Intent X = new Intent(ForumOption.this,AllReplies.class);
        X.putExtra("ForumName",ForumName);

        startActivity(X);
    }

    public void toReplying(){
        Intent X = new Intent(ForumOption.this,ReplyForum.class);
        X.putExtra("ForumName",ForumName);

        startActivity(X);
    }

    public void getConfirmation(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.deleteSheet));

        TextView deleteView = bottomSheetView.findViewById(R.id.deleteText);
        Button deleteForumButton = bottomSheetView.findViewById(R.id.deleteButton);
        Button deleteCancel = bottomSheetView.findViewById(R.id.deleteCancel);

        deleteView.setText("This Forum Question and all Replies related to it will be permanently deleted \nAre you sure?");
        deleteForumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef.child(ForumName).setValue(null);
                Toast.makeText(ForumOption.this,"Content Deleted ",Toast.LENGTH_SHORT).show();
                Intent X = new Intent(ForumOption.this,MainActivityTutor.class);
                startActivity(X);
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