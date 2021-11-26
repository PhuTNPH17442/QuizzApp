package com.example.quizzapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class SplashScreenActivity extends AppCompatActivity {

    private final static int EXIT_CODE = 100;

    TextView tvSplashText;
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

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