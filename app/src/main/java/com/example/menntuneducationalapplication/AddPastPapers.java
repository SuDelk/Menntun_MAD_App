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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class AddPastPapers extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText subject;
    Spinner spinner2;
    String grade[];
    Button upload;
    Button submit;
    Spinner spinner1;
    String year[];
    EditText pdfname;


    StorageReference storageReference;
    DatabaseReference PastPaperDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_past_papers);
        spinner1 = findViewById(R.id.yearSpinner);
        subject = findViewById(R.id.subjectPP);
        spinner2 = findViewById(R.id.gradepp);
        upload = findViewById(R.id.UploadPPbtn);
        submit = findViewById(R.id.submitPP);
        pdfname=findViewById(R.id.UploadPPtext);

        storageReference =FirebaseStorage.getInstance().getReference();
        PastPaperDbRef = FirebaseDatabase.getInstance().getReference().child("PastPaper");
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFiles();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPastPaperData();
            }
        });


        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this,R.array.year, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapter1 =  ArrayAdapter.createFromResource(this,R.array.grade, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);
        spinner2.setOnItemSelectedListener(this);

    }

    private void selectFiles() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select pdf file"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            UploadFiles(data.getData());
        }



    }

    private void UploadFiles(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference reference = storageReference.child("Uploads/" + System.currentTimeMillis() + "pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri url=uriTask.getResult();

                        uploadPDF pdfClass = new uploadPDF(pdfname.getText().toString(),url.toString());
                        PastPaperDbRef.child(PastPaperDbRef.push().getKey()).setValue(pdfClass);

                        Toast.makeText(AddPastPapers.this,"File Upload",Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()





























































































































































                {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress=(100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded "+(int)progress+"%");
                    }
                });
    }

    private void insertPastPaperData(){
        String Subject = subject.getText().toString();
        String year = spinner1.getSelectedItem().toString();
        String Grade = spinner2.getSelectedItem().toString();



        PastPapers PP = new PastPapers(Subject,year,Grade);
        PastPaperDbRef.push().setValue(PP);
        Toast.makeText(AddPastPapers.this,"Data Insert",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class PastPapers{
        String Subject;
        String year;
        String Grade;

        public PastPapers(String subject, String year, String grade) {
            Subject = subject;
            this.year = year;
            this.Grade = grade;
        }

        public String getSubject() {
            return Subject;
        }

        public String getYear() {
            return year;
        }

        public String getGrade() {
            return Grade;
        }
    }
}