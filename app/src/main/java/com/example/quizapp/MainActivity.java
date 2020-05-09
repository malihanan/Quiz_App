package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int currentIndex;
    int multiple;
    int score;
    ArrayList<Question> questions;
    TextView questionTextView;
    ImageView questionImageView;
    RadioGroup optionsRadioGroup;
    RadioButton option0Radio;
    RadioButton option1Radio;
    RadioButton option2Radio;
    TextView errorTextView;
    TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = (TextView) findViewById(R.id.question_text_view);
        questionImageView = (ImageView) findViewById(R.id.question_image_view);
        optionsRadioGroup = (RadioGroup) findViewById(R.id.answer_radio_group);
        option0Radio = (RadioButton) findViewById(R.id.option0);
        option1Radio = (RadioButton) findViewById(R.id.option1);
        option2Radio = (RadioButton) findViewById(R.id.option2);
        errorTextView = (TextView) findViewById(R.id.error_text);
        scoreTextView = (TextView) findViewById(R.id.score_text_main);

        setQuestions();
        currentIndex = 0;
        score = 0;
        multiple = 100 / questions.size();
        scoreTextView.setText("" + score);
        setQuestion();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (optionsRadioGroup.getCheckedRadioButtonId() == -1) {
                    errorTextView.setText("Select an option.");
                    return;
                }
                errorTextView.setText("");
                setScore();
                scoreTextView.setText("" + score);
                currentIndex += 1;
                if (currentIndex < questions.size()) {
                    setQuestion();
                    optionsRadioGroup.clearCheck();
                } else {
                    currentIndex = 0;
                    Intent intent = new Intent(getApplicationContext(), ScoreBoardActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void setQuestion() {
        questionTextView.setText(questions.get(currentIndex).getQuestion());
        questionImageView.setImageResource(questions.get(currentIndex).getImageId());
        option0Radio.setText(questions.get(currentIndex).getOptions().get(0));
        option1Radio.setText(questions.get(currentIndex).getOptions().get(1));
        option2Radio.setText(questions.get(currentIndex).getOptions().get(2));
    }

    private boolean checkAnswer() {
        int correctOption = questions.get(currentIndex).getAnswerIndex();
        switch (correctOption) {
            case 0:
                return option0Radio.isChecked();
            case 1:
                return option1Radio.isChecked();
            case 2:
                return option2Radio.isChecked();
            default:
                return false;
        }
    }

    private void setScore() {
        if (checkAnswer()) {
            score += multiple;
        }
    }

    private void setQuestions() {
        questions = new ArrayList<Question>(){{
            add(new Question(getString(R.string.question1), new ArrayList<String>() {{
                add(getString(R.string.option1_1));
                add(getString(R.string.option1_2));
                add(getString(R.string.option1_3));
            }}, 1, R.drawable.img01));
            add(new Question(getString(R.string.question2), new ArrayList<String>() {{
                add(getString(R.string.option2_1));
                add(getString(R.string.option2_2));
                add(getString(R.string.option2_3));
            }}, 0, R.drawable.img02));
            add(new Question(getString(R.string.question3), new ArrayList<String>() {{
                add(getString(R.string.option3_1));
                add(getString(R.string.option3_2));
                add(getString(R.string.option3_3));
            }}, 2, R.drawable.img03));
            add(new Question(getString(R.string.question4), new ArrayList<String>() {{
                add(getString(R.string.option4_1));
                add(getString(R.string.option4_2));
                add(getString(R.string.option4_3));
            }}, 0, R.drawable.img04));
            add(new Question(getString(R.string.question5), new ArrayList<String>() {{
                add(getString(R.string.option5_1));
                add(getString(R.string.option5_2));
                add(getString(R.string.option5_3));
            }}, 0, R.drawable.img05));
            add(new Question(getString(R.string.question6), new ArrayList<String>() {{
                add(getString(R.string.option6_1));
                add(getString(R.string.option6_2));
                add(getString(R.string.option6_3));
            }}, 1, R.drawable.img06));
            add(new Question(getString(R.string.question7), new ArrayList<String>() {{
                add(getString(R.string.option7_1));
                add(getString(R.string.option7_2));
                add(getString(R.string.option7_3));
            }}, 0, R.drawable.img07));
            add(new Question(getString(R.string.question8), new ArrayList<String>() {{
                add(getString(R.string.option8_1));
                add(getString(R.string.option8_2));
                add(getString(R.string.option8_3));
            }}, 2, R.drawable.img08));
            add(new Question(getString(R.string.question9), new ArrayList<String>() {{
                add(getString(R.string.option9_1));
                add(getString(R.string.option9_2));
                add(getString(R.string.option9_3));
            }}, 0, R.drawable.img09));
            add(new Question(getString(R.string.question10), new ArrayList<String>() {{
                add(getString(R.string.option10_1));
                add(getString(R.string.option10_2));
                add(getString(R.string.option10_3));
            }}, 2, R.drawable.img10));
        }};
    }
}
