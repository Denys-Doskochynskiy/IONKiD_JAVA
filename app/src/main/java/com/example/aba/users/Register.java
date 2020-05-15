package com.example.aba.users;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
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
import com.example.aba.R;
import com.example.aba.kids.AddKidInRegistration;
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
        findView();
        Firebase.setAndroidContext(this);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = numberOfPhone.getText().toString();
                surnameUser = surname.getText().toString();
                lastNameUser = lastName.getText().toString();
                firstNameUser = firstName.getText().toString();

                email = emailUser.getText().toString().replace(".", ",");
                pass = password.getText().toString();
                confirm = confirmPassword.getText().toString();
                if (lastNameUser.equals("")) {
                    lastName.setError("can't be blank");

                } else if (firstNameUser.equals("")) {
                    firstName.setError("can't be blank");

                } else if (surnameUser.equals("")) {
                    surname.setError("can't be blank");

                } else if (email.equals("")) {
                    emailUser.setError("can't be blank");


                } else if (pass.equals("")) {
                    password.setError("can't be blank");

                } else if (confirm.equals("")) {
                    confirmPassword.setError("can't be blank");

                } else if (phone.equals("")) {
                    numberOfPhone.setError("can't be blank");

                } else if (!lastNameUser.matches("[A-Za-z]+")) {
                    lastName.setError("Use only alphabet");

                } else if (!firstNameUser.matches("[A-Za-z]+")) {
                    firstName.setError("Use only alphabet");

                } else if (!surnameUser.matches("[A-Za-z]+")) {
                    surname.setError("Use only alphabet");

              /*  } else if (!email.matches("^[a-z0-9](\\.?[a-z0-9]){5,29}@gmail\\    .com$")) {
                    emailUser.setError(
                            "Please don't use only corect email");*/
                } else if (email.length() < 13) {
                    emailUser.setError("at least 5 characters long");
                } else if (!pass.matches("[A-Za-z0-9]+")) {
                    password.setError("only alphabet or number allowed");
                } else if (pass.length() < 5) {
                    password.setError("at least 5 characters long");

                } else if (!pass.equals(confirm)) {
                    confirmPassword.setError("password does not match");
                } else if (!phone.matches("[0-9]+")) {
                    numberOfPhone.setError("Use only number");

                } else {
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
                                UserDetails.username = email;

                                Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Register.this, AddKidInRegistration.class));
                            } else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(email)) {
                                        reference.child(email).child("firstName").setValue(firstNameUser);
                                        reference.child(email).child("lastName").setValue(lastNameUser);
                                        reference.child(email).child("surname").setValue(surnameUser);

                                        reference.child(email).child("password").setValue(pass);

                                        reference.child(email).child("phoneNumber").setValue(phone);
                                        UserDetails.username = email;
                                        Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Register.this, AddKidInRegistration.class));
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

    public void findView() {

        confirmPassword = findViewById(R.id.confirmPassword);
        emailUser = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);
        numberOfPhone = findViewById(R.id.numberOfPhone);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        surname = findViewById(R.id.surname);
    }


    static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "myDBIONKidV1", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("create table mytableIONKidV1 ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "email text,"
                    + "confirmPassword text,"
                    + "firstName text,"
                    + "secondName text,"
                    + "surname text,"
                    + "numberPhone text ,"
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