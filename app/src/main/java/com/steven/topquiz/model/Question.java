package com.steven.topquiz.model;

import java.util.List;

/**
 * Created by Steven - on 06/04/2020
 */
public class Question
{
    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;
    private boolean mAnswered;

    public Question(String question, List<String> choiceList, int answerIndex)
    {
        mQuestion = question;
        mChoiceList = choiceList;
        mAnswerIndex = answerIndex;
    }

    // Question State -----------------------------
    public String getQuestion()
    {
        return mQuestion;
    }

    public void setQuestion(String question)
    {
        if(question.length() > 0) mQuestion = question;
    }

    // Choice List -----------------------------
    public List<String> getChoiceList()
    {
        return mChoiceList;
    }

    public void setChoiceList(List<String> choiceList)
    {
        mChoiceList = choiceList;
    }

    // Answer Index -----------------------------
    public int getAnswerIndex()
    {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int answerIndex)
    {
        if (answerIndex >= 0 && answerIndex < mChoiceList.size()) mAnswerIndex = answerIndex;
    }

    // IsAnswered State -----------------------------
    public boolean isAnswered()
    {
        return mAnswered;
    }

    public void setAnswered(boolean answered)
    {
        mAnswered = answered;
    }
}
