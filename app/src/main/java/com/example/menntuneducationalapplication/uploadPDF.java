package com.example.menntuneducationalapplication;

public class uploadPDF {
    String url;
    String Subject;
    String year;
    String Grade;


    public uploadPDF() {

    }

    public uploadPDF(String url, String subject, String year, String grade) {
        this.url = url;
        Subject = subject;
        this.year = year;
        Grade = grade;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }
}