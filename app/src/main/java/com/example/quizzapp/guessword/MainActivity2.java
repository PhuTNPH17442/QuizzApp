package com.example.quizzapp.guessword;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.MenuGameActivity;
import com.example.quizzapp.R;
import com.example.quizzapp.quizzLDSection.Activities.QuizActivity;
import com.example.quizzapp.quizzLDSection.Audio.PlayAudioForAnswer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    //data
    String[] question = new String[]{"Animals", "Beautiful", "Exited", "Vegetable", "Pigs",
            "Cute", "Handsome", "Pretty", "Blood", "Clever", "Funny", "Camera", "Design",
            "Film", "Movie", "Music", "Computer", "Blog", "Data", "Email", "Laptop", "Headline"
    };

    String que;
    Random random;
    TextView txtCorrectAnswer, txtRightAnswer, txtQuestionContainer,
            txtCauHoi, txtDiem, txtCauDung, txtCauSai, txtTime;
    EditText edYourAnswer;
    Button btnCheck, btnShow, btnNext;

    private final Handler handler = new Handler();

    //audio
    private PlayAudioForAnswer playAudioForAnswer;
    int FLAG = 0;

    //tính điểm,câu đúng,sai
    private int correctAns = 0, wrongAns = 0;
    int score = 0;

    private int questionCounter;
    private int questionTotalCount;

    private static final long COUNT_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);

        txtCorrectAnswer = findViewById(R.id.txtCorrectAnswer);
        txtQuestionContainer = findViewById(R.id.txtQuestionContainer);
        txtRightAnswer = findViewById(R.id.txtRightAnswer);
        txtCauHoi = findViewById(R.id.txtCauHoi);
        txtDiem = findViewById(R.id.txtDiem);
        txtCauDung = findViewById(R.id.txtSLCauDung);
        txtCauSai = findViewById(R.id.txtCauSai);
        txtTime = findViewById(R.id.txtTime);

        edYourAnswer = findViewById(R.id.edYourAnswer);
        btnCheck = findViewById(R.id.btnCheck);
        btnNext = findViewById(R.id.btnNext);
        btnShow = findViewById(R.id.btnShow);

//lấy về độ dài của chuỗi
        questionTotalCount = question.length;

        //audio
        playAudioForAnswer = new PlayAudioForAnswer(this);

        //random
//        random = new Random();
//        que = question[random.nextInt(question.length)];
//        txtQuestionContainer.setText(mixWords(que));
        showQuestion();


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edYourAnswer.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa điền đáp án !!!", Toast.LENGTH_SHORT).show();
                } else if (edYourAnswer.getText().toString().equalsIgnoreCase(que)) {
                    //dialog đáp án đúng
                    Dialog dialog = new Dialog(MainActivity2.this);
                    dialog.setContentView(R.layout.correct_dialog);
                    TextView text = dialog.findViewById(R.id.tv_score);
                    text.setText("" + que);

                    Button hide = dialog.findViewById(R.id.btn_correct_dialog);
                    correctAns++;
                    txtCauDung.setText("Câu đúng : " + String.valueOf(correctAns));
                    //set audio
                    FLAG = 1;
                    playAudioForAnswer.setAudioForAnswer(FLAG);
                    //set điểm
                    score += 20;
                    txtDiem.setText("Điểm: " + String.valueOf(score) + "/100");
                    dialog.show();
                    hide.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            countDownTimer.cancel();
                            showQuestion();
                        }
                    });
                    edYourAnswer.setText("");
//                    que = question[random.nextInt(question.length)];
//                    txtQuestionContainer.setText(mixWords(que));

                    //ẩn các view
                    txtCorrectAnswer.setVisibility(View.INVISIBLE);
                    txtRightAnswer.setVisibility(View.INVISIBLE);
                } else {

                    //dialog hiển thị báo đáp án sai
                    Dialog dialog = new Dialog(MainActivity2.this);
                    dialog.setContentView(R.layout.wrong_dialog);
                    Button hide = dialog.findViewById(R.id.btn_wrong_dialog);

                    TextView txtCauDung = dialog.findViewById(R.id.tv_correct_answer);
                    txtCauDung.setText("" + que);

                    //audio báo sai
                    FLAG = 2;
                    playAudioForAnswer.setAudioForAnswer(FLAG);
                    wrongAns++;
                    txtCauSai.setText("Câu sai : " + String.valueOf(wrongAns));
                    dialog.show();
                    hide.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            countDownTimer.cancel();
                            showQuestion();
                        }
                    });
                    edYourAnswer.setText("");
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                que = question[random.nextInt(question.length)];
//                txtQuestionContainer.setText(mixWords(que));
//                if (edYourAnswer.getText().toString().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Bạn chưa điền đáp án", Toast.LENGTH_SHORT).show();
//                } else {
                //dialog hiển thị báo đáp án sai
                Dialog dialog = new Dialog(MainActivity2.this);
                dialog.setContentView(R.layout.wrong_dialog);
                Button hide = dialog.findViewById(R.id.btn_wrong_dialog);

                TextView txtCauDung = dialog.findViewById(R.id.tv_correct_answer);
                txtCauDung.setText("" + que);

                //audio báo sai
                FLAG = 2;
                playAudioForAnswer.setAudioForAnswer(FLAG);
                wrongAns++;
                txtCauSai.setText("Câu sai : " + String.valueOf(wrongAns));
                dialog.show();
                hide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        countDownTimer.cancel();
                        showQuestion();
                    }
                });
//                countDownTimer.cancel();
//                showQuestion();
//                }
                edYourAnswer.setText("");
                txtCorrectAnswer.setVisibility(View.INVISIBLE);
                txtRightAnswer.setVisibility(View.INVISIBLE);
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity2.this);
                dialog.setContentView(R.layout.showanswer);
                dialog.show();
                TextView text = dialog.findViewById(R.id.tvgoiy);
                text.setText("" + que);
                Button hide = dialog.findViewById(R.id.btntt);
                hide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private String mixWords(String word) {
        List<String> words = Arrays.asList(word.split("")); //chuyển đổi array sang list
        //phương thức split tách chuỗi dựa trên ""
        Collections.shuffle(words); //xáo trộn phần tử
        String mixed = "";
        //Sử dụng vòng lặp for each để in ra các element của chuỗi thử được
        for (String i : words) {
            mixed += i;
            mixed += " / ";
        }

        return mixed;
    }

    public void showQuestion() {
        if (questionCounter < 5) {

            random = new Random();
            que = question[random.nextInt(question.length)];
            txtQuestionContainer.setText(mixWords(que));

            questionCounter++;

            txtCauHoi.setText("Câu hỏi: " + questionCounter + "/" + 5);

            timeLeftInMillis = COUNT_IN_MILLIS;
            startCountDown();
        } else {
            //Khi chạy hết số câu hỏi sẽ hiện ra thông báo
            Toast.makeText(this, "Hoàn thành Quiz", Toast.LENGTH_SHORT).show();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    //gửi dữ liệu
                    Intent intent = new Intent(MainActivity2.this, ManHinhTinhDiem.class);
                    intent.putExtra("totalScore", score);
                    intent.putExtra("correctAns", correctAns);
                    intent.putExtra("wrongAns", wrongAns);
                    startActivity(intent);
                }
            }, 1000);
        }
//        timeLeftInMillis = COUNT_IN_MILLIS;
//        startCountDown();
    }

    //hàm đếm ngược
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

    private void updateCountDownText() {

        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        txtTime.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {

            txtTime.setTextColor(Color.RED);

            FLAG = 3;
            playAudioForAnswer.setAudioForAnswer(FLAG);

        } else {
            txtTime.setTextColor(Color.BLACK);
        }

        if (timeLeftInMillis == 0) {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Dialog dialog = new Dialog(MainActivity2.this);
                    dialog.setContentView(R.layout.timer_dialog);

                    Button hide = dialog.findViewById(R.id.btn_timer);
                    hide.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            showQuestion();
                        }
                    });

                    dialog.show();
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                }
            }, 2000);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG", "onStop() in MainActivity");
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        Log.i("BUGBUG", "onDestroy() in MainActivity2");
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MainActivity2.this, ManHinhChinh.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

}
