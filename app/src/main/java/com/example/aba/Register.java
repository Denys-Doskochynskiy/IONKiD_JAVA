
package com.example.aba;


import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {
    EditText emailUser, password, numberOfPhone;
    EditText firstName, confirmPassword, surname, lastName;
    Button registerButton;
    String email, pass, confirm;
    String phone, surnameUser, lastNameUser, firstNameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        confirmPassword = findViewById(R.id.confirmPassword);
        emailUser = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);
        numberOfPhone = findViewById(R.id.numberOfPhone);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        surname = findViewById(R.id.surname);
        Firebase.setAndroidContext(this);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone=numberOfPhone.getText().toString();
                surnameUser=surname.getText().toString();
                lastNameUser=lastName.getText().toString();
                firstNameUser=firstName.getText().toString();

                email = emailUser.getText().toString().replace(".","DOT");
                pass = password.getText().toString();
                confirm = confirmPassword.getText().toString();
                if(lastNameUser.equals("")) {
                    lastName.setError("can't be blank");

                } else if (firstNameUser.equals("")) {
                    firstName.setError("can't be blank");

                } else if (surnameUser.equals("")) {
                    surname.setError("can't be blank");

                }else if(email.equals("")){
                    emailUser.setError("can't be blank");


                } else if (pass.equals("")) {
                    password.setError("can't be blank");

                } else if (confirm.equals("")) {
                    confirmPassword.setError("can't be blank");

                }else if (phone.equals("")) {
                    numberOfPhone.setError("can't be blank");

                }else if(!lastNameUser.matches("[A-Za-z]+")){
                    lastName.setError("Use only alphabet");

                }else if(!firstNameUser.matches("[A-Za-z]+")){
                    firstName.setError("Use only alphabet");

                }else if(!surnameUser.matches("[A-Za-z]+")){
                    surname.setError("Use only alphabet");

              /*  } else if (!email.matches("^[a-z0-9](\\.?[a-z0-9]){5,29}@gmail\\    .com$")) {
                    emailUser.setError(
                            "Please don't use only corect email");*/
                }  else if (email.length() < 13) {
                    emailUser.setError("at least 5 characters long");
                }  else if(!pass.matches("[A-Za-z0-9]+")){
                    password.setError("only alphabet or number allowed");
                }  else if (pass.length() < 5) {
                    password.setError("at least 5 characters long");

                } else if (!pass.equals(confirm)) {
                    confirmPassword.setError("password does not match");
                }else if(!phone.matches("[0-9]+")){
                    numberOfPhone.setError("Use only number");

                }

                else {
                    final ProgressDialog pd = new ProgressDialog(Register.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://ionkid-abd2f.firebaseio.com/users.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://ionkid-abd2f.firebaseio.com/users");

                            if (s.equals("null")) {


                                reference.child(email).child("firstName").setValue(firstNameUser);
                                reference.child(email).child("lastName").setValue(lastNameUser);
                                reference.child(email).child("surname").setValue(surnameUser);

                                reference.child(email).child("password").setValue(pass);

                                reference.child(email).child("phoneNumber").setValue(phone);




                                Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Register.this, Login.class));
                            } else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(email)) {
                                        reference.child(email).child("firstName").setValue(firstNameUser);
                                        reference.child(email).child("lastName").setValue(lastNameUser);
                                        reference.child(email).child("surname").setValue(surnameUser);

                                        reference.child(email).child("password").setValue(pass);

                                        reference.child(email).child("phoneNumber").setValue(phone);

                                        Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Register.this, Login.class));
                                    } else {
                                        Toast.makeText(Register.this, "username already exists", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError);
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(Register.this);
                    rQueue.add(request);
                }

            }
        });
    }
   /* final String LOG_TAG = "myLogs";

    Button btnAdd, btnRead, btnClear, next;
    EditText etName, etEmail, eNumberPhone,etFirstName,etSecondName,etSurname,etConfirmPassword;

    DBHelper dbHelper;


  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);*/
    /*    btnClear = (Button) findViewById(R.id.btnClean);
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
         /*       Log.d(LOG_TAG, "--- Delete from mytable: ---");
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
     /*   }
        // закрываем подключение к БД
        dbHelper.close();
    }
*/





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