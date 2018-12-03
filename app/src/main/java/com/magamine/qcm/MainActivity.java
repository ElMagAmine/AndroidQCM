package com.magamine.qcm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.magamine.qcm.Adapters.MyStepperAdapter;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class MainActivity extends AppCompatActivity implements StepperLayout.StepperListener {

    public StepperLayout mStepperLayout;
    private MyStepperAdapter mStepperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperAdapter = new MyStepperAdapter(getSupportFragmentManager(), MainActivity.this);
        mStepperLayout.setAdapter(mStepperAdapter);
        mStepperLayout.setListener(MainActivity.this);
        MyAppSingleton.playerScore = 0;
    }
    @Override
    public void onCompleted(View completeButton) {
        Toast.makeText(MainActivity.this, "onCompleted!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(VerificationError verificationError) {
        Toast.makeText(MainActivity.this, "onError! -> " + verificationError.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }
    @Override
    public void onReturn() {
        //finish();
    }
}
