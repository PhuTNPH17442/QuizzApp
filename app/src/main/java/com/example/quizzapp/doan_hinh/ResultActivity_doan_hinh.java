package com.example.quizzapp.doan_hinh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzapp.MainActivity;
import com.example.quizzapp.MenuGameActivity;
import com.example.quizzapp.R;
import com.example.quizzapp.quizzLDSection.Activities.ResultActivity;

public class ResultActivity_doan_hinh extends AppCompatActivity {
    TextView txtCorect, txtPercent;
    private int totalQuetions;
    private int finalScore;

    private long backPressedTime;

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
        Button btnhome = findViewById(R.id.btnhome);
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ResultActivity_doan_hinh.this, MenuGameActivity.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            Intent intent = new Intent(ResultActivity_doan_hinh.this, MainActivity.class);
            startActivity(intent);

        } else {

            Toast.makeText(this, "Nhấn lại nút Back để thoát", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in ResultActivity_doan_hinh");
        finish();

    }

}