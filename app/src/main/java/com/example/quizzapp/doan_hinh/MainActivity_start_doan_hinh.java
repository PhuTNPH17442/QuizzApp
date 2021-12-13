package com.example.quizzapp.doan_hinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.quizzapp.MenuGameActivity;
import com.example.quizzapp.R;

public class MainActivity_start_doan_hinh extends AppCompatActivity {
    Button btnStart, btnQuaylai;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start_doan_hinh);
        btnStart = findViewById(R.id.btnstart_doan_hinh);
        btnQuaylai = findViewById(R.id.btn_quay_lai);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity_start_doan_hinh.this,MainActivity_doan_hinh.class);
                startActivity(intent);
            }
        });
        btnQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity_start_doan_hinh.this, MenuGameActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in MainActivity_start_doan_hinh");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BUGBUG","onDestroy() in MainActivity_start_doan_hinh");
        finish();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MainActivity_start_doan_hinh.this, MenuGameActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

}