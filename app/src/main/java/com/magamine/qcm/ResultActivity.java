package com.magamine.qcm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.lzyzsd.circleprogress.DonutProgress;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        DonutProgress pb = findViewById(R.id.pbResults);
        pb.setProgress(MyAppSingleton.playerScore*100/4);

        Button mEmailSignInButton = (Button) findViewById(R.id.btn_try);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAppSingleton.getInstance(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(0,0);
                //finish();
            }
        });
    }
}
