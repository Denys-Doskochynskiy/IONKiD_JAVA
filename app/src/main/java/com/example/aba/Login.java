package com.example.aba;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity
{
    DBHelper2 dbHelper;
    DBHelper2 dbHelper1;
    public EditText username;
    public EditText password;
    public Button login,singnup;
    public TextView loginLocked;
    public TextView attempts;
    public TextView numberOfAttempts;

    int numberOfRemainingLoginAttempts = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper2(  this);
        //dbHelper1 = new DBHelper2(this);
        username = (EditText) findViewById(R.id.edit_user);
        password = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.button_login);
        loginLocked = (TextView) findViewById(R.id.login_locked);
        attempts = (TextView) findViewById(R.id.attempts);
        numberOfAttempts = (TextView) findViewById(R.id.number_of_attempts);
        numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));
        singnup = (Button) findViewById(R.id.singup);
        singnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest();
            }
        });
    }
    public void openTaskTest () {
        Intent intent = new Intent(this, Reg.class);
        startActivity(intent);
    }
    public void Login(View view)
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
      //  SQLiteDatabase database1 = dbHelper1.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       // Cursor cursor = database.query(DBHelper2.TABLE_CONTACTS, null, null, null, null, null, null);
        Cursor cursor = database.query(DBHelper2.TABLE_CONTACTS, null, null, null, null, null,null);
        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(DBHelper2.KEY_NICK);
            int emailIndex = cursor.getColumnIndex(DBHelper2.KEY_PASS);

            if (username.getText().toString().equals(cursor.getString(nameIndex)) &&
                    password.getText().toString().equals(cursor.getString(emailIndex))) {
                Toast.makeText(getApplicationContext(), "Вхід виконано!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(Login.this, Menu.class);


                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Зареєструйтесь або перевірте логін/пароль!", Toast.LENGTH_SHORT).show();
                numberOfRemainingLoginAttempts--;

                attempts.setVisibility(View.VISIBLE);
                numberOfAttempts.setVisibility(View.VISIBLE);
                numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));

                if (numberOfRemainingLoginAttempts == 0) {
                    login.setEnabled(false);
                    loginLocked.setVisibility(View.VISIBLE);
                    loginLocked.setBackgroundColor(Color.RED);
                    loginLocked.setText("Вхід заблоковано!");
                }
                cursor.close();
            }

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openTaskTest2();
                }
            });
        }

        dbHelper.close();
    }
    public void openTaskTest2 () {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}

