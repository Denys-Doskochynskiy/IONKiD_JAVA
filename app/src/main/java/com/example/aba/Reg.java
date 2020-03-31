
package com.example.aba;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Reg extends Activity implements OnClickListener {

    final String LOG_TAG = "myLogs";

    Button btnAdd, btnRead, btnClear, next;
    EditText etName, etEmail, eNumberPhone,etFirstName,etSecondName,etSurname,etConfirmPassword;

    DBHelper dbHelper;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
/* кнопки для відладки
        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);*/
        btnClear = (Button) findViewById(R.id.btnClean);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etEmail);
        etEmail = (EditText) findViewById(R.id.etPassword);
        etSurname = (EditText) findViewById(R.id.surname);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etSecondName = (EditText) findViewById(R.id.lastName);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        eNumberPhone = (EditText) findViewById(R.id.etNumberOfPhone);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskLogin();
            }
        });
        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);
    }

    public void openTaskLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String surname = etSurname.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        String secondName = etSecondName.getText().toString();
        String firsName = etFirstName.getText().toString();
       String numberPhone = eNumberPhone.getText().toString();

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        switch (v.getId()) {
            case R.id.btnAdd:
                Log.d(LOG_TAG, "--- Insert in mytableIONKidV1: ---");
                // подготовим данные для вставки в виде пар: наименование столбца - значение

              cv.put("name", name);
                cv.put("email", email);
                cv.put("numberPhone", numberPhone);
                cv.put("surname", surname);
                cv.put("secondName", secondName);
                cv.put("firstName", firsName);
                cv.put("confirmPassword", confirmPassword);

                // вставляем запись и получаем ее ID
                long rowID = db.insert("mytableIONKidV1", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                Log.d(LOG_TAG, "--- Rows in mytableIONKidV1: ---");
                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db.query("mytableIONKidV1", null, null, null, null, null, null);

                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                if (c.moveToFirst()) {

                    // определяем номера столбцов по имени в выборке
                    int idColIndex = c.getColumnIndex("id");
                  int nameColIndex = c.getColumnIndex("name");
                    int emailColIndex = c.getColumnIndex("email");
                    int numberPhoneColIndex = c.getColumnIndex("numberPhone");
                    int confirmPasswordColIndex = c.getColumnIndex("confirmPassword");
                    int firstNameColIndex = c.getColumnIndex("firstName");
                    int secondNameColIndex = c.getColumnIndex("secondName");
                    int surnameColIndex = c.getColumnIndex("surname");
                    do {
                        // получаем значения по номерам столбцов и пишем все в лог
                        Log.d(LOG_TAG,
                                "ID = " + c.getInt(idColIndex)+"\n" +
                                       ", Email = " + c.getString(nameColIndex) +
                                        ", Password = " + c.getString(emailColIndex)+"\n"+
                                        ", ConfirmPassword = " + c.getString(confirmPasswordColIndex)+
                                        ", FirstName = " + c.getString(firstNameColIndex)+"\n"+
                                ", LastName = " + c.getString(secondNameColIndex)+
                                ", Surname = " + c.getString(surnameColIndex)+"\n"+

                                       ", numberPhone = " + c.getString(numberPhoneColIndex)
                        ) ;
                        // переход на следующую строку
                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while (c.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                c.close();
                break;case R.id.btnClean:
                //if (id.equalsIgnoreCase("")) {
 //                   break;
   //             }
                Log.d(LOG_TAG, "--- Delete from mytable: ---");
                // удаляем по id
                int delCount = db.delete("mytableIONKidV1", "id = " + 25, null);
                Log.d(LOG_TAG, "deleted rows count = " + delCount);
                break;
            /*Кнопка для відладки
            case R.id.btnRead:
              Log.d(LOG_TAG, "--- Rows in mytable: ---");
                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db.query("mytable", null, null, null, null, null, null);
                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                if (c.moveToFirst()) {
                    // определяем номера столбцов по имени в выборке
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int emailColIndex = c.getColumnIndex("email");
                    do {
                        // получаем значения по номерам столбцов и пишем все в лог
                        Log.d(LOG_TAG,
                                "ID = " + c.getInt(idColIndex) +
                                        ", name = " + c.getString(nameColIndex) +
                                        ", email = " + c.getString(emailColIndex));
                        // переход на следующую строку
                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while (c.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                c.close();*
                break;
            case R.id.btnClear:
                Log.d(LOG_TAG, "--- Clear mytable: ---");
                // удаляем все записи
                int clearCount = db.delete("mytable", null, null);
                Log.d(LOG_TAG, "deleted rows count = " + clearCount);
                break;*/
        }
        // закрываем подключение к БД
        dbHelper.close();
    }


    static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDBIONKidV1", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //  Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table mytableIONKidV1 ("
                    + "id integer primary key autoincrement,"
                   + "name text,"
                    + "email text,"
                    + "confirmPassword text,"
                    + "firstName text,"
                    + "secondName text,"
                    + "surname text,"
                   +"numberPhone text ,"
                    + "comment text"
                    + ");");
            db.execSQL("create table mytableIONKidV3 ("
                    + "comment text"
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}