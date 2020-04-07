package com.steven.blr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.steven.topquiz.R;

import java.util.List;

public class GameActivity extends AppCompatActivity
{
    private TextView mQuestion;
    private Button mAnswerBt1;
    private Button mAnswerBt2;
    private Button mAnswerBt3;
    private Button mAnswerBt4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // Refs to Layout
        mQuestion = findViewById(R.id.activity_game_question_text);
        mAnswerBt1 = findViewById(R.id.activity_game_answer1_btn);
        mAnswerBt2 = findViewById(R.id.activity_game_answer2_btn);
        mAnswerBt3 = findViewById(R.id.activity_game_answer3_btn);
        mAnswerBt4 = findViewById(R.id.activity_game_answer4_btn);

        mAnswerBt1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

    }


}
