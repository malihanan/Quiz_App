package com.example.quizapp;

public class Question {
    private String question;
    private int imageId;


    public Question(String question, int imageId) {
        this.question = question;
        this.imageId = imageId;
    }

    public String getQuestion() {
        return question;
    }

    public int getImageId() {
        return imageId;
    }
}