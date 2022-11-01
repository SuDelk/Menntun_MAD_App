package com.example.menntuneducationalapplication;

import static android.view.View.INVISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.menntuneducationalapplication.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    Button logout,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = findViewById(R.id.logOut);
        home = findViewById(R.id.homeBtn);

        if(GlobalStudent._USER == null && GlobalTutor._USER == null){
            logout.setVisibility(INVISIBLE);
            home.setVisibility(INVISIBLE);

            Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            };

            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(runnable,2000);

        }
        else{

            if(GlobalTutor._USER != null){
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GlobalTutor._USER = null;
                        GlobalTutor._EMAIL = null;
                        GlobalTutor._PWD = null;
                        GlobalTutor._PHONE = null;
                        GlobalTutor._DOB = null;
                        GlobalTutor._NAME = null;

                        Toast.makeText(MainActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();

                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        };

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(runnable,2000);
                    }
                });
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,MainActivityTutor.class);
                        startActivity(intent);
                    }
                });
            }else if(GlobalStudent._USER !=null){
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GlobalStudent._USER = null;
                        GlobalStudent._EMAIL = null;
                        GlobalStudent._PWD = null;
                        GlobalStudent._PHONE = null;
                        GlobalStudent._DOB = null;
                        GlobalStudent._NAME = null;
                        GlobalStudent._GRADE = null;

                        Toast.makeText(MainActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();

                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        };

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(runnable,3000);
                    }
                });
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,MainActivityStudent.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }
    protected void onResume() {
        super.onResume();
        if(GlobalStudent._USER == null && GlobalTutor._USER == null){
            Toast.makeText(MainActivity.this, "Log in first...", Toast.LENGTH_SHORT).show();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            };

            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(runnable,2000);
        }
    }
}