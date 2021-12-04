package com.example.quizzapp.guessword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzapp.R;

public class ManHinhTinhDiem extends AppCompatActivity {
    TextView tvHighScore, tvTotalQuizQuestion, tvCorrectQues, tvWrongQues;
    Button btnStartQuiz, btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_tinh_diem);
        btnMainMenu = findViewById(R.id.btnTrangChu);
        btnStartQuiz = findViewById(R.id.btnChoiLai);
        tvHighScore = findViewById(R.id.tvDiemCuaBan);
        tvTotalQuizQuestion = findViewById(R.id.tvTongDiem);
        tvCorrectQues = findViewById(R.id.tvCauDung);
        tvWrongQues = findViewById(R.id.tvCauSai);
    }

    public void sendToLevelAgain(View view) {
    }
}