package com.example.quizzapp.guessword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzapp.MainActivity;
import com.example.quizzapp.R;

public class ManHinhTinhDiem extends AppCompatActivity {
    TextView tvHighScore, tvTotalQuizQuestion, tvCorrectQues, tvWrongQues;
    Button btnStartQuiz, btnMainMenu, btnPlayAgain;

    private int highScore, correctAns, wrongAns;
    public static final String SHARED_PREFERENCE = "shared_preference";
    public static final String SHARED_PREFERENCE_HIGH_SCORE = "shared_preference_high_score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_tinh_diem);
        btnMainMenu = findViewById(R.id.btnTrangChu);
        btnStartQuiz = findViewById(R.id.btnChoiLai);
        btnPlayAgain = findViewById(R.id.btnChoiLai);

        tvHighScore = findViewById(R.id.tvDiemCuaBan);
        tvTotalQuizQuestion = findViewById(R.id.tvTongDiem);
        tvCorrectQues = findViewById(R.id.tvCauDung);
        tvWrongQues = findViewById(R.id.tvCauSai);

        Intent intent = getIntent();

        //lấy dữ liệu từ mainactivity2
        highScore = intent.getIntExtra("totalScore", 0);
        correctAns = intent.getIntExtra("correctAns", 0);
        wrongAns = intent.getIntExtra("wrongAns", 0);

        tvTotalQuizQuestion.setText("Tổng điểm : " + highScore);
        tvCorrectQues.setText("Câu đúng : " + correctAns);
        tvWrongQues.setText("Câu sai : " + wrongAns);

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManHinhTinhDiem.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ManHinhTinhDiem.this, MainActivity2.class);
                startActivity(intent1);
            }
        });
    }

    //chuyển về màn hình chính
    public void sendToLevelAgain(View view) {
        Intent intent = new Intent(ManHinhTinhDiem.this, ManHinhChinh.class);
        startActivity(intent);
    }
}