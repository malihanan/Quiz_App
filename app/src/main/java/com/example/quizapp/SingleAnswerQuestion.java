package com.example.quizapp;

import java.util.ArrayList;

public class SingleAnswerQuestion extends Question {

    private ArrayList<String> options;
    private int answerIndex;

    public SingleAnswerQuestion(String question, int imageId, ArrayList<String> options, int answerIndex) {
        super(question, imageId);
        this.options = options;
        this.answerIndex = answerIndex;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }
}
