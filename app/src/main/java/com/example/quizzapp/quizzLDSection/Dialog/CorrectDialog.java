package com.example.quizzapp.quizzLDSection.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzapp.quizzLDSection.Activities.QuizActivity;
import com.example.quizzapp.R;

public class CorrectDialog {

    private Context mContext;

    private Dialog correctDialog;

    private QuizActivity mQuizActivity;

    public CorrectDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void correctDialog(int score, final QuizActivity quizActivity) {

        mQuizActivity = quizActivity;

        correctDialog = new Dialog(mContext);
        correctDialog.setContentView(R.layout.correct_dialog);

        Button btnCorrectDialog = (Button) correctDialog.findViewById(R.id.btn_correct_dialog);

        score(score);

        btnCorrectDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctDialog.dismiss();
                mQuizActivity.showQuestions(); //Hiển thị câu hỏi
            }
        });

        correctDialog.show();
        correctDialog.setCancelable(false);
        correctDialog.setCanceledOnTouchOutside(false);
    }

    private void score(int score) {
        //Hiển thị điểm
        TextView tvScore = (TextView) correctDialog.findViewById(R.id.tv_score);
        tvScore.setText("Điểm: " + String.valueOf(score));
    }
}
