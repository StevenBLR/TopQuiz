package com.steven.topquiz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.steven.topquiz.R;
import com.steven.topquiz.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{
    // UI
    @BindView(R.id.activity_main_greeting_txt)
    public TextView mGreetingText;
    @BindView(R.id.activiy_main_name_input)
    public EditText mNameInput;
    @BindView(R.id.activity_main_play_btn)
    public Button mPlayBt;

    // System
    private User mUser;
    private SharedPreferences mPreferences;

    // Constantes
    public static final int GAME_ACTIVITY_REQUEST_CODE = 12;
    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        textEvents();
        greetUser();
        mPlayBt.setEnabled(false);

        ButterKnife.bind(this);
        /*
        Vers
        mPlayBt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String firstName = mNameInput.getText().toString();
                mUser.setFirstName(firstName);
                mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstName()).apply();
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });
        */



    }

    @OnClick(R.id.activity_main_play_btn)
    public void onClick()
    {
        String firstName = mNameInput.getText().toString();
        mUser.setFirstName(firstName);
        mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstName()).apply();
        Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
        startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
    }

    // Debug
    @Override
    protected void onStart()
    {
        super.onStart();
        System.out.println("MainActivity :: onStart()");
    }



    private void init()
    {
        // Init values
        mPreferences = getPreferences(MODE_PRIVATE);
        mUser = new User();
/*
        // References to UI
        mGreetingText = findViewById(R.id.activity_main_greeting_txt);
        mNameInput = findViewById(R.id.activiy_main_name_input);
        mPlayBt = findViewById(R.id.activity_main_play_btn);
*/

    }

    private void textEvents()
    {
        mNameInput.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                mPlayBt.setEnabled((s.toString().length() != 0));
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode)
        {
            // Fetch the score from the Intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();
        }
    }

    private void greetUser()
    {
        String userName = mPreferences.getString(PREF_KEY_FIRSTNAME, null);
        int lastScore = mPreferences.getInt(PREF_KEY_SCORE, 0);

        mPreferences.getString(PREF_KEY_FIRSTNAME, userName);
        mPreferences.getInt(PREF_KEY_SCORE, lastScore);

        if (userName.length() > 0)
        {
            // Display Greeting
            displayGreetings("Welcome Back " + userName +" !", "Welcome back, your last score was " + lastScore + "\nDo you think you can do better ?");
        }
    }

    private void displayGreetings(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .create()
                .show();
    }

}
