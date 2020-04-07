package com.steven.topquiz.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Steven - on 06/04/2020
 */

public class QuestionBank
{
    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> questionList)
    {
        mQuestionList = questionList;
        // Randomize questions order
        Collections.shuffle(mQuestionList);


        /*
        mNextQuestionIndex = new Random().nextInt(mQuestionList.size());
        mQuestionList = questionList;
        */
    }


    public Question getQuestion()
    {
        // Loop over the questions and return a new one at each call
        mNextQuestionIndex = (mNextQuestionIndex + 1)%mQuestionList.size();
        return mQuestionList.get(mNextQuestionIndex);
    }

/*
    private List<Question> ShuffleQuestions(List<Question> questions)
    {
        // Shuffle the question list before storing it
        List<Integer> allIndex = new ArrayList<Integer>();
        List<Question> newQuestionList = new ArrayList<Question>();

        Collections.shuffle(questions);


        return newQuestionList;
    }
*/

}
