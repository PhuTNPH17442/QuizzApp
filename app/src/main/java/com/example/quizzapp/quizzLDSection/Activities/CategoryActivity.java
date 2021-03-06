package com.example.quizzapp.quizzLDSection.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzapp.MenuGameActivity;
import com.example.quizzapp.R;
import com.example.quizzapp.quizzLDSection.constants.CategoryConstants;
import com.example.quizzapp.quizzLDSection.constants.Constant;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAll, btnHistory, btnMath, btnEnglish, btnGeography, btnScience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        btnAll = findViewById(R.id.btn_All);
        btnHistory = findViewById(R.id.btn_History);
//        btnMath = findViewById(R.id.btn_Math);
//        btnEnglish = findViewById(R.id.btn_English);
        btnGeography = findViewById(R.id.btn_Geography);
        btnScience = findViewById(R.id.btn_Science);

        btnAll.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
//        btnMath.setOnClickListener(this);
//        btnEnglish.setOnClickListener(this);
        btnGeography.setOnClickListener(this);
        btnScience.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_All:

                createLevelsForAll();
                Intent intentAll = new Intent(CategoryActivity.this, AllLevelsActivity.class);
                intentAll.putExtra("Category", CategoryConstants.ALL); //truyền dữ liệu Category - all đến AllLevelsActivity
                startActivity(intentAll);
                break;
            case R.id.btn_History:

                createLevelsForHistory();
                Intent intentHistory = new Intent(CategoryActivity.this, HistoryLevelsActivity.class);
                intentHistory.putExtra("Category", CategoryConstants.HISTORY); //truyền dữ liệu Category - history đến HistoryLevelsActivity
                startActivity(intentHistory);
                break;
//            case R.id.btn_Math:
//
//                Intent intentMath = new Intent(CategoryActivity.this, AllLevelsActivity.class);
//                intentMath.putExtra("Category", CategoryConstants.MATHS);
//                startActivity(intentMath);
//                break;
//            case R.id.btn_English:
//
//                Intent intentEnglish = new Intent(CategoryActivity.this, AllLevelsActivity.class);
//                intentEnglish.putExtra("Category", CategoryConstants.ENGLISH);
//                startActivity(intentEnglish);
//                break;
            case R.id.btn_Geography:

                createLevelsForGeography();
                Intent intentGeography = new Intent(CategoryActivity.this, GeographyLevelsActivity.class);
                intentGeography.putExtra("Category", CategoryConstants.GEOGRAPHY); //truyền dữ liệu Category - geography đến GeographyLevelsActivity
                startActivity(intentGeography);
                break;
            case R.id.btn_Science:

                createLevelsForScience();
                Intent intentScience = new Intent(CategoryActivity.this, ScienceLevelsActivity.class);
                intentScience.putExtra("Category", CategoryConstants.SCIENCE); //truyền dữ liệu Category - science đến GeographyLevelsActivity
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

    // 1 = unlocked  &  0 = locked
    private void createLevelsForGeography() {
        //Su dung SharedPreferences de luu tru thong tin
        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.KEY_GEO_LEVEL_1, 1); // mặc định Level 1 đã đc unlocked
        editor.putString(Constant.KEY_CAT_GEO_LEVEL_1, "Unlock");
        editor.apply();

        if (sharedPreferences.getString(Constant.KEY_CAT_GEO_LEVEL_1, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_GEO_LEVEL_1, 1);
            editor.putInt(Constant.KEY_GEO_LEVEL_2, 0);
            editor.putInt(Constant.KEY_GEO_LEVEL_3, 0);

        }else if (sharedPreferences.getString(Constant.KEY_CAT_GEO_LEVEL_2, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_GEO_LEVEL_1, 1);
            editor.putInt(Constant.KEY_GEO_LEVEL_2, 1);
            editor.putInt(Constant.KEY_GEO_LEVEL_3, 0);

        }else if (sharedPreferences.getString(Constant.KEY_CAT_GEO_LEVEL_3, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_GEO_LEVEL_1, 1);
            editor.putInt(Constant.KEY_GEO_LEVEL_2, 1);
            editor.putInt(Constant.KEY_GEO_LEVEL_3, 1);

        }
    }

    // 1 = unlocked  &  0 = locked
    private void createLevelsForScience() {
        //Su dung SharedPreferences de luu tru thong tin
        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.KEY_SCI_LEVEL_1, 1); // mặc định Level 1 đã đc unlocked
        editor.putString(Constant.KEY_CAT_SCI_LEVEL_1, "Unlock");
        editor.apply();

        if (sharedPreferences.getString(Constant.KEY_CAT_SCI_LEVEL_1, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_SCI_LEVEL_1, 1);
            editor.putInt(Constant.KEY_SCI_LEVEL_2, 0);
            editor.putInt(Constant.KEY_SCI_LEVEL_3, 0);

        }else if (sharedPreferences.getString(Constant.KEY_CAT_SCI_LEVEL_2, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_SCI_LEVEL_1, 1);
            editor.putInt(Constant.KEY_SCI_LEVEL_2, 1);
            editor.putInt(Constant.KEY_SCI_LEVEL_3, 0);

        }else if (sharedPreferences.getString(Constant.KEY_CAT_SCI_LEVEL_3, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_SCI_LEVEL_1, 1);
            editor.putInt(Constant.KEY_SCI_LEVEL_2, 1);
            editor.putInt(Constant.KEY_SCI_LEVEL_3, 1);

        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CategoryActivity.this, MenuGameActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in CategoryActivity");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BUGBUG","onDestroy() in CategoryActivity");
        finish();
    }
}