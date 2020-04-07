package com.steven.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.steven.topquiz.R;
import com.steven.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayBt;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        textEvents();
        clickEvents();

        mPlayBt.setEnabled(false);
    }

    private void init()
    {
        // References aux elements graphiques
        mGreetingText = findViewById(R.id.activity_main_greeting_txt);
        mNameInput = findViewById(R.id.activiy_main_name_input);
        mPlayBt = findViewById(R.id.activity_main_play_btn);
        mUser = new User();
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

    private void clickEvents()
    {
        mPlayBt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String firstName = mNameInput.getText().toString();
                mUser.setFirstName(firstName);
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivityIntent);
            }
        });
    }
}
