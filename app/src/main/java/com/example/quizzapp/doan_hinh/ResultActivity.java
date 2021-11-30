package com.example.quizzapp.doan_hinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzapp.R;

public class ResultActivity extends AppCompatActivity {
    TextView txtCorect, txtPercent;
    private int totalQuetions;
    private int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_doanhinh);
        txtCorect = findViewById(R.id.coorect);
        txtPercent = findViewById(R.id.percent);

        Intent intent = getIntent();
        totalQuetions = intent.getIntExtra("totalQuestions",0);
        finalScore = intent.getIntExtra("finalScore",0);

        int mPercent = finalScore * 100/ totalQuetions;


        txtPercent.setText(getString(R.string.score_percent, mPercent));
        String final_Score = getString(R.string.txtCorrectScore,finalScore, 10);

        txtCorect.setText(final_Score);

        Button btnRestar = findViewById(R.id.btnrestar);
        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.super.onBackPressed();
            }
        });
    }

}