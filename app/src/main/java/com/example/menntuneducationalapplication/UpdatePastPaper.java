package com.example.menntuneducationalapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdatePastPaper extends AppCompatActivity{
    EditText sub;
    Spinner gradeSpin,yearSpin;
    Button head,update;
    DatabaseReference dbRefToUpdate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_past_paper);
        sub =findViewById(R.id.subjectname);
        gradeSpin = findViewById(R.id.gradespin);
        yearSpin = findViewById(R.id.yearspin);
        head = findViewById(R.id.updateppp);
        update = findViewById(R.id.buttonUp);

        String Subject = getIntent().getStringExtra("Subject");
        String key = getIntent().getStringExtra("key");
        String year = getIntent().getStringExtra("year");
        String grade = getIntent().getStringExtra("grade");

        dbRefToUpdate = FirebaseDatabase.getInstance().getReference().child("PastPaper").child(key);

        sub.setText(Subject);
        head.setText(Subject + " " + grade + " " + year);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subs = sub.getText().toString();
                String grad = gradeSpin.getSelectedItem().toString();
                String yearss = yearSpin.getSelectedItem().toString();
                if(subs.length()==0|| grad.equals("Select Grade") || yearss.equals("Select Year")){
                    if (subs.length()==0){
                        sub.setError("Enter Subject Name");
                    }
                    else if (grad.equals("Select Grade")){
                        Toast.makeText(UpdatePastPaper.this, "Select Grade", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(UpdatePastPaper.this, "Select Year", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    dbRefToUpdate.child("subject").setValue(subs);
                    dbRefToUpdate.child("grade").setValue(grad);
                    dbRefToUpdate.child("year").setValue(yearss);
                    Toast.makeText(UpdatePastPaper.this, "Details Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
