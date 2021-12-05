package com.example.quizzapp.quizzLDSection.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzapp.MainActivity;
import com.example.quizzapp.R;

public class ResultActivity extends AppCompatActivity {

    TextView tvHighScore, tvTotalQuizQuestion, tvCorrectQues, tvWrongQues;
    Button btnStartQuiz, btnMainMenu;

    private int highScore;
    public static final String SHARED_PREFERENCE = "shared_preference";
    public static final String SHARED_PREFERENCE_HIGH_SCORE = "shared_preference_high_score";

    private long backPressedTime;

    String categoryAgainValue = "";
    int levelsID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnMainMenu = findViewById(R.id.result_btn_mainmenu);
        btnStartQuiz = findViewById(R.id.resuLt_btn_playAgain);
        tvHighScore = findViewById(R.id.result_tv_High_Score);
        tvTotalQuizQuestion = findViewById(R.id.result_tv_total_Ques);
        tvCorrectQues = findViewById(R.id.result_tv_Correct_Ques);
        tvWrongQues = findViewById(R.id.result_tv_Wrong_Ques);

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
                intent.putExtra("Category", categoryAgainValue);
                intent.putExtra("Level", levelsID);
                startActivity(intent);

            }
        });

        loadHighScore();

        Intent intent = getIntent();

        int score = intent.getIntExtra("UserScore", 0);
        int totalQuestion = intent.getIntExtra("TotalQuestion", 0);
        int correctQues = intent.getIntExtra("CorrectQues", 0);
        int wrongQues = intent.getIntExtra("WrongQues", 0);

        categoryAgainValue = intent.getStringExtra("Category");
        levelsID = intent.getIntExtra("Level", 0);


        tvTotalQuizQuestion.setText("Các câu hỏi đã trờ lời: " + String.valueOf(totalQuestion));
        tvCorrectQues.setText("Đáp án đúng: " + String.valueOf(correctQues));
        tvWrongQues.setText("Đáp an sai: " + String.valueOf(wrongQues));

        if (score > highScore) {

            updateHighScore(score);
        }

    }

    private void updateHighScore(int newHighScore) {

        highScore = newHighScore;
        tvHighScore.setText("Điểm cao nhất: " + String.valueOf(highScore));

        //lưu điểm số cao nhất vào shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFERENCE_HIGH_SCORE, highScore);
        editor.apply();

    }

    private void loadHighScore() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(SHARED_PREFERENCE_HIGH_SCORE, 0);
        tvHighScore.setText("Điểm cao nhất: " + String.valueOf(highScore));

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);

        } else {

            Toast.makeText(this, "Nhấn lại nút Back để thoát", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in ResultActivity");
        finish();

    }

    public void sendToLevelAgain(View view) {

        if (categoryAgainValue.equals("All")) {

            Intent intentAll = new Intent(ResultActivity.this, AllLevelsActivity.class);
            startActivity(intentAll);

        } else if (categoryAgainValue.equals("History")) {

            Intent intentHistory = new Intent(ResultActivity.this, HistoryLevelsActivity.class);
            startActivity(intentHistory);

        }

    }
}