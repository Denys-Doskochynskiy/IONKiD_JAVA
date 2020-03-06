package com.example.aba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper2 extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contact1Db";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_NICK = "name";
    public static final String KEY_PASS = "mail";
    public static final String KEY_ID = "_id";
    public static final String KEY_SEX = "sex";
    public static final String KEY_BLOUD = "bloud";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_DIAGNOSE = "diagnose";
    public static final String KEY_NAME1 = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_PATRONYMIC = "patronymic";
    public static final String KEY_DAY = "day";
    public static final String KEY_MOUNTH = "mounth";
    public static final String KEY_YEAR = "year";

    public DBHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID
                + " integer primary key,"+ KEY_DIAGNOSE + " text," + KEY_DAY + " text," + KEY_NAME1 + " text,"
                + KEY_SURNAME + " text," + KEY_PATRONYMIC + " text,"  + KEY_MOUNTH + " text,"+ KEY_YEAR + " text,"
                + KEY_SEX + " text,"   + KEY_BLOUD + " text," + KEY_WEIGHT + " text," + KEY_HEIGHT + " text," +  KEY_NICK + " text," + KEY_PASS + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + KEY_NICK +KEY_PASS);

        onCreate(db);

    }/*
    public void onUpgrade2(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + KEY_YEAR+KEY_MOUNTH+KEY_PATRONYMIC+KEY_SURNAME+KEY_DAY+KEY_HEIGHT+KEY_BLOUD+KEY_WEIGHT+KEY_SEX+KEY_NAME1);

        onCreate(db);

    }*/
}