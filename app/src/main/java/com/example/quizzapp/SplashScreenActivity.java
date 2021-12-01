package com.example.quizzapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class SplashScreenActivity extends AppCompatActivity {

    private final static int EXIT_CODE = 100;

    ProgressBar progressBar;
    TextView tvSplashText, tvProgress;
    ImageView imgLogo;
    
    private int progressStatus = 0;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.pro);
        tvProgress = findViewById(R.id.tvProgress);
        tvSplashText = findViewById(R.id.tvLogoText);
        imgLogo = findViewById(R.id.imgLogo);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.transition);
        imgLogo.setAnimation(animation);
        tvSplashText.setAnimation(animation);

        //Tạo luồng
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    //Ngừng thread sau 3s
                    sleep(3000);

                } catch (Exception e) {

                    e.printStackTrace();
                } finally {

                    GotoPlayActivity();
                }

            }
        });
        thread.start(); //Bắt đầu chạy thread

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus++;
                    android.os.SystemClock.sleep(30);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            tvProgress.setText(progressStatus + "%");
                        }
                    });
                }
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
////                        tvProgress.setVisibility(View.VISIBLE);
//                        tvProgress.setText("Đã tải xong...");
//                    }
//                });
            }
        }).start();
    }

    private void GotoPlayActivity() {

        startActivityForResult(new Intent(SplashScreenActivity.this, MainActivity.class), EXIT_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EXIT_CODE) {

            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("EXIT", true)) {
                    finish();
                }
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in SplashScreenActivity");
        finish();

    }
}