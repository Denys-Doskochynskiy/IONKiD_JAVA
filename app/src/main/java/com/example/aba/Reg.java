package com.example.aba;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Reg extends AppCompatActivity implements View.OnClickListener{

    public Button btnAdd, btnRead, btnClear,btn3;
    public EditText etName, etEmail;
    public TextView txt,txt1;

    DBHelper2 dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        txt = (TextView)findViewById(R.id.textView);
txt1= (TextView) findViewById(R.id.textView8);
        txt1.setOnClickListener(this);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest2();
            }
        });

        dbHelper = new DBHelper2(this);
    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String mail = etEmail.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

//Toast.makeText(getApplicationContext(), "Вхід виконано!",Toast.LENGTH_SHORT).show();
  //       Toast.makeText(getApplicationContext(), "Вхід виконано!"+ cursor.getString(nameIndex),Toast.LENGTH_SHORT).show();

        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DBHelper2.KEY_NICK, name);
                contentValues.put(DBHelper2.KEY_PASS,mail);

                database.insert(DBHelper2.TABLE_CONTACTS, null, contentValues);
                break;


            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper2.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper2.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper2.KEY_NICK);
                    int mailIndex = cursor.getColumnIndex(DBHelper2.KEY_PASS);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", email = " + cursor.getString(mailIndex));
                        txt1.setText(", name = " + cursor.getString(nameIndex) +
                                ", pasword = " + cursor.getString(mailIndex));
                    } while (cursor.moveToNext());

                } else
                    Log.d("mLog","0 rows");
                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DBHelper2.TABLE_CONTACTS, null, null);


                  break;
        }
        dbHelper.close();

    }

    public void openTaskTest2 () {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
/*
import android.content.Intent;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Reg extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnRead, btnClear,btn3;
    EditText etName, etEmail;
    TextView txt1;

    DBHelper1 dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);

        txt1= (TextView) findViewById(R.id.textView8);
        txt1.setOnClickListener(this);

        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest2();
            }
        });


        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);

        dbHelper = new DBHelper1(this);
    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DBHelper1.KEY_NICK, name);
                contentValues.put(DBHelper1.KEY_PASS, email);

                database.insert(DBHelper1.TABLE_CONTACTS, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper1.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper1.KEY_ID);
                    int nickIndex = cursor.getColumnIndex(DBHelper1.KEY_NICK);
                    int passIndex = cursor.getColumnIndex(DBHelper1.KEY_PASS);

                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nickIndex) +
                                ", email = " + cursor.getString(passIndex));
                        txt1.setText(", name = " + cursor.getString(nickIndex) +
                                ", pasword = " + cursor.getString(passIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DBHelper1.KEY_PASS, null, null);
                database.delete(DBHelper1.KEY_NICK, null, null);
                break;
        }
        dbHelper.close();
    }

    public void openTaskTest2 () {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }}*/
