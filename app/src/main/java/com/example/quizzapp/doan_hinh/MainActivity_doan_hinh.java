package com.example.quizzapp.doan_hinh;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzapp.R;
import com.example.quizzapp.quizzLDSection.Activities.AllLevelsActivity;
import com.example.quizzapp.quizzLDSection.Activities.CategoryActivity;
import com.example.quizzapp.quizzLDSection.Audio.PlayAudioForAnswer;

public class    MainActivity_doan_hinh extends AppCompatActivity {
    private ImageView mQuizImage;
    private String mAnswer;
    private int score = 0;
    private int mQuizNum = 1;
    private int QuestionNum = 0;
    TextView mQuestionView, mQuizNumView;
    private Questions mQuestions = new Questions();

    int FLAG = 0;
    PlayAudioForAnswer playAudioForAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doanhinh);
        mQuestionView = findViewById(R.id.question);
        mQuizNumView = findViewById(R.id.quiznumber);
        Button btnSubmit = findViewById(R.id.btnsubmit);

        playAudioForAnswer = new PlayAudioForAnswer(this);

        updateQuestion();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestions.getType(QuestionNum) == "radiobutton"){
                    if (mQuestions.getCoorect(QuestionNum).equals(mAnswer)){
                        score++;
                        FLAG = 1;
                        playAudioForAnswer.setAudioForAnswer(FLAG);
                        displayCoorect();
                    }else {
                        FLAG = 2;
                        playAudioForAnswer.setAudioForAnswer(FLAG);
                        displayWrong();
                    }
                }
                SystemClock.sleep(1000);
                if (QuestionNum == mQuestions.getLength()-1){
                    Intent intent_result = new Intent(MainActivity_doan_hinh.this, ResultActivity_doan_hinh.class);
                    intent_result.putExtra("totalQuestions",mQuestions.getLength());
                    intent_result.putExtra("finalScore",score);
                    startActivity(intent_result);

                    QuestionNum = 0;
                    mQuizNum = 0;
                    score = 0;
                }else {
                    QuestionNum++;
                    mQuizNum++;
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateQuestion();
                    }
                },1000);


            }
        });
    }
    private void showImage(){
         mQuizImage = findViewById(R.id.quiz_image);
         String img = mQuestions.getImages(QuestionNum);
         mQuizImage.setImageResource(getResources().getIdentifier(img,"drawable",getPackageName()));

    }

    private void displayCoorect(){
        Toast.makeText(this, "????p ??n ch??nh x??c", Toast.LENGTH_SHORT).show();
    }
    private void displayWrong(){
        Toast.makeText(this, "????p ??n sai", Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion(){
        LinearLayout answerLayout = findViewById(R.id.answers);
        answerLayout.removeAllViews();
        mAnswer="";

        mQuizNumView.setText(mQuizNum + "/" + String.valueOf(mQuestions.getLength()));
        mQuestionView.setText(mQuestions.getQuestions(QuestionNum));

        if (mQuestions.getType(QuestionNum) == "radiobutton"){
            showRadio(QuestionNum);
        }
        showImage();
        ScrollView sv = findViewById(R.id.scrollview);
        sv.smoothScrollTo(0,0);
    }

    private void showRadio(int qnum){
        final LinearLayout answerLayout = findViewById(R.id.answers);

        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rg.setLayoutParams(lp);
        rg.setPadding(90,0,0,0);

        final RadioButton[] rb1 = new RadioButton[3];
        for (int i = 0; i<=2;i++){
            rb1[i] = new RadioButton(this);
            rb1[i].setText(mQuestions.getChoice(qnum) [i]);
            rb1[i].setTextColor(Color.BLACK);
            rb1[i].setPadding(8,16,8,16);
            rb1[i].setTextSize(25);
            rb1[i].setId(i);
            rb1[i].setWidth(550);

            rg.addView(rb1[i]);
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int Id) {
                mAnswer = mQuestions.getChoice(QuestionNum)[Id];
            }
        });
        answerLayout.addView(rg);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in MainActivity_doan_hinh");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BUGBUG","onDestroy() in MainActivity_doan_hinh");
        finish();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MainActivity_doan_hinh.this, MainActivity_start_doan_hinh.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

}