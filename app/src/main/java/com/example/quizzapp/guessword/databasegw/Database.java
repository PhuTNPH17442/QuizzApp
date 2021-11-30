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
        //insert du lieu
        CreateQuestion();
        CacTheLoai();
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
        TheLoai theLoai3 = new TheLoai("Tiếng Anh");
        insertTheLoai(theLoai3);
        TheLoai theLoai4 = new TheLoai("Thể Thao");
        insertTheLoai(theLoai4);
        TheLoai theLoai5 = new TheLoai("Ẩm Thực");
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
        //sử
        Question ls1 = new Question("Trong thời kì kháng chiến chống Pháp, chiến khu 11 nằm ở đâu? àH ộiN ", "Hà Nội", "Lịch Sử");
        insertQuestion(ls1);
        Question ls2 = new Question("An Dương Vương đặt tên nước ta là? uÂ ạcL", "Âu Lạc", "Lịch Sử");
        insertQuestion(ls2);
        Question ls3 = new Question("Ngô Quyền đóng kinh đô ở đâu? ổC aoL", "Cổ Loa", "Lịch Sử");
        insertQuestion(ls3);
        Question ls4 = new Question(" Lý Công Uẩn rời đô từ đâu về Thăng Long? aoH ưL", "Hoa Lư", "Lịch Sử");
        insertQuestion(ls4);
        Question ls5 = new Question("Năm 1689, thành phố Hồ Chí Minh có tên gọi là…? hủP iaG nhịĐ ", "Phủ Gia Định", "Lịch Sử");
        insertQuestion(ls5);
        //văn
        Question vh1 = new Question("Nhà thơ … đã ví: “Thân em như quả mít trên cây”? ồH âunX ươHng", "Hồ Xuân Hương", "Văn Học");
        insertQuestion(vh1);
        Question vh2 = new Question("“Truyện Kiều” viết bằng loại chữ gì? ữhC ôNm", "Chữ Nôm", "Văn Học");
        insertQuestion(vh2);
        Question vh3 = new Question("Người phụ nữ trong ca dao xuất hiện dưới hình tượng loại cá này? áC ốngB", "Cá Bống", "Văn Học");
        insertQuestion(vh3);
        Question vh4 = new Question("Nguyễn Kim Thành là tên thật của nhà thơ nào? ốT ưuH", "Tố Hữu", "Văn Học");
        insertQuestion(vh4);
        Question vh5 = new Question("Nguyễn Tường Vinh là tên thật của nhà văn nào? ạchTh amL ", "Thạch Lam", "Văn Học");
        insertQuestion(vh5);
        //tiếng anh
        Question ta1 = new Question("Bali is an island and province of ? aisdoneIn", "Indonesia", "Tiếng Anh");
        insertQuestion(ta1);
        Question ta2 = new Question("Which planet is the nearest to the Earth? eVusn", "Venus", "Tiếng Anh");
        insertQuestion(ta2);
        Question ta3 = new Question("Which is the smallest country in the South East Asia? aingSpore", "Singapore", "Tiếng Anh");
        insertQuestion(ta3);
        Question ta4 = new Question("What is the national flower of Viet Nam? oustL", "Lotus", "Tiếng Anh");
        insertQuestion(ta4);
        Question ta5 = new Question("Which is the biggest sport event of the South East Asian Nations? SAE AMESG", "SEA GAMES", "Tiếng Anh");
        insertQuestion(ta5);
        //âm nhạc
        Question tt1 = new Question("Bàn thắng nhanh nhất trong lịch sử World Cup do cầu thủ nào ghi? ankHa kurSu ", "Hanka Sukur", "Thể Thao");
        insertQuestion(tt1);
        Question tt2 = new Question("Đội tuyển nào đã để thua 0-5 trước LiverPool tại Champions League? chesertanM niedtU", "Manchester United", "Thể Thao");
        insertQuestion(tt2);
        Question tt3 = new Question("Ai đã sở hữu nhiều quả bóng vàng nhất ? ioeloL essMi", "Lionel Messi", "Thể Thao");
        insertQuestion(tt3);
        Question tt4 = new Question("Đội tuyển đã thi đấu với tuyển Việt Nam tại U23 Châu Á 2018? bekisUztan", "Uzbekistan", "Thể Thao");
        insertQuestion(tt4);
        Question tt5 = new Question("World Cup lần đầu tiên được tổ chức ở đâu? ruguyaU", "Uruguay", "Thể Thao");
        insertQuestion(tt5);
        //ẩm thực
        Question at1 = new Question("Bánh Pía là đặc sản của tỉnh nào sau đây? ócS răTng", "Sóc Trăng", "Ẩm Thực");
        insertQuestion(at1);
        Question at2 = new Question("Bánh tráng nướng là món ăn nổi tiếng nhất ở địa phương nào? àĐ ạLt", "Đà Lạt", "Ẩm Thực");
        insertQuestion(at2);
        Question at3 = new Question("Bánh khọt là đặc sản của tỉnh? àTu ũngV", "Vũng Tàu", "Ẩm Thực");
        insertQuestion(at3);
        Question at4 = new Question("Cơm cháy là đặc sản nổi tiếng của vùng địa phương nào dưới đây? inNh ìnBh", "Ninh Bình", "Ẩm Thực");
        insertQuestion(at4);
        Question at5 = new Question("Bún sứa là món ăn đặc trưng của vùng nào? ahN angTr", "Nha Trang", "Ẩm Thực");
        insertQuestion(at5);
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
    public ArrayList<Question> getQuestion(int theLoaiID) {
        ArrayList<Question> questionArrayList = new ArrayList<>();
        db = getWritableDatabase();
        String selection = Table.QuestionTable.COLUMN_THELOAI_ID + "= ?";
        String[] selectionArgs = new String[]{String.valueOf(theLoaiID)};
        Cursor cursor = db.query(Table.QuestionTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getString(cursor.getColumnIndex(Table.QuestionTable._ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(Table.QuestionTable.COLUMN_QUESTION)));
                question.setAnswer(cursor.getString(cursor.getColumnIndex(Table.QuestionTable.COLUMN_ANSWER)));
                question.setTheLoaiID(cursor.getString(cursor.getColumnIndex(Table.QuestionTable.COLUMN_THELOAI_ID)));
                questionArrayList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionArrayList;
    }
}
