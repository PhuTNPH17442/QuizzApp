package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {

    private Context mContext;
    private Switch mMusicCheckbox;
    private Button btn_ok;
    private boolean isMusicOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mContext = SettingActivity.this;
        AppController.currentActivity = this;

        initViews();

        populateMusicEnableContents();
    }

    private void initViews() {

        mMusicCheckbox = findViewById(R.id.music_checkbox);
        mMusicCheckbox.setChecked(true); //mặc định là true
        btn_ok = findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainScreen();
            }
        });

    }

    private void goToMainScreen() {

        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(intent);

    }

    public void viewClickHandler(View view) {

        switch (view.getId()) {

            case R.id.music_checkbox:
                        switchMusicEnableCheckbox();
                        break;
        }

    }

    private void switchMusicEnableCheckbox() {
        //switch checkbox
        isMusicOn = !isMusicOn;
        if (isMusicOn) {
            SettingPreferences.setMusicEnableDisable(mContext, true);
            AppController.playMusic(); //bật
        } else {
            SettingPreferences.setMusicEnableDisable(mContext, false);
            AppController.stopSound(); //tắt
        }

        populateMusicEnableContents();
    }

    protected void populateMusicEnableContents() {
        //bật tắt âm thanh
        if (SettingPreferences.getMusicEnableDisable(mContext)) {
            AppController.playMusic(); //chạy ngạc
            mMusicCheckbox.setChecked(true);
        } else {
            AppController.stopSound(); //ngừng nhạc
            mMusicCheckbox.setChecked(false);
        }
        isMusicOn = SettingPreferences.getMusicEnableDisable(mContext);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in SettingActivity");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BUGBUG","onDestroy() in SettingActivity");
        finish();
    }
}