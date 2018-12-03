package com.magamine.qcm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.lzyzsd.circleprogress.DonutProgress;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        DonutProgress pb = findViewById(R.id.pbResults);
        pb.setProgress(MyAppSingleton.playerScore*100/4);
    }
}
