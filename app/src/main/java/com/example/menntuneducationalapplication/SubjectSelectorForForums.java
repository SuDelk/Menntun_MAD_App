package com.example.menntuneducationalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectSelectorForForums extends AppCompatActivity {

    Button BtM;
    Button BtH;
    Button BtP;
    Button BtG;
    Button BtS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_selector_for_forums);
        BtM = findViewById(R.id.BTM);
        BtH = findViewById(R.id.BTH);
        BtP = findViewById(R.id.BTP);
        BtG = findViewById(R.id.BTG);
        BtS = findViewById(R.id.BTS);


    }

    public void intoSelectionWithMath(View view){
        Intent X = new Intent(this,ForumTypeSelector.class);
        X.putExtra("subject","Mathematics");
        startActivity(X);
    }
    public void intoSelectionWithHis(View view){
        Intent X = new Intent(this,ForumTypeSelector.class);
        X.putExtra("subject","History");
        startActivity(X);
    }
    public void intoSelectionWithPhy(View view){
        Intent X = new Intent(this,ForumTypeSelector.class);
        X.putExtra("subject","Physics");
        startActivity(X);
    }
    public void intoSelectionWithGeo(View view){
        Intent X = new Intent(this,ForumTypeSelector.class);
        X.putExtra("subject","Geography");
        startActivity(X);
    }
    public void intoSelectionWithSpo(View view){
        Intent X = new Intent(this,ForumTypeSelector.class);
        X.putExtra("subject","Sports");
        startActivity(X);
    }
}