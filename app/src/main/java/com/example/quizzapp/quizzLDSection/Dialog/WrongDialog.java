package com.example.quizzapp.quizzLDSection.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzapp.R;
import com.example.quizzapp.quizzLDSection.Activities.QuizActivity;

public class WrongDialog {

    private Context mContext;

    private Dialog wrongDialog;

    private QuizActivity mQuizActivity;

    public WrongDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void wrongDialog(String correctAnswer, final QuizActivity quizActivity) {

        mQuizActivity = quizActivity;

        wrongDialog = new Dialog(mContext);
        wrongDialog.setContentView(R.layout.wrong_dialog);
        Button btnWrongDialog = (Button) wrongDialog.findViewById(R.id.btn_wrong_dialog);
        TextView tvCorrectAnswer = (TextView) wrongDialog.findViewById(R.id.tv_correct_answer);
        //Hiển thị đáp án đúng
        tvCorrectAnswer.setText("Đáp án đúng: " + String.valueOf(correctAnswer));

        btnWrongDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.dismiss();
                mQuizActivity.showQuestions(); //Hiển thị câu hỏi
            }
        });

        wrongDialog.show();
        wrongDialog.setCancelable(false);
        wrongDialog.setCanceledOnTouchOutside(false);

    }
}
