package com.example.quizapp;

import java.util.ArrayList;

public class TextInputQuestion extends Question {
    String answer;

    public TextInputQuestion(String question, int imageId, String answer) {
        super(question, imageId);
        this.answer = answer.toLowerCase();
    }

    public String getAnswer() {
        return answer;
    }
}
