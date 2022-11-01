package com.example.menntuneducationalapplication;

public class Forum {
    String Question;
    String Subject;


    public Forum(String question,String subject) {
        Question = question;
        Subject = subject;


    }

    public void setQuestion(String question) {
        Question = question;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getQuestion() {
        return Question;
    }

    public String getSubject() {
        return Subject;
    }

}
