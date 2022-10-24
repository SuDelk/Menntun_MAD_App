package com.example.menntuneducationalapplication;

import android.app.Application;

import java.util.ArrayList;

public class Global extends Application {
    private ArrayList<QuizModel> globalVariableOne;

    public ArrayList<QuizModel> getGlobalVariableOne() {
        return globalVariableOne;
    }

    public void setGlobalVariableOne(ArrayList<QuizModel> globalVariableOne) {
        this.globalVariableOne = globalVariableOne;
    }
}
