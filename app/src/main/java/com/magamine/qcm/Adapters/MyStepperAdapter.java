package com.magamine.qcm.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.magamine.qcm.Fragments.Question;
import com.magamine.qcm.MyAppSingleton;
import com.magamine.qcm.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;


public class MyStepperAdapter extends AbstractFragmentStepAdapter {
    private static final String CURRENT_STEP_POSITION_KEY = "messageResourceId";
    public MyStepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        switch (position){
            case 0:
                MyAppSingleton.playerScore = 0;
                final Question step1 = new Question();
                Bundle b1 = new Bundle();
                MyAppSingleton.QuestionForm quest = MyAppSingleton.getRandomQuestion();
                b1.putInt(CURRENT_STEP_POSITION_KEY, position);
                b1.putString("QUESTION",quest.Qquestion);
                b1.putStringArray("ANSWERS",quest.Qanswers);
                b1.putInt("IMAGE", R.drawable.triangle);
                b1.putInt("CORRECT",quest.Correct);
                step1.setArguments(b1);
                return step1;
            case 1:
                final Question step2 = new Question();
                Bundle b2 = new Bundle();
                MyAppSingleton.QuestionForm quest2 = MyAppSingleton.getRandomQuestion();
                b2.putInt(CURRENT_STEP_POSITION_KEY, position);
                b2.putString("QUESTION",quest2.Qquestion);
                b2.putStringArray("ANSWERS",quest2.Qanswers);
                b2.putInt("IMAGE",R.drawable.triangle);
                b2.putInt("CORRECT",quest2.Correct);
                step2.setArguments(b2);
                return step2;
            case 2:
                final Question step3 = new Question();
                Bundle b3 = new Bundle();
                MyAppSingleton.QuestionForm quest3 = MyAppSingleton.getRandomQuestion();
                b3.putInt(CURRENT_STEP_POSITION_KEY, position);
                b3.putString("QUESTION",quest3.Qquestion);
                b3.putStringArray("ANSWERS",quest3.Qanswers);
                b3.putInt("IMAGE",R.drawable.triangle);
                b3.putInt("CORRECT",quest3.Correct);
                step3.setArguments(b3);
                return step3;
            case 3:
                final Question step4 = new Question();
                Bundle b4 = new Bundle();
                MyAppSingleton.QuestionForm quest4 = MyAppSingleton.getRandomQuestion();
                b4.putInt(CURRENT_STEP_POSITION_KEY, position);
                b4.putString("QUESTION",quest4.Qquestion);
                b4.putStringArray("ANSWERS",quest4.Qanswers);
                b4.putInt("IMAGE",R.drawable.triangle);
                b4.putInt("CORRECT",quest4.Correct);
                step4.setArguments(b4);
                return step4;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        switch (position){
            case 0: {
                return new StepViewModel.Builder(context)
                        .setTitle("Question 1") //can be a CharSequence instead
                        .create();
            }
            case 1: {
                return new StepViewModel.Builder(context)
                        .setTitle("Question 2") //can be a CharSequence instead
                        .create();
            }
            case 2: {
                return new StepViewModel.Builder(context)
                        .setTitle("Question 3") //can be a CharSequence instead
                        .create();
            }
            case 3: {
                return new StepViewModel.Builder(context)
                        .setTitle("Question 4") //can be a CharSequence instead
                        .create();
            }
        }
        return null;
    }
}