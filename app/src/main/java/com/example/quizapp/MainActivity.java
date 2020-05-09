package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int currentIndex;
    int multiple;
    int score;
    Question currentQuestion;
    ArrayList<Question> questions;
    TextView questionTextView;
    ImageView questionImageView;
    RadioGroup optionsRadioGroup;
    RadioButton option0Radio;
    RadioButton option1Radio;
    RadioButton option2Radio;
    LinearLayout checkBoxList;
    CheckBox option0Checkbox;
    CheckBox option1Checkbox;
    CheckBox option2Checkbox;
    EditText answerText;
    TextView errorTextView;
    TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
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
                if (notAnswered()) {
                    errorTextView.setText("Select an option.");
                    return;
                }
                errorTextView.setText("");
                setScore();
                scoreTextView.setText("" + score);
                currentIndex += 1;
                if (currentIndex < questions.size()) {
                    setQuestion();
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

    private void setViews() {
        questionTextView = (TextView) findViewById(R.id.question_text_view);
        questionImageView = (ImageView) findViewById(R.id.question_image_view);
        optionsRadioGroup = (RadioGroup) findViewById(R.id.answer_radio_group);
        option0Radio = (RadioButton) findViewById(R.id.option0_rb);
        option1Radio = (RadioButton) findViewById(R.id.option1_rb);
        option2Radio = (RadioButton) findViewById(R.id.option2_rb);
        checkBoxList = (LinearLayout) findViewById(R.id.answer_checkboxes);
        option0Checkbox = (CheckBox) findViewById(R.id.option0_cb);
        option1Checkbox = (CheckBox) findViewById(R.id.option1_cb);
        option2Checkbox = (CheckBox) findViewById(R.id.option2_cb);
        answerText = (EditText) findViewById(R.id.answer_text);
        errorTextView = (TextView) findViewById(R.id.error_text);
        scoreTextView = (TextView) findViewById(R.id.score_text_main);
    }

    private void setQuestion() {
        currentQuestion = questions.get(currentIndex);
        questionTextView.setText(currentQuestion.getQuestion());
        questionImageView.setImageResource(currentQuestion.getImageId());

        if (currentQuestion.getClass() == SingleAnswerQuestion.class) {
            optionsRadioGroup.setVisibility(View.VISIBLE);
            checkBoxList.setVisibility(View.GONE);
            answerText.setVisibility(View.GONE);
            SingleAnswerQuestion q = (SingleAnswerQuestion) currentQuestion;
            option0Radio.setText(q.getOptions().get(0));
            option1Radio.setText(q.getOptions().get(1));
            option2Radio.setText(q.getOptions().get(2));
            optionsRadioGroup.clearCheck();
        } else if (currentQuestion.getClass() == MultiAnswerQuestion.class) {
            optionsRadioGroup.setVisibility(View.GONE);
            checkBoxList.setVisibility(View.VISIBLE);
            answerText.setVisibility(View.GONE);
            MultiAnswerQuestion q = (MultiAnswerQuestion) currentQuestion;
            option0Checkbox.setText(q.getOptions().get(0));
            option1Checkbox.setText(q.getOptions().get(1));
            option2Checkbox.setText(q.getOptions().get(2));
            option2Checkbox.setChecked(false);
            option1Checkbox.setChecked(false);
            option2Checkbox.setChecked(false);
        } else if (currentQuestion.getClass() == TextInputQuestion.class) {
            optionsRadioGroup.setVisibility(View.GONE);
            checkBoxList.setVisibility(View.GONE);
            answerText.setVisibility(View.VISIBLE);
            answerText.setText("");
        }
    }

    private boolean notAnswered() {
        if(currentQuestion.getClass() == SingleAnswerQuestion.class) {
            return optionsRadioGroup.getCheckedRadioButtonId() == -1;
        } else if(currentQuestion.getClass() == MultiAnswerQuestion.class) {
            return option0Checkbox.isChecked() && option1Checkbox.isChecked() && option2Checkbox.isChecked();
        } else if(currentQuestion.getClass() == TextInputQuestion.class) {
            return answerText.getText().length() == 0;
        } else {
            return false;
        }
    }

    private boolean checkAnswer() {
        if (currentQuestion.getClass() == SingleAnswerQuestion.class) {
            SingleAnswerQuestion q = (SingleAnswerQuestion) currentQuestion;
            int correctOption = q.getAnswerIndex();
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
        } else if (currentQuestion.getClass() == MultiAnswerQuestion.class) {
            MultiAnswerQuestion q = (MultiAnswerQuestion) currentQuestion;
            ArrayList<Integer> correctAnswerList = q.getAnswerIndices();
            boolean answer = true;
            answer &= (correctAnswerList.indexOf(0) >= 0) ? option0Checkbox.isChecked() : !option0Checkbox.isChecked();
            answer &= (correctAnswerList.indexOf(1) >= 0) ? option1Checkbox.isChecked() : !option1Checkbox.isChecked();
            answer &= (correctAnswerList.indexOf(2) >= 0) ? option2Checkbox.isChecked() : !option2Checkbox.isChecked();
            return answer;
        } else if (currentQuestion.getClass() == TextInputQuestion.class) {
            TextInputQuestion q = (TextInputQuestion) currentQuestion;
            return answerText.getText().toString().toLowerCase().equals(q.getAnswer());
        } else {
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
            add(new SingleAnswerQuestion(getString(R.string.question1), R.drawable.img01,
                    new ArrayList<String>() {{
                        add(getString(R.string.option1_1));
                        add(getString(R.string.option1_2));
                        add(getString(R.string.option1_3));
                    }}, 1));
            add(new TextInputQuestion(getString(R.string.question2), R.drawable.img02,
                    getString(R.string.answer2)));
            add(new SingleAnswerQuestion(getString(R.string.question3), R.drawable.img03,
                    new ArrayList<String>() {{
                        add(getString(R.string.option3_1));
                        add(getString(R.string.option3_2));
                        add(getString(R.string.option3_3));
                    }}, 2));
            add(new SingleAnswerQuestion(getString(R.string.question4), R.drawable.img04,
                    new ArrayList<String>() {{
                        add(getString(R.string.option4_1));
                        add(getString(R.string.option4_2));
                        add(getString(R.string.option4_3));
                    }}, 0));
            add(new MultiAnswerQuestion(getString(R.string.question5), R.drawable.img05,
                    new ArrayList<String>() {{
                        add(getString(R.string.option5_1));
                        add(getString(R.string.option5_2));
                        add(getString(R.string.option5_3));
                    }}, new ArrayList<Integer>() {{
                        add(0);
                        add(1);
                    }}));
            add(new TextInputQuestion(getString(R.string.question6), R.drawable.img06,
                    getString(R.string.answer6)));
            add(new SingleAnswerQuestion(getString(R.string.question7), R.drawable.img07,
                    new ArrayList<String>() {{
                        add(getString(R.string.option7_1));
                        add(getString(R.string.option7_2));
                        add(getString(R.string.option7_3));
                    }}, 0));
            add(new SingleAnswerQuestion(getString(R.string.question8), R.drawable.img08,
                    new ArrayList<String>() {{
                        add(getString(R.string.option8_1));
                        add(getString(R.string.option8_2));
                        add(getString(R.string.option8_3));
                    }}, 2));
            add(new SingleAnswerQuestion(getString(R.string.question9), R.drawable.img09,
                    new ArrayList<String>() {{
                        add(getString(R.string.option9_1));
                        add(getString(R.string.option9_2));
                        add(getString(R.string.option9_3));
                    }}, 0));
            add(new SingleAnswerQuestion(getString(R.string.question10), R.drawable.img10,
                    new ArrayList<String>() {{
                        add(getString(R.string.option10_1));
                        add(getString(R.string.option10_2));
                        add(getString(R.string.option10_3));
                    }}, 2));
        }};
    }
}
