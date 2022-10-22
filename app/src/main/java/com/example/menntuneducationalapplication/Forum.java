package com.example.menntuneducationalapplication;

public class Forum {
    String Question;
    String Subject;
    String [] Answers;

    public Forum(String question) {
        Question = question;
    }

    public String getQuestion() {
        return Question;
    }

    public String getSubject() {
        return Subject;
    }

    public String[] getAnswers() {
        return Answers;
    }
}
