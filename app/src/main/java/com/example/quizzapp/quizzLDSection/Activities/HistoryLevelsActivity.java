package com.example.quizzapp.quizzLDSection.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.quizzapp.R;
import com.example.quizzapp.quizzLDSection.constants.Constant;
import com.example.quizzapp.quizzLDSection.model.Questions;

public class HistoryLevelsActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLevel1, btnLevel2, btnLevel3;

    String CategoryValue = "";

    int HL1, HL2, HL3; //History level

    TextView tvLevel1, tvLevel2, tvLevel3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_levels);

        btnLevel1 = findViewById(R.id.btnLevel1);
        btnLevel2 = findViewById(R.id.btnLevel2);
        btnLevel3 = findViewById(R.id.btnLevel3);

//        btnLevel1.setOnClickListener(this);
//        btnLevel2.setOnClickListener(this);
//        btnLevel3.setOnClickListener(this);

        tvLevel1 = findViewById(R.id.tvLevel1);
        tvLevel2 = findViewById(R.id.tvLevel2);
        tvLevel3 = findViewById(R.id.tvLevel3);


        lockAndUnlockLevels();


        Intent intentCategory = getIntent();
        CategoryValue = intentCategory.getStringExtra("Category");


    }

    @Override
    public void onClick(View v) {

        if (CategoryValue.equals("History")) {

            switch (v.getId()) {

                case R.id.btnLevel1:

                    Intent intentAllLevel1 = new Intent(HistoryLevelsActivity.this, QuizActivity.class);
                    intentAllLevel1.putExtra("Category", Questions.CATEGORY_HISTORY);
                    intentAllLevel1.putExtra("Level", Questions.LEVEL1);
                    startActivity(intentAllLevel1);
                    break;

                case R.id.btnLevel2:

                    Intent intentAllLevel2 = new Intent(HistoryLevelsActivity.this, QuizActivity.class);
                    intentAllLevel2.putExtra("Category", Questions.CATEGORY_HISTORY);
                    intentAllLevel2.putExtra("Level", Questions.LEVEL2);
                    startActivity(intentAllLevel2);
                    break;

                case R.id.btnLevel3:

                    Intent intentAllLevel3 = new Intent(HistoryLevelsActivity.this, QuizActivity.class);
                    intentAllLevel3.putExtra("Category", Questions.CATEGORY_HISTORY);
                    intentAllLevel3.putExtra("Level", Questions.LEVEL3);
                    startActivity(intentAllLevel3);
                    break;
            }
        }

    }

    public void LoadData(View view) {

        tvLevel1.setText(String.valueOf(HL1));
        tvLevel2.setText(String.valueOf(HL2));
        tvLevel3.setText(String.valueOf(HL3));

    }

    private void lockAndUnlockLevels() {

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE,
                        Context.MODE_PRIVATE);

        HL1 = sharedPreferences.getInt(Constant.KEY_HIS_LEVEL_1,0);
        HL2 = sharedPreferences.getInt(Constant.KEY_HIS_LEVEL_2,0);
        HL3 = sharedPreferences.getInt(Constant.KEY_HIS_LEVEL_3,0);

        if (HL1 == 1) {

            btnLevel1.setClickable(true);
            btnLevel1.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
            btnLevel1.setOnClickListener(this);
        } else if (HL1 == 0) {
            btnLevel1.setClickable(false);
            btnLevel1.setBackground(ContextCompat.getDrawable(this, R.drawable.confirm_button_background));
        }

        if (HL2 == 1) {

            btnLevel2.setClickable(true);
            btnLevel2.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
            btnLevel2.setOnClickListener(this);

        } else if (HL2 == 0) {

            btnLevel2.setClickable(false);
            btnLevel2.setBackground(ContextCompat.getDrawable(this, R.drawable.confirm_button_background));

        }

        if (HL3 == 1) {

            btnLevel3.setClickable(true);
            btnLevel3.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
            btnLevel3.setOnClickListener(this);
            
        } else if (HL3 == 0) {

            btnLevel3.setClickable(false);
            btnLevel3.setBackground(ContextCompat.getDrawable(this, R.drawable.confirm_button_background));

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in HistoryLevelsActivity");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BUGBUG","onDestroy() in HistoryLevelsActivity");
        finish();
    }
}