package com.example.menntuneducationalapplication;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReplyOptions extends AppCompatActivity {

    EditText tv;
    Button btnEdR;
    Button btnDR;
    String replies;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_options);

        tv= findViewById(R.id.editTextTextPersonName);
        btnEdR =findViewById(R.id.btnEdR);
        btnDR =findViewById(R.id.btnDR);
        replies=getIntent().getStringExtra("replies");
        tv.setText(replies);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Forums").child("Reply");


        btnDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getConfirmation();
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
                dbRef.child(replies).setValue(null);
                Toast.makeText(ReplyOptions.this,"Content Deleted ",Toast.LENGTH_SHORT).show();
                Intent X = new Intent(ReplyOptions.this,MainActivityTutor.class);
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