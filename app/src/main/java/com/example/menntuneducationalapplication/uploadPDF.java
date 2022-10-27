package com.example.menntuneducationalapplication;

public class uploadPDF {

    public String url;
    public String Subject;


    public uploadPDF() {

    }

    public uploadPDF(String url, String subject) {
        this.url = url;
        Subject = subject;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}