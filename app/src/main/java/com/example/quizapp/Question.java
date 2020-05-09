package com.example.quizapp;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> options;
    private int answerIndex;
    private int imageId;

    public Question(String question, ArrayList<String> options, int answerIndex, int imageId) {
        this.question = question;
        this.options = new ArrayList<>();
        this.options.addAll(options);
        this.answerIndex = answerIndex;
        this.imageId = imageId;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public int getImageId() {
        return imageId;
    }
}