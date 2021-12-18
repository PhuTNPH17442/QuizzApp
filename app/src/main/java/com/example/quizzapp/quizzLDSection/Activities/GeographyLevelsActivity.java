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

public class GeographyLevelsActivity extends AppCompatActivity implements View.OnClickListener {

    /*
     *   Khong the su dung 1 LevelActivity cho toan bo the loai vi se phat sinh loi
     *   1 Activity chi co the su dung cho 1 the loai
     */

    Button btnLevel1, btnLevel2, btnLevel3;

    String CategoryValue = "";

    int GL1, GL2, GL3; //Geography Levels

//    TextView tvLevel1, tvLevel2, tvLevel3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geography_levels);

        btnLevel1 = findViewById(R.id.btnLevel1);
        btnLevel2 = findViewById(R.id.btnLevel2);
        btnLevel3 = findViewById(R.id.btnLevel3);

//        btnLevel1.setOnClickListener(this);
//        btnLevel2.setOnClickListener(this);
//        btnLevel3.setOnClickListener(this);

//        tvLevel1 = findViewById(R.id.tvLevel1);
//        tvLevel2 = findViewById(R.id.tvLevel2);
//        tvLevel3 = findViewById(R.id.tvLevel3);


        lockAndUnlockLevels();


        Intent intentCategory = getIntent();
        CategoryValue = intentCategory.getStringExtra("Category");


    }

    @Override
    public void onClick(View v) {

        if (CategoryValue.equals("Geography")) {

            switch (v.getId()) {

                case R.id.btnLevel1:

                    Intent intentGeoLevel1 = new Intent(GeographyLevelsActivity.this, QuizActivity.class);
                    intentGeoLevel1.putExtra("Category", Questions.CATEGORY_GEOGRAPHY);
                    intentGeoLevel1.putExtra("Level", Questions.LEVEL1);
                    startActivity(intentGeoLevel1);
                    break;

                case R.id.btnLevel2:

                    Intent intentGeoLevel2 = new Intent(GeographyLevelsActivity.this, QuizActivity.class);
                    intentGeoLevel2.putExtra("Category", Questions.CATEGORY_GEOGRAPHY);
                    intentGeoLevel2.putExtra("Level", Questions.LEVEL2);
                    startActivity(intentGeoLevel2);
                    break;

                case R.id.btnLevel3:

                    Intent intentGeoLevel3 = new Intent(GeographyLevelsActivity.this, QuizActivity.class);
                    intentGeoLevel3.putExtra("Category", Questions.CATEGORY_GEOGRAPHY);
                    intentGeoLevel3.putExtra("Level", Questions.LEVEL3);
                    startActivity(intentGeoLevel3);
                    break;
            }
        }

    }

//    public void LoadData(View view) {
//
//        tvLevel1.setText(String.valueOf(GL1));
//        tvLevel2.setText(String.valueOf(GL2));
//        tvLevel3.setText(String.valueOf(GL3));
//
//    }

    private void lockAndUnlockLevels() {

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE,
                        Context.MODE_PRIVATE);

        GL1 = sharedPreferences.getInt(Constant.KEY_GEO_LEVEL_1,0);
        GL2 = sharedPreferences.getInt(Constant.KEY_GEO_LEVEL_2,0);
        GL3 = sharedPreferences.getInt(Constant.KEY_GEO_LEVEL_3,0);

        if (GL1 == 1) {

            btnLevel1.setClickable(true);
            btnLevel1.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
            btnLevel1.setOnClickListener(this);
        } else if (GL1 == 0) {
            btnLevel1.setClickable(false);
            btnLevel1.setBackground(ContextCompat.getDrawable(this, R.drawable.confirm_button_background));
        }

        if (GL2 == 1) {

            btnLevel2.setClickable(true);
            btnLevel2.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
            btnLevel2.setOnClickListener(this);

        } else if (GL2 == 0) {

            btnLevel2.setClickable(false);
            btnLevel2.setBackground(ContextCompat.getDrawable(this, R.drawable.confirm_button_background));

        }

        if (GL3 == 1) {

            btnLevel3.setClickable(true);
            btnLevel3.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
            btnLevel3.setOnClickListener(this);

        } else if (GL3 == 0) {

            btnLevel3.setClickable(false);
            btnLevel3.setBackground(ContextCompat.getDrawable(this, R.drawable.confirm_button_background));

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in GeographyLevelsActivity");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BUGBUG","onDestroy() in GeographyLevelsActivity");
        finish();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(GeographyLevelsActivity.this, CategoryActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }
}