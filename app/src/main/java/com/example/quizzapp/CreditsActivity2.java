package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;
import android.widget.TextView;

public class CreditsActivity2 extends AppCompatActivity {

    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits2);

        scrollView = findViewById(R.id.scrollView_credits);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.credits);
        scrollView.setAnimation(animation);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(CreditsActivity2.this, SettingActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in CreditsActivity2");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BUGBUG","onDestroy() in CreditsActivity2");
        finish();
    }

}