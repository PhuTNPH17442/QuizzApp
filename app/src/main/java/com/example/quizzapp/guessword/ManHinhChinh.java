package com.example.quizzapp.guessword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.quizzapp.MenuGameActivity;
import com.example.quizzapp.R;
import com.example.quizzapp.doan_hinh.MainActivity_start_doan_hinh;

public class ManHinhChinh extends AppCompatActivity {
    Button btnBatdau, btnOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        btnBatdau = findViewById(R.id.btnBatdau);
        btnOut = findViewById(R.id.btnOut);
        btnBatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManHinhChinh.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManHinhChinh.this, MenuGameActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in ManHinhChinh");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BUGBUG","onDestroy() in ManHinhChinh");
        finish();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ManHinhChinh.this, MenuGameActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

}