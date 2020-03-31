package com.example.aba;


import android.app.Activity;
import android.content.ContentValues;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TestComment extends Activity implements OnClickListener {


    final String LOG_TAG = "myLogs";

    Button btnAdd, btnMenu;
    EditText editComment;

    Reg.DBHelper dbHelper;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_comment);

        btnAdd = (Button) findViewById(R.id.btnReadAndAdd);
        btnAdd.setOnClickListener(this);
        btnMenu = (Button) findViewById(R.id.btnBackMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        editComment = (EditText) findViewById(R.id.editComment);


        // создаем объект для создания и управления версиями БД
        dbHelper = new Reg.DBHelper(this);
    }

    public void openMenu() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        String comment = editComment.getText().toString();
        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        switch (v.getId()) {
            case R.id.btnReadAndAdd:
                Log.d(LOG_TAG, "--- Insert in mytableIONKidV3OnliComment: ---");
                // подготовим данные для вставки в виде пар: наименование столбца - значение
                cv.put("comment", comment);

                // вставляем запись и получаем ее ID
                long rowID = db.insert("mytableIONKidV3", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                Log.d(LOG_TAG, "--- Rows in mytableIONKidV3OnliComment: ---");
                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db.query("mytableIONKidV3", null, null, null, null, null, null);

                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                if (c.moveToFirst()) {

                    // определяем номера столбцов по имени в выборке
                    int commentColIndex = c.getColumnIndex("comment");
                    do {
                        // получаем значения по номерам столбцов и пишем все в лог
                        Log.d(LOG_TAG,
                                        ", Comment = " + c.getString(commentColIndex)

                        ) ;
                        // переход на следующую строку
                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while (c.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                c.close();

        }
        // закрываем подключение к БД
        dbHelper.close();
    }



}
