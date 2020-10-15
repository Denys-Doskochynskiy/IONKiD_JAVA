package com.example.aba.users.registration;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aba.R;
import com.example.aba.users.UserDetails;
import com.example.aba.working_and_test.EncryptAndDecryptData;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SecondStepOfRegistration extends AppCompatActivity {
    EditText numberOfPhone;
    EditText firstName, surname, lastName;
    Button registerButton;
    String decryptPhone, decryptSurnameUser, decryptLastNameUser, decryptFirstNameUser;
    String phone, surnameUser, lastNameUser, firstNameUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference user = db.collection("usersInfo");
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_step_registration);
        /*findView();*/
        Firebase.setAndroidContext(this);

        db = FirebaseFirestore.getInstance();
        registerButton = findViewById(R.id.registerButton);
        numberOfPhone = findViewById(R.id.numberOfPhone);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        surname = findViewById(R.id.surname);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = numberOfPhone.getText().toString().trim();//
                final String surnameUser = surname.getText().toString().trim();//
                final String lastNameUser = lastName.getText().toString().trim();//
                final String firstNameUser = firstName.getText().toString().trim();//


                if (lastNameUser.equals("")) {
                    lastName.setError("can't be blank");

                } else if (firstNameUser.equals("")) {
                    firstName.setError("can't be blank");

                } else if (surnameUser.equals("")) {
                    surname.setError("can't be blank");


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

                } else if (!phone.matches("[0-9]+")) {
                    numberOfPhone.setError("Use only number");

                } else {
                    final ProgressDialog pd = new ProgressDialog(SecondStepOfRegistration.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    Map<String, Object> user = new HashMap<>();
                    user.put("First Name", firstNameUser);
                    user.put("Surname", surnameUser);
                    user.put("Last name", lastNameUser);
                    user.put("Phone", phone);

                    db.collection("UserInfo").document(UserDetails.username)
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "User Info added");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    String error = e.getMessage();
                                    Toast.makeText(SecondStepOfRegistration.this, "Error " + error, Toast.LENGTH_LONG);
                                }
                            });

                  /*  String url = "https://ionkid-abd2f.firebaseio.com/users.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://ionkid-abd2f.firebaseio.com/users");

                            if (s.equals("null")) {
                                try {
                                    decryptLastNameUser = EncryptAndDecryptData.encrypt(lastNameUser, UserDetails.SECRET_KEY);
                                    reference.child(UserDetails.username).child("lastName").setValue(decryptLastNameUser);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                try {
                                    decryptPhone = EncryptAndDecryptData.encrypt(phone, UserDetails.SECRET_KEY);
                                    reference.child(UserDetails.username).child("phoneNumber").setValue(decryptPhone);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    decryptSurnameUser = EncryptAndDecryptData.encrypt(surnameUser, UserDetails.SECRET_KEY);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                reference.child(UserDetails.username).child("surname").setValue(decryptSurnameUser);

                                try {
                                    decryptLastNameUser = EncryptAndDecryptData.encrypt(lastNameUser, UserDetails.SECRET_KEY);
                                    reference.child(UserDetails.username).child("lastName").setValue(decryptLastNameUser);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    decryptFirstNameUser = EncryptAndDecryptData.encrypt(firstNameUser, UserDetails.SECRET_KEY);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                reference.child(UserDetails.username).child("firstName").setValue(decryptFirstNameUser);


                                UserDetails.registerCheck = "1";*/

                                Toast.makeText(SecondStepOfRegistration.this, "registration successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SecondStepOfRegistration.this, ThirdStepOfRegistrationAddKid.class));/*
                            } else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(UserDetails.username)) {
                                        reference.child(UserDetails.username).child("firstName").setValue(firstNameUser);
                                        reference.child(UserDetails.username).child("lastName").setValue(lastNameUser);
                                        reference.child(UserDetails.username).child("surname").setValue(surnameUser);
                                        reference.child(UserDetails.username).child("phoneNumber").setValue(phone);

                                        try {
                                            decryptPhone = EncryptAndDecryptData.encrypt(phone, UserDetails.SECRET_KEY);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        reference.child(UserDetails.username).child("phoneNumber").setValue(phone);

                                        try {
                                            decryptSurnameUser = EncryptAndDecryptData.encrypt(surnameUser, UserDetails.SECRET_KEY);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        reference.child(UserDetails.username).child("surname").setValue(decryptSurnameUser);

                                        try {
                                            decryptLastNameUser = EncryptAndDecryptData.encrypt(lastNameUser, UserDetails.SECRET_KEY);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        reference.child(UserDetails.username).child("lastName").setValue(decryptLastNameUser);

                                        try {
                                            decryptFirstNameUser = EncryptAndDecryptData.encrypt(firstNameUser, UserDetails.SECRET_KEY);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        reference.child(UserDetails.username).child("firstName").setValue(decryptFirstNameUser);

                                        UserDetails.registerCheck = "1";
                                        Toast.makeText(SecondStepOfRegistration.this, "registration successful", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(SecondStepOfRegistration.this, ThirdStepOfRegistrationAddKid.class));
                                    } else {
                                        Toast.makeText(SecondStepOfRegistration.this, "username already exists", Toast.LENGTH_LONG).show();
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

                    RequestQueue rQueue = Volley.newRequestQueue(SecondStepOfRegistration.this);
                    rQueue.add(request);*/
                }

            }
        });
    }




   /* public void findView() {



        numberOfPhone = findViewById(R.id.numberOfPhone);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        surname = findViewById(R.id.surname);
    }*/


    /*static class DBHelper extends SQLiteOpenHelper {

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
    }*/

}

