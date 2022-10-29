package com.example.menntuneducationalapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.Year;

import javax.security.auth.Subject;


public class AddPastPapers extends AppCompatActivity {


    Button upload_btn;
    EditText Subject;
    Spinner yearS;
    Spinner gradeS;
    long maxId = 0;



    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_past_papers);

        upload_btn = findViewById(R.id.UploadPPbtn);
        Subject = findViewById(R.id.subjectPP);
        yearS = findViewById(R.id.yearSpinner);
        gradeS = findViewById(R.id.gradepp);

        //database
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("PastPaper");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxId = snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Subject.getText().toString().length()==0|| gradeS.getSelectedItem().toString().equals("Select Grade") || Subject.getText().toString().equals("Select Year")){
                    if (Subject.getText().toString().length()==0){
                        Subject.setError("Enter Subject Name");
                    }
                    else if (gradeS.getSelectedItem().toString().equals("Select Grade")){
                        Toast.makeText(AddPastPapers.this, "Select Grade", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(AddPastPapers.this, "Select Year", Toast.LENGTH_SHORT).show();
                    }
                }else {

                    selectFiles();
                }


            }
        });
    }

    private void selectFiles() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select pdf file"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            UploadFiles(data.getData());
        }

    }

    private void UploadFiles(Uri data) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference reference = storageReference.child("UploadsPP/" + System.currentTimeMillis() + ".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete()) ;
                        Uri url = uriTask.getResult();

                        uploadPDF uploadPDF = new uploadPDF( url.toString(),Subject.getText().toString(), yearS.getSelectedItem().toString(),gradeS.getSelectedItem().toString());
                        databaseReference.child(String.valueOf(maxId+1)).setValue(uploadPDF);

                        Toast.makeText(AddPastPapers.this, "File Upload", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        double progress=(100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded:"+(int)progress+"%");

                    }
                });



    }}
