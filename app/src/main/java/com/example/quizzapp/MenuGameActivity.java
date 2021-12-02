package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizzapp.doan_hinh.MainActivity_doan_hinh;
import com.example.quizzapp.doan_hinh.MainActivity_start_doan_hinh;
import com.example.quizzapp.guessword.MainActivity2;
import com.example.quizzapp.guessword.ManHinhChinh;
import com.example.quizzapp.quizzLDSection.Activities.CategoryActivity;

public class MenuGameActivity extends AppCompatActivity {

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_game);

        Button btnDoVui = findViewById(R.id.btn_do_vui);
        Button btnDoanHinh = findViewById(R.id.btn_doan_hinh);
        Button btnDoanChu = findViewById(R.id.btn_doan_chu);

        btnDoVui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuGameActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        btnDoanHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuGameActivity.this, MainActivity_start_doan_hinh.class);
                startActivity(intent);

            }
        });
        btnDoanChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuGameActivity.this, ManHinhChinh.class);
                startActivity(intent);
            }
        });
    }
//    @Override
//    public void onBackPressed() {
//        if (backPressedTime + 2000 > System.currentTimeMillis()) {
//
//            Intent intent = new Intent(MenuGameActivity.this, MainActivity.class);
//            startActivity(intent);
//
//        } else {
//
//            Toast.makeText(this, "Nhấn lại nút Back để về màn hình chính", Toast.LENGTH_SHORT).show();
//        }
//        backPressedTime = System.currentTimeMillis();
//    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MenuGameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in MenuGameActivity");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BUGBUG","onDestroy() in MenuGameActivity");
        finish();
    }
}