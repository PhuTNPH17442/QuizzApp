package com.example.quizzapp.quizzLDSection.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzapp.R;
import com.example.quizzapp.quizzLDSection.constants.Constant;
import com.example.quizzapp.quizzLDSection.model.Questions;

public class ScienceLevelsActivity extends AppCompatActivity implements View.OnClickListener{
    /*
     *   Khong the su dung 1 LevelActivity cho toan bo the loai vi se phat sinh loi
     *   1 Activity chi co the su dung cho 1 the loai
     */

    Button btnLevel1, btnLevel2, btnLevel3;

    String CategoryValue = "";

    int SL1, SL2, SL3; //

    TextView tvLevel1, tvLevel2, tvLevel3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_levels);

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

        if (CategoryValue.equals("Science")) {

            switch (v.getId()) {

                case R.id.btnLevel1:

                    Intent intentSciLevel1 = new Intent(ScienceLevelsActivity.this, QuizActivity.class);
                    intentSciLevel1.putExtra("Category", Questions.CATEGORY_SCIENCE);
                    intentSciLevel1.putExtra("Level", Questions.LEVEL1);
                    startActivity(intentSciLevel1);
                    break;

                case R.id.btnLevel2:

                    Intent intentSciLevel2 = new Intent(ScienceLevelsActivity.this, QuizActivity.class);
                    intentSciLevel2.putExtra("Category", Questions.CATEGORY_SCIENCE);
                    intentSciLevel2.putExtra("Level", Questions.LEVEL2);
                    startActivity(intentSciLevel2);
                    break;

                case R.id.btnLevel3:

                    Intent intentSciLevel3 = new Intent(ScienceLevelsActivity.this, QuizActivity.class);
                    intentSciLevel3.putExtra("Category", Questions.CATEGORY_SCIENCE);
                    intentSciLevel3.putExtra("Level", Questions.LEVEL3);
                    startActivity(intentSciLevel3);
                    break;
            }
        }

    }

    public void LoadData(View view) {

        tvLevel1.setText(String.valueOf(SL1));
        tvLevel2.setText(String.valueOf(SL2));
        tvLevel3.setText(String.valueOf(SL3));

    }

    private void lockAndUnlockLevels() {

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE,
                        Context.MODE_PRIVATE);

        SL1 = sharedPreferences.getInt(Constant.KEY_SCI_LEVEL_1,0);
        SL2 = sharedPreferences.getInt(Constant.KEY_SCI_LEVEL_2,0);
        SL3 = sharedPreferences.getInt(Constant.KEY_SCI_LEVEL_3,0);

        if (SL1 == 1) {

            btnLevel1.setClickable(true);
            btnLevel1.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
            btnLevel1.setOnClickListener(this);
        } else if (SL1 == 0) {
            btnLevel1.setClickable(false);
            btnLevel1.setBackground(ContextCompat.getDrawable(this, R.drawable.confirm_button_background));
        }

        if (SL2 == 1) {

            btnLevel2.setClickable(true);
            btnLevel2.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
            btnLevel2.setOnClickListener(this);

        } else if (SL2 == 0) {

            btnLevel2.setClickable(false);
            btnLevel2.setBackground(ContextCompat.getDrawable(this, R.drawable.confirm_button_background));

        }

        if (SL3 == 1) {

            btnLevel3.setClickable(true);
            btnLevel3.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
            btnLevel3.setOnClickListener(this);

        } else if (SL3 == 0) {

            btnLevel3.setClickable(false);
            btnLevel3.setBackground(ContextCompat.getDrawable(this, R.drawable.confirm_button_background));

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in AllLevelsActivity");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BUGBUG","onDestroy() in AllLevelsActivity");
        finish();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ScienceLevelsActivity.this, CategoryActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

}