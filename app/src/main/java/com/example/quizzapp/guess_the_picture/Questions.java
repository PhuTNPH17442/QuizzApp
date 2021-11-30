package com.example.quizzapp.guess_the_picture;

public class Questions {
    private String mQuestions[] = {
            "Đoán Logo ?",
            "Bạn có thể đoán Logo",
            "Đoán Logo ?",
            "Bạn có thể đoán Logo",
            "Đoán Logo ?",
            "Bạn có thể đoán Logo",
            "Đoán Logo ?",
            "Bạn có thể đoán Logo",
            "Đoán Logo ?",
            "Bạn có thể đoán Logo",
    };
    private String mChoice[] [] = {
            {"Smart Phone", "Android", "Mobile"},
            {"Apple", "Software", "App Store"},
            {"Opera", "Chome", "CocCoc"},
            {"DeLL", "Acer", "Msi"},
            {"Zalo", "FaceTime", "FaceBook"},
            {"Dell", "Hp", "Lenovo"},
            {"NoKiA", "Huawei", "SamSung"},
            {"Java", "Android", "HarmonyOS"},
            {"Telegram", "Messeger", "Mocha"},
            {"Mi", "Google", "RedMi"},
    };
    private String mImages[] = {
            "q1", //android
            "q2", //apple
            "q3",//Chome
            "q4",//dell
            "q5",//facebook
            "q6",//hp
            "q7",//huawei
            "q8",//java
            "q9",//mes
            "q10",//mi
    };
    private String mQuestionType[] = {
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
    };
    private String mCoorect[] = {
            "Android",
            "Apple",
            "Chome",
            "DeLL",
            "FaceBook",
            "Hp",
            "Huawei",
            "Java",
            "Messeger",
            "Mi",
    };

    public String getQuestions(int q) {
        String questions = mQuestions[q];
        return questions;
    }

    public String[] getChoice(int q) {
        String[] choice = mChoice[q];
        return choice;
    }

    public String getImages(int q) {
        String img = mImages[q];
        return img;
    }

    public String getType(int q) {
        String type = mQuestionType[q];
        return type;
    }
    public int getLength(){
        return mQuestions.length;
    }

    public String getCoorect(int q) {
        String coorect = mCoorect[q];
        return coorect;
    }
}
