package com.example.quizzapp.guessword.databasegw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quizzapp.guessword.Table;
import com.example.quizzapp.guessword.model.Question;
import com.example.quizzapp.guessword.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    //tên database
    private static final String DATABASE_NAME = "Question.db";

    private static final int VERSION = 1;
    private SQLiteDatabase db;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        //Bảng thể loại
        final String TheLoaiTable = " CREATE TABLE " + Table.TheLoaiTable.TABLE_NAME + "("
                + Table.TheLoaiTable._ID + "INTEGER PRIMARY KEY," + Table.TheLoaiTable.COLUMN_NAME + "TEXT" + ")";
        //Bảng câu hỏi
        final String QuestionTable = " CREATE TABLE "
                + Table.QuestionTable.TABLE_NAME + "(" + Table.QuestionTable._ID + "INTEGER PRIMARY KEY,"
                + Table.QuestionTable.COLUMN_QUESTION + "TEXT,"
                + Table.QuestionTable.COLUMN_ANSWER + "TEXT,"
                + Table.QuestionTable.COLUMN_THELOAI_ID + "TEXT" + ")";
        //tạo bảng
        db.execSQL(TheLoaiTable);
        db.execSQL(QuestionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table.TheLoaiTable.COLUMN_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Table.QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void insertTheLoai(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put(Table.TheLoaiTable.COLUMN_NAME, theLoai.getName());
        db.insert(Table.TheLoaiTable.TABLE_NAME, null, values);
    }

    //Thêm Thể loại
    public void CacTheLoai() {
        TheLoai theLoai = new TheLoai("Văn Học");
        insertTheLoai(theLoai);
        TheLoai theLoai1 = new TheLoai("Lịch Sử");
        insertTheLoai(theLoai1);
        TheLoai theLoai2 = new TheLoai("Văn Hóa");
        insertTheLoai(theLoai2);
        TheLoai theLoai3 = new TheLoai("Tiếng Anh");
        insertTheLoai(theLoai3);
        TheLoai theLoai4 = new TheLoai("Âm Nhạc");
        insertTheLoai(theLoai4);
        TheLoai theLoai5 = new TheLoai("Ăn Uống");
        insertTheLoai(theLoai5);
    }

    //thêm câu hỏi và đáp án
    private void insertQuestion(Question question) {
        ContentValues values = new ContentValues();
        values.put(Table.QuestionTable.COLUMN_QUESTION, question.getQuestion());
        values.put(Table.QuestionTable.COLUMN_ANSWER, question.getAnswer());
        values.put(Table.QuestionTable.COLUMN_THELOAI_ID, question.getTheLoaiID());
        db.insert(Table.QuestionTable.TABLE_NAME, null, values);
    }

    public void CreateQuestion() {
        Question question1 = new Question("Nhân vật nào tự xưng là Mỹ hầu vương ? nTô ộgN ônghK ", "Tôn Ngộ Không", "Văn Học");
        insertQuestion(question1);
    }

    //lấy về dữ liệu của chủ đề
    public List<TheLoai> getAllData() {
        List<TheLoai> theLoais = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT*FROM " + Table.TheLoaiTable.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                TheLoai theLoai = new TheLoai();
                theLoai.setId(cursor.getString(cursor.getColumnIndex(Table.TheLoaiTable._ID)));
                theLoai.setName(cursor.getString(cursor.getColumnIndex(Table.TheLoaiTable.COLUMN_NAME)));
                theLoais.add(theLoai);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return theLoais;
    }
    //lấy về dữ liệu câu hỏi và đáp án theo chủ đề

}
