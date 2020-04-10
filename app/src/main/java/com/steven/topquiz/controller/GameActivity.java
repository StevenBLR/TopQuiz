package com.steven.topquiz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.steven.topquiz.R;
import com.steven.topquiz.model.Question;
import com.steven.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener
{
    // UI
    private TextView mQuestion;
    private Button mAnswerBt1;
    private Button mAnswerBt2;
    private Button mAnswerBt3;
    private Button mAnswerBt4;

    // System
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mNumberOfQuestions;
    private int mScore;

    private boolean mEnableTouchEvents;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();

        // Init Values
        if (savedInstanceState != null)
        {
            Log.d("Recuperation last value", "Recuperation last value");
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        }
        else
        {
            Log.d("Reset value", "Reset value");
            mEnableTouchEvents = true;
            mNumberOfQuestions = 4;
            mScore = 0;
        }
        displayQuestion(mCurrentQuestion);
    }

/*
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        savedInstanceState.putInt(BUNDLE_STATE_SCORE, mScore);
        savedInstanceState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);
        super.onPostCreate(savedInstanceState);
    }
*/
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);

        super.onSaveInstanceState(outState);
    }

    private void init()
    {
        // Question generation
        mQuestionBank = generateQuestions();
        mCurrentQuestion = mQuestionBank.getNextQuestion();

        // Refs to Layout
        mQuestion = findViewById(R.id.activity_game_question_text);
        mAnswerBt1 = findViewById(R.id.activity_game_answer1_btn);
        mAnswerBt2 = findViewById(R.id.activity_game_answer2_btn);
        mAnswerBt3 = findViewById(R.id.activity_game_answer3_btn);
        mAnswerBt4 = findViewById(R.id.activity_game_answer4_btn);

        // Setting Buttons tags
        mAnswerBt1.setTag(0);
        mAnswerBt2.setTag(1);
        mAnswerBt3.setTag(2);
        mAnswerBt4.setTag(3);

        // Linking Buttons to Click event
        mAnswerBt1.setOnClickListener(this);
        mAnswerBt2.setOnClickListener(this);
        mAnswerBt3.setOnClickListener(this);
        mAnswerBt4.setOnClickListener(this);
    }



    @Override
    public void onClick(View v)
    {
        // Test Processing
        int responseIndex = (int) v.getTag();
        if (responseIndex == mCurrentQuestion.getAnswerIndex())
        {
            // Good answer
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
        }
        else
        {
            // Wrong answer
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents = false;
        // Wait for 2 sec (2000 ms) and continue process
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                mEnableTouchEvents = true;
                // Next question or End
                if (--mNumberOfQuestions == 0) // Decrement number of question
                {
                    // End of the game
                    displayAlertBox("Well done !", "Your score is " + mScore, "OK");
                }
                else
                {
                    // Get next question and process it
                    mCurrentQuestion = mQuestionBank.getNextQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        },2000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        // If touch input enabled + input triggered --> continue input process
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void displayQuestion(final Question question)
    {
        mQuestion.setText(question.getQuestion());
        mAnswerBt1.setText(question.getChoiceList().get(0));
        mAnswerBt2.setText(question.getChoiceList().get(1));
        mAnswerBt3.setText(question.getChoiceList().get(2));
        mAnswerBt4.setText(question.getChoiceList().get(3));
    }

    private QuestionBank generateQuestions()
    {
        Question question1 = new Question("What is the name of the current french president?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        Question question2 = new Question("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Question question3 = new Question("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));
    }

    private void displayAlertBox(String title, String message, String positivBtn)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positivBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }
}
