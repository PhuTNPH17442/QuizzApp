package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizzapp.constants.CategoryConstants;
import com.example.quizzapp.constants.Constant;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAll, btnHistory, btnMath, btnEnglish, btnGeography, btnScience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        btnAll = findViewById(R.id.btn_All);
        btnHistory = findViewById(R.id.btn_History);
        btnMath = findViewById(R.id.btn_Math);
        btnEnglish = findViewById(R.id.btn_English);
        btnGeography = findViewById(R.id.btn_Geography);
        btnScience = findViewById(R.id.btn_Science);

        btnAll.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnMath.setOnClickListener(this);
        btnEnglish.setOnClickListener(this);
        btnGeography.setOnClickListener(this);
        btnScience.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_All:

                createLevelsForAll();
                Intent intentAll = new Intent(CategoryActivity.this, AllLevelsActivity.class);
                intentAll.putExtra("Category", CategoryConstants.ALL);
                startActivity(intentAll);
                break;
            case R.id.btn_History:

//                createLevelsForHistory();
//                Intent intentHistory = new Intent(CategoryActivity.this, HistoryLevelsActivity.class);
//                intentHistory.putExtra("Category", CategoryConstants.HISTORY);
//                startActivity(intentHistory);
                break;
            case R.id.btn_Math:

                Intent intentMath = new Intent(CategoryActivity.this, AllLevelsActivity.class);
                intentMath.putExtra("Category", CategoryConstants.MATHS);
                startActivity(intentMath);
                break;
            case R.id.btn_English:

                Intent intentEnglish = new Intent(CategoryActivity.this, AllLevelsActivity.class);
                intentEnglish.putExtra("Category", CategoryConstants.ENGLISH);
                startActivity(intentEnglish);
                break;
            case R.id.btn_Geography:

                Intent intentGeography = new Intent(CategoryActivity.this, AllLevelsActivity.class);
                intentGeography.putExtra("Category", CategoryConstants.GEOGRAPHY);
                startActivity(intentGeography);
                break;
            case R.id.btn_Science:

                Intent intentScience = new Intent(CategoryActivity.this, AllLevelsActivity.class);
                intentScience.putExtra("Category", CategoryConstants.SCIENCE);
                startActivity(intentScience);
                break;
        }
    }


    // 1 = unlocked  &  0 = locked
    private void createLevelsForAll() {

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.KEY_ALL_LEVEL_1, 1); // mặc định Level 1 đã đc unlocked
        editor.putString(Constant.KEY_CAT_ALL_LEVEL_1, "Unlock");
        editor.apply();

        if (sharedPreferences.getString(Constant.KEY_CAT_ALL_LEVEL_1, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_ALL_LEVEL_1, 1);
            editor.putInt(Constant.KEY_ALL_LEVEL_2, 0);
            editor.putInt(Constant.KEY_ALL_LEVEL_3, 0);

        }else if (sharedPreferences.getString(Constant.KEY_CAT_ALL_LEVEL_2, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_ALL_LEVEL_1, 1);
            editor.putInt(Constant.KEY_ALL_LEVEL_2, 1);
            editor.putInt(Constant.KEY_ALL_LEVEL_3, 0);

        }else if (sharedPreferences.getString(Constant.KEY_CAT_ALL_LEVEL_3, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_ALL_LEVEL_1, 1);
            editor.putInt(Constant.KEY_ALL_LEVEL_2, 1);
            editor.putInt(Constant.KEY_ALL_LEVEL_3, 1);

        }
    }


    // 1 = unlocked  &  0 = locked
    private void createLevelsForHistory() {
        //Su dung SharedPreferences de luu tru thong tin
        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.KEY_HIS_LEVEL_1, 1); // mặc định Level 1 đã đc unlocked
        editor.putString(Constant.KEY_CAT_HIS_LEVEL_1, "Unlock");
        editor.apply();

        if (sharedPreferences.getString(Constant.KEY_CAT_HIS_LEVEL_1, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_HIS_LEVEL_1, 1);
            editor.putInt(Constant.KEY_HIS_LEVEL_2, 0);
            editor.putInt(Constant.KEY_HIS_LEVEL_3, 0);

        }else if (sharedPreferences.getString(Constant.KEY_CAT_HIS_LEVEL_2, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_HIS_LEVEL_1, 1);
            editor.putInt(Constant.KEY_HIS_LEVEL_2, 1);
            editor.putInt(Constant.KEY_HIS_LEVEL_3, 0);

        }else if (sharedPreferences.getString(Constant.KEY_CAT_HIS_LEVEL_3, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_HIS_LEVEL_1, 1);
            editor.putInt(Constant.KEY_HIS_LEVEL_2, 1);
            editor.putInt(Constant.KEY_HIS_LEVEL_3, 1);

        }
    }
}