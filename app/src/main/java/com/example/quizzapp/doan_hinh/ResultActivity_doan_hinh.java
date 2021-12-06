package com.example.quizzapp.doan_hinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzapp.MenuGameActivity;
import com.example.quizzapp.R;

public class ResultActivity_doan_hinh extends AppCompatActivity {
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
        String final_Score = getString(R.string.txtCorrectScore,finalScore, totalQuetions);

        txtCorect.setText(final_Score);

        Button btnRestar = findViewById(R.id.btnrestar);
        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity_doan_hinh.super.onBackPressed();
            }
        });
//        Button btnhome = findViewById(R.id.btnhome);
//        btnhome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(ResultActivity_doan_hinh.this, MenuGameActivity.class);
//                startActivity(intent1);
//            }
//        });
    }

}