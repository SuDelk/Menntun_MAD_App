package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.security.auth.Subject;

public class EditPastPaper extends AppCompatActivity {
    Button HeadBtn,Delete,Edit,Test;
    String Subject,key,year,grade;//questionCount;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_past_paper);

        Subject = getIntent().getStringExtra("Subject");
        key = getIntent().getStringExtra("key");
        year = getIntent().getStringExtra("year");
        grade = getIntent().getStringExtra("grade");

       // questionCount = getIntent().getStringExtra("questionCount");
        HeadBtn = findViewById(R.id.submitPP);
        Delete = findViewById(R.id.DeletePP);
        Edit = findViewById(R.id.UpdatePP);
        Test =findViewById(R.id.viewpp);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("PastPaper");

        HeadBtn.setText(Subject + " " + grade + " " + year);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDelete();
            }
        });
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditPastPaper.this,UpdatePastPaper.class);
                intent.putExtra("key",key);
                intent.putExtra("Subject",Subject);
                intent.putExtra("year",year);
                intent.putExtra("grade",grade);
                startActivity(intent);
            }
        });
        Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditPastPaper.this,PastPapers.class);
                intent.putExtra("Subject",Subject);
               // intent.putExtra("questionCount",questionCount);
                startActivity(intent);
            }
        });
    }
    private void showBottomSheetDelete(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(EditPastPaper.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.deleteSheet));

        TextView deleteView = bottomSheetView.findViewById(R.id.deleteText);
        Button deleteQuizButton = bottomSheetView.findViewById(R.id.deleteButton);
        Button deleteCancel = bottomSheetView.findViewById(R.id.deleteCancel);

        deleteView.setText("This Quiz will be permanently deleted \nAre you sure?");
        deleteQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(key).setValue(null);
                Toast.makeText(EditPastPaper.this, " Deleted", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
                Intent intent = new Intent(EditPastPaper.this,PastPaperTutor.class);
                startActivity(intent);
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
