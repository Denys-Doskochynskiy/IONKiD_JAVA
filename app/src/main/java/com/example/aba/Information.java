package com.example.aba;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Information extends AppCompatActivity implements View.OnClickListener{

   public Button btnAdd, btnRead, btnClear,bek;
   public EditText Nameinput,Yearinput,Dayinput,Surnameinpu,Monthinput,Patronymicinput;
   public TextView txt,txt1;

    DBHelper2 dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        txt1 = (TextView)findViewById(R.id.txt1);
        txt= (TextView) findViewById(R.id.textView8);
        Nameinput=(EditText) findViewById(R.id.Name);
        Surnameinpu=(EditText) findViewById(R.id.Surname);
        Patronymicinput=(EditText) findViewById(R.id.Patronymic);
        Dayinput=(EditText) findViewById(R.id.Day);
        Monthinput=(EditText) findViewById(R.id.Mounth);
        Yearinput=(EditText) findViewById(R.id.Year);



        txt1.setOnClickListener(this);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        bek = (Button) findViewById(R.id.bek);
        bek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest1();
            }
        });



        dbHelper = new DBHelper2(this);
    }
    public void openTaskTest1 () {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        String name = Nameinput.getText().toString();
        String surname = Surnameinpu.getText().toString();
        String patron = Patronymicinput.getText().toString();
        String year = Yearinput.getText().toString();
        String day = Dayinput.getText().toString();
        String mounth = Monthinput.getText().toString();


        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

//Toast.makeText(getApplicationContext(), "Вхід виконано!",Toast.LENGTH_SHORT).show();
        // Toast.makeText(getApplicationContext(), "Вхід виконано!"+ cursor.getString(nameIndex),Toast.LENGTH_SHORT).show();

        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DBHelper2.KEY_NAME1, name);
                contentValues.put(DBHelper2.KEY_SURNAME, surname);
                contentValues.put(DBHelper2.KEY_PATRONYMIC, patron);
                contentValues.put(DBHelper2.KEY_DAY, day);
                contentValues.put(DBHelper2.KEY_YEAR, year);
                contentValues.put(DBHelper2.KEY_MOUNTH, mounth);

                database.insert(DBHelper2.TABLE_CONTACTS, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper2.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {

                    do {int idIndex = cursor.getColumnIndex(DBHelper2.KEY_ID);
                        int dayIndex = cursor.getColumnIndex(DBHelper2.KEY_DAY);
                        int surnameIndex = cursor.getColumnIndex(DBHelper2.KEY_SURNAME);
                        int name1Index = cursor.getColumnIndex(DBHelper2.KEY_NAME1);
                        int yearIndex = cursor.getColumnIndex(DBHelper2.KEY_YEAR);
                        int mounthIndex = cursor.getColumnIndex(DBHelper2.KEY_MOUNTH);
                        int patronIndex = cursor.getColumnIndex(DBHelper2.KEY_PATRONYMIC);

                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +

                                        ",  = " + cursor.getString(yearIndex)+
                                        ", name = " + cursor.getString(name1Index) +
                                        ", day = " + cursor.getString(dayIndex) +", Surname is = " + cursor.getString(surnameIndex)
                                        +", patron is = " + cursor.getString(patronIndex)+", Year is = " + cursor.getString(yearIndex)+", Mounth = " + cursor.getString(mounthIndex)
                                );
                        txt1.setText(", name = " + cursor.getString(name1Index) +
                                ",  = " + cursor.getString(yearIndex)+
                                ", day = " + cursor.getString(dayIndex) +", Surname is = " + cursor.getString(surnameIndex)
                                +", patron is = " + cursor.getString(patronIndex)+", Year is = " + cursor.getString(yearIndex)+", Mounth = " + cursor.getString(mounthIndex));
                    } while (cursor.moveToNext());

                } else
                    Log.d("mLog","0 rows");
                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DBHelper2.KEY_YEAR , null, null);
                database.delete(DBHelper2.KEY_SURNAME, null, null);
                database.delete(DBHelper2.KEY_PATRONYMIC, null, null);
                database.delete(DBHelper2.KEY_MOUNTH, null, null);
                database.delete(DBHelper2.KEY_DAY, null, null);
                database.delete(DBHelper2.KEY_YEAR, null, null);
                database.delete(DBHelper2.KEY_HEIGHT, null, null);
                database.delete(DBHelper2.KEY_BLOUD, null, null);
                database.delete(DBHelper2.KEY_SEX, null, null);
                database.delete(DBHelper2.KEY_NAME1, null, null);

                break;
        }
        dbHelper.close();

    }


}
