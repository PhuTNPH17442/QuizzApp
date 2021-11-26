package com.example.quizzapp.guessword;

import android.provider.BaseColumns;

public class Table {
    private Table() {

    }

    //dữ liệu bảng thể loại
    public static class TheLoaiTable implements BaseColumns {
        public static final String TABLE_NAME = "TheLoai";
        public static final String COLUMN_NAME = "name";
    }

    public static class QuestionTable implements BaseColumns {
        //tên bảng
        public static final String TABLE_NAME = "Question";
        //câu hỏi
        public static final String COLUMN_QUESTION = "question";
        //dữ liệu đáp án
        public static final String COLUMN_ANSWER = "answer";
        //id thể loại
        public static final String COLUMN_THELOAI_ID = "id_theloai";
    }
}
