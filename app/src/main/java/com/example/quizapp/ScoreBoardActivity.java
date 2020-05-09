package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        Bundle bundle = getIntent().getExtras();
        int score = (Integer)bundle.get("score");

        TextView scoreTextView = (TextView) findViewById(R.id.score_text);
        scoreTextView.setText("" + score);
    }

    public void goToMain(android.view.View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }


}
