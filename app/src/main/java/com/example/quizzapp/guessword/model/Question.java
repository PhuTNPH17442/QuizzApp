package com.example.quizzapp.guessword.model;


public class Question {
    private String id;
    private String question;
    private String answer;
    private String theLoaiID;

    public Question() {
    }

    public Question(String id, String question, String answer, String theLoaiID) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.theLoaiID = theLoaiID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTheLoaiID() {
        return theLoaiID;
    }

    public void setTheLoaiID(String theLoaiID) {
        this.theLoaiID = theLoaiID;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", theLoaiID='" + theLoaiID + '\'' +
                '}';
    }
}