package com.example.quizzapp.quizzLDSection.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.quizzapp.quizzLDSection.Audio.PlayAudioForAnswer;
import com.example.quizzapp.quizzLDSection.Dialog.CorrectDialog;
import com.example.quizzapp.quizzLDSection.Dialog.TimerDialog;
import com.example.quizzapp.quizzLDSection.Dialog.WrongDialog;
import com.example.quizzapp.R;
import com.example.quizzapp.quizzLDSection.constants.Constant;
import com.example.quizzapp.quizzLDSection.database.QuizDbHelper;
import com.example.quizzapp.quizzLDSection.model.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup rbGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btnConfirmNext;
    private TextView tvQuestions, tvScore, tvQuestionsCount, tvCountDown, tvCorrect, tvWrong;

    private ArrayList<Questions> questionList;
    private int questionCounter;
    private int questionTotalCount;
    private Questions currentQuestions;
    private boolean answerd;

    private final Handler handler = new Handler();

    private ColorStateList defaultTextColor;

    private int correctAns = 0, wrongAns = 0;

    private TimerDialog timerDialog;
    private CorrectDialog correctDialog;
    private WrongDialog wrongDialog;
    private PlayAudioForAnswer playAudioForAnswer;

    int FLAG = 0;

    int score = 0;

    private static final long COUNT_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private long backPressedTime;

    private String categoryValue = "";
    private int levelsID = 0;

    int UNLOCK_AL2 = 0, UNLOCK_AL3 = 0; //All Level
    int UNLOCK_HL2 = 0, UNLOCK_HL3 = 0; //History Level
    int UNLOCK_GL2 = 0, UNLOCK_GL3 = 0; //Geography Level
    int UNLOCK_SL2 = 0, UNLOCK_SL3 = 0; //Science Level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setupUI(); //??nh x??? d??? li???u

        Intent intentCategoryWithLevel = getIntent();
        categoryValue = intentCategoryWithLevel.getStringExtra("Category"); //nh???n d??? li???u th??? lo???i t??? CategoryActivity
        levelsID = intentCategoryWithLevel.getIntExtra("Level", 0); //nh???n d??? li???u ????? kh?? t??? LevelsActivity

        fetchDB();
        Log.i("BUGBUG","onCreate() in QuizActivity");

        defaultTextColor = rb1.getTextColors();

        timerDialog = new TimerDialog(this);
        correctDialog = new CorrectDialog(this);
        wrongDialog = new WrongDialog(this);
        playAudioForAnswer = new PlayAudioForAnswer(this);
    }

    private void setupUI() {
        tvQuestions = findViewById(R.id.tvQuestion);

        tvScore = findViewById(R.id.tvScore);
        tvQuestionsCount = findViewById(R.id.tvTotalQuestion);
        tvCountDown = findViewById(R.id.tvViewTimer);

        tvCorrect = findViewById(R.id.tvCorrect);
        tvWrong = findViewById(R.id.tvWrong);



        btnConfirmNext = findViewById(R.id.btnConfirmNext);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
    }

    private void fetchDB() {

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestionsWithCategoryAndLevels(levelsID, categoryValue); //g???i method truy xu???t sql v???i category&levels

        startQuiz();
    }

    private void startQuiz() {

        questionTotalCount = questionList.size();
        Collections.shuffle(questionList); //x??o tr???n ph???n t???

        showQuestions(); // g???i ?????n ph????ng th???c showQuestions

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.radio_button1:

                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_option_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));

                        break;
                    case R.id.radio_button2:

                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_option_selected));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));

                        break;
                    case R.id.radio_button3:

                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_option_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));

                        break;
                    case R.id.radio_button4:

                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_option_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));

                        break;
                }
            }
        });

        btnConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!answerd) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {

                        quizOperations();
                    } else {

                        Toast.makeText(QuizActivity.this, "H??y ch???n ????p ??n", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    private void quizOperations() {

        answerd = true;

        countDownTimer.cancel(); // h???y ?????m ng?????c

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        checkSolution(answerNr, rbSelected);

    }

    private void checkSolution(int answerNr, RadioButton rbSelected) {

        switch (currentQuestions.getAnswerNr()) {
            case 1:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb1.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_correct));

                    correctAns++;
                    tvCorrect.setText("????ng: " + String.valueOf(correctAns));

                    score += 20;
                    tvScore.setText("??i???m: " + String.valueOf(score));
                    correctDialog.correctDialog(score, this);

                    FLAG = 1;
                    playAudioForAnswer.setAudioForAnswer(FLAG);



                } else {

                    changeToIncorrectColor(rbSelected);

                    wrongAns++;
                    tvWrong.setText("Sai: " + String.valueOf(wrongAns));

                    score -= 5;
                    tvScore.setText("??i???m: " + String.valueOf(score));

                    String correctAnswer = (String) rb1.getText();
                    wrongDialog.wrongDialog(correctAnswer, this);

                    FLAG = 2;
                    playAudioForAnswer.setAudioForAnswer(FLAG);


                }
                break;

            case 2:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb2.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_correct));

                    correctAns++;
                    tvCorrect.setText("????ng: " + String.valueOf(correctAns));

                    score += 20;
                    tvScore.setText("??i???m: " + String.valueOf(score));
                    correctDialog.correctDialog(score, this);

                    FLAG = 1;
                    playAudioForAnswer.setAudioForAnswer(FLAG);



                } else {

                    changeToIncorrectColor(rbSelected);

                    wrongAns++;
                    tvWrong.setText("Sai: " + String.valueOf(wrongAns));

                    score -= 5;
                    tvScore.setText("??i???m: " + String.valueOf(score));

                    String correctAnswer = (String) rb2.getText();
                    wrongDialog.wrongDialog(correctAnswer, this);

                    FLAG = 2;
                    playAudioForAnswer.setAudioForAnswer(FLAG);


                }
                break;

            case 3:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb3.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_correct));

                    correctAns++;
                    tvCorrect.setText("????ng: " + String.valueOf(correctAns));

                    score += 20;
                    tvScore.setText("??i???m: " + String.valueOf(score));
                    correctDialog.correctDialog(score, this);

                    FLAG = 1;
                    playAudioForAnswer.setAudioForAnswer(FLAG);



                } else {

                    changeToIncorrectColor(rbSelected);

                    wrongAns++;
                    tvWrong.setText("Sai: " + String.valueOf(wrongAns));

                    score -= 5;
                    tvScore.setText("??i???m: " + String.valueOf(score));

                    String correctAnswer = (String) rb3.getText();
                    wrongDialog.wrongDialog(correctAnswer, this);

                    FLAG = 2;
                    playAudioForAnswer.setAudioForAnswer(FLAG);


                }
                break;

            case 4:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb4.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_correct));

                    correctAns++;
                    tvCorrect.setText("????ng: " + String.valueOf(correctAns));

                    score += 20;
                    tvScore.setText("??i???m: " + String.valueOf(score));
                    correctDialog.correctDialog(score, this);

                    FLAG = 1;
                    playAudioForAnswer.setAudioForAnswer(FLAG);



                } else {

                    changeToIncorrectColor(rbSelected);

                    wrongAns++;
                    tvWrong.setText("Sai: " + String.valueOf(wrongAns));

                    score -= 5;
                    tvScore.setText("??i???m: " + String.valueOf(score));

                    String correctAnswer = (String) rb4.getText();
                    wrongDialog.wrongDialog(correctAnswer, this);

                    FLAG = 2;
                    playAudioForAnswer.setAudioForAnswer(FLAG);


                }
                break;


        }

        if (questionCounter < questionTotalCount) {

            btnConfirmNext.setText("Ti???p t???c");
        }

    }

    private void changeToIncorrectColor(RadioButton rbSelected) {

        rbSelected.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_wrong));

        rbSelected.setTextColor(Color.BLACK);
    }

    //Hi???n th??? c??u h???i
    public void showQuestions() {

        rbGroup.clearCheck();

        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));

        if (questionCounter < questionTotalCount) {

            currentQuestions = questionList.get(questionCounter);
            tvQuestions.setText(currentQuestions.getQuestion());
            rb1.setText(currentQuestions.getOption1());
            rb2.setText(currentQuestions.getOption2());
            rb3.setText(currentQuestions.getOption3());
            rb4.setText(currentQuestions.getOption4());

            questionCounter++;
            answerd = false;

            btnConfirmNext.setText("Ti???p t???c");

            tvQuestionsCount.setText("C??u h???i: " + questionCounter + "/" + questionTotalCount);

            timeLeftInMillis = COUNT_IN_MILLIS;
            startCountDown();


        } else {
            //Khi ch???y h???t s??? c??u h???i s??? hi???n ra th??ng b??o
            Toast.makeText(this, "Ho??n th??nh Quiz", Toast.LENGTH_SHORT).show();

            rb1.setClickable(false);
            rb2.setClickable(false);
            rb3.setClickable(false);
            rb4.setClickable(false);
            btnConfirmNext.setClickable(false);

            //S??? d???ng handler ????? delay hi???n th???
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    finalResult();

                }
            }, 1000);
        }
    }


    // code b???t ?????u ?????m ng?????c
    private void startCountDown() {

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;

                updateCountDownText();
            }

            @Override
            public void onFinish() {

                timeLeftInMillis = 0;
                updateCountDownText();

            }
        }.start();

    }

    //code update ?????m ng?????c t???ng gi??y
    private void updateCountDownText() {

        int minutes = (int) (timeLeftInMillis/1000) / 60;
        int seconds = (int) (timeLeftInMillis/1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {

            tvCountDown.setTextColor(Color.RED);

            FLAG = 3;
            playAudioForAnswer.setAudioForAnswer(FLAG);

        } else {

            tvCountDown.setTextColor(defaultTextColor);

        }

        if (timeLeftInMillis == 0) {

            Toast.makeText(this, "H???t th???i gian", Toast.LENGTH_SHORT).show();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    timerDialog.timerDialog(QuizActivity.this);

                }
            }, 2000);

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("BUGBUG","onRestart() in QuizActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in QuizActivity");
        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("BUGBUG","onPause() in QuizActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("BUGBUG","onResume() in QuizActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("BUGBUG","onStart() in QuizActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        Log.i("BUGBUG","onDestroy() in QuizActivity");
    }

    private void finalResult() {

        unlockLevels();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Chuy???n d??? li???u ?????n ResultActivity
                Intent resultData = new Intent(QuizActivity.this, ResultActivity.class);

                resultData.putExtra("UserScore", score);
                resultData.putExtra("TotalQuestion", questionTotalCount);
                resultData.putExtra("CorrectQues", correctAns);
                resultData.putExtra("WrongQues", wrongAns);

                resultData.putExtra("Category", categoryValue);
                resultData.putExtra("Level", levelsID);

                startActivity(resultData);
            }
        }, 1000);

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            Intent intent = new Intent(QuizActivity.this, CategoryActivity.class);
            startActivity(intent);

        } else {

            Toast.makeText(this, "Nh???n l???i n??t Back ????? tho??t", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    //M??? kh??a levels
    private void unlockLevels() {

        unlockAllLevels();
        
        unlockHistoryLevels();

        unlockGeographyLevels();

        unlockScienceLevels();

    }


    private void unlockAllLevels() {

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE,
                        Context.MODE_PRIVATE);

        if (levelsID == 1 && categoryValue.equals("All")) {

            UNLOCK_AL2 = correctAns;
            //Tra loi dung >= 3 cau hoi de unlock level
            if (UNLOCK_AL2 >= 3) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Constant.KEY_ALL_LEVEL_2, 1);
                editor.apply();

                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.putString(Constant.KEY_CAT_ALL_LEVEL_2, "Unlock");
                editor1.apply();

            }

        } else if (levelsID == 2 && categoryValue.equals("All")) {

            UNLOCK_AL3 = correctAns;

            if (sharedPreferences.getInt(Constant.KEY_ALL_LEVEL_2, 0) == 1) {
                //Tra loi dung >= 3 cau hoi de unlock level
                if (UNLOCK_AL3 >= 3) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(Constant.KEY_ALL_LEVEL_3, 1);
                    editor.apply();

                    SharedPreferences.Editor editor1 = sharedPreferences.edit();
                    editor1.putString(Constant.KEY_CAT_ALL_LEVEL_3, "Unlock");
                    editor1.apply();
                }
            }

        }
    }


    private void unlockHistoryLevels() {

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE,
                        Context.MODE_PRIVATE);

        if (levelsID == 1 && categoryValue.equals("History")) {

            UNLOCK_HL2 = correctAns;
            //Tra loi dung >= 3 cau hoi de unlock level
            if (UNLOCK_HL2 >= 3) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Constant.KEY_HIS_LEVEL_2, 1);
                editor.apply();

                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.putString(Constant.KEY_CAT_HIS_LEVEL_2, "Unlock");
                editor1.apply();

            }
        } else if (levelsID == 2 && categoryValue.equals("History")) {

            UNLOCK_AL3 = correctAns;

            if (sharedPreferences.getInt(Constant.KEY_HIS_LEVEL_2, 0) == 1) {
                //Tra loi dung >= 3 cau hoi & da unlock level 2 de unlock level moi
                if (UNLOCK_AL3 >= 3) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(Constant.KEY_HIS_LEVEL_3, 1);
                    editor.apply();

                    SharedPreferences.Editor editor1 = sharedPreferences.edit();
                    editor1.putString(Constant.KEY_CAT_HIS_LEVEL_3, "Unlock");
                    editor1.apply();
                }
            }

        }
    }


    private void unlockGeographyLevels() {

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE,
                        Context.MODE_PRIVATE);

        if (levelsID == 1 && categoryValue.equals("Geography")) {

            UNLOCK_GL2 = correctAns;
            //Tra loi dung >= 3 cau hoi de unlock level
            if (UNLOCK_GL2 >= 3) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Constant.KEY_GEO_LEVEL_2, 1);
                editor.apply();

                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.putString(Constant.KEY_CAT_GEO_LEVEL_2, "Unlock");
                editor1.apply();

            }

        } else if (levelsID == 2 && categoryValue.equals("Geography")) {

            UNLOCK_AL3 = correctAns;

            if (sharedPreferences.getInt(Constant.KEY_GEO_LEVEL_2, 0) == 1) {
                //Tra loi dung >= 3 cau hoi de unlock level
                if (UNLOCK_AL3 >= 3) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(Constant.KEY_GEO_LEVEL_3, 1);
                    editor.apply();

                    SharedPreferences.Editor editor1 = sharedPreferences.edit();
                    editor1.putString(Constant.KEY_CAT_GEO_LEVEL_3, "Unlock");
                    editor1.apply();
                }
            }

        }
    }

    private void unlockScienceLevels() {

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE,
                        Context.MODE_PRIVATE);

        if (levelsID == 1 && categoryValue.equals("Science")) {

            UNLOCK_SL2 = correctAns;
            //Tra loi dung >= 3 cau hoi de unlock level
            if (UNLOCK_SL2 >= 3) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Constant.KEY_SCI_LEVEL_2, 1);
                editor.apply();

                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.putString(Constant.KEY_CAT_SCI_LEVEL_2, "Unlock");
                editor1.apply();

            }

        } else if (levelsID == 2 && categoryValue.equals("Science")) {

            UNLOCK_AL3 = correctAns;

            if (sharedPreferences.getInt(Constant.KEY_SCI_LEVEL_2, 0) == 1) {
                //Tra loi dung >= 3 cau hoi de unlock level
                if (UNLOCK_AL3 >= 3) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(Constant.KEY_SCI_LEVEL_3, 1);
                    editor.apply();

                    SharedPreferences.Editor editor1 = sharedPreferences.edit();
                    editor1.putString(Constant.KEY_CAT_SCI_LEVEL_3, "Unlock");
                    editor1.apply();
                }
            }

        }
    }


}