package com.example.quizzapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizzapp.quizzLDSection.Activities.CategoryActivity;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String test = "Đây là dữ liệu mới(kientvph17296)";
//        Toast.makeText(this, "Đào Mạnh Toàn", Toast.LENGTH_SHORT).show();
//        //Le Dinh Duong commit
//        String name = "Lê Đình Dương - PH17450";
        Button btnPlay = findViewById(R.id.btn_play);
        Button btnSetting = findViewById(R.id.btn_settings);

        context = getApplicationContext();
        AppController.currentActivity = this;
        if (SettingPreferences.getMusicEnableDisable(context)) {
            try {

                AppController.playMusic();

            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MenuGameActivity.class);
                startActivity(intent);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            new AlertDialog.Builder(this)
                    .setTitle("Bạn có muốn thoát?")
                    .setNegativeButton("Không", null)
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            setResult(RESULT_OK, new Intent().putExtra("Exit", true));
                            AppController.stopSound();
//                            android.os.Process.killProcess(android.os.Process.myPid());
//                            System.exit(1);
                            finish();
                        }
                    }).create().show();

        } else {

            Toast.makeText(this, "Nhấn lại nút Back để thoát", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in PlayActivity");
        finish();

    }
}