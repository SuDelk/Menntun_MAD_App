package com.example.menntuneducationalapplication;

public class Forum {
    String Question;
    String Subject;
    String [] Answers;
    int noOfReplies;


    public Forum(String question,String subject) {
        Question = question;
        Subject = subject;
        Answers = new String[10];
        noOfReplies=0;

    }
    public void setAnswers(String X){
        Answers[noOfReplies++]=X;
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
