package com.example.quizzapp.quizzLDSection.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.quizzapp.R;
import com.example.quizzapp.quizzLDSection.Activities.QuizActivity;

public class TimerDialog {

    private Context mContext;
    private Dialog TimerDialog;

    private QuizActivity mQuizActivity;

    public TimerDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void timerDialog(final QuizActivity quizActivity) {

        mQuizActivity = quizActivity;

        TimerDialog = new Dialog(mContext);
        TimerDialog.setContentView(R.layout.timer_dialog);

        final Button btnTimer = (Button) TimerDialog.findViewById(R.id.btn_timer);



        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimerDialog.dismiss();
                mQuizActivity.showQuestions();

                //Kết thúc trò chơi
//                Intent intent = new Intent(mContext, PlayActivity.class);
//                mContext.startActivity(intent);

            }
        });

        TimerDialog.show();
        TimerDialog.setCancelable(false);
        TimerDialog.setCanceledOnTouchOutside(false);

    }

}
