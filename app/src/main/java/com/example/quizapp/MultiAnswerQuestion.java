package com.example.quizapp;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MultiAnswerQuestion extends Question {

    private ArrayList<String> options;
    private ArrayList<Integer> answerIndices;

    public MultiAnswerQuestion(String question, int imageId, ArrayList<String> options, ArrayList<Integer> answerIndices) {
        super(question, imageId);
        this.options = options;
        this.answerIndices = answerIndices;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public ArrayList<Integer> getAnswerIndices() {
        return answerIndices;
    }
}
