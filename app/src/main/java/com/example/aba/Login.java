package com.example.aba;

import android.app.ProgressDialog;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity {
    TextView registerUser;
    EditText username, password;
    Button loginButton;
    String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerUser = findViewById(R.id.register);
        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString().replace(".","DOT");
                pass = password.getText().toString();

                if(user.equals("")){
                    username.setError("can't be blank");
                }
                else if(pass.equals("")){
                    password.setError("can't be blank");
                }
                else{
                    String url = "https://ionkid-abd2f.firebaseio.com/users.json";
                    final ProgressDialog pd = new ProgressDialog(Login.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            if(s.equals("null")){
                                Toast.makeText(Login.this, "user not found", Toast.LENGTH_LONG).show();
                            }
                            else{
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if(!obj.has(user)){
                                        Toast.makeText(Login.this, "user not found", Toast.LENGTH_LONG).show();
                                    }
                                    else if(obj.getJSONObject(user).getString("password").equals(pass)){
                                        UserDetails.username = user;
                                        UserDetails.password = pass;
                                        startActivity(new Intent(Login.this, Menu.class));
                                    }
                                    else {
                                        Toast.makeText(Login.this, "incorrect password", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }
                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError);
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(Login.this);
                    rQueue.add(request);
                }

            }
        });
    }
}


/*
public class Login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
   // private Button navDraw;
    private TextView loginLocked;
    private TextView attempts;
    private TextView numberOfAttempts;
    private TextView signup;
    Register.DBHelper dbHelper;

    int numberOfRemainingLoginAttempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.edit_user);
        password = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.singin);
        loginLocked = (TextView) findViewById(R.id.login_locked);
        attempts = (TextView) findViewById(R.id.attempts);
        numberOfAttempts = (TextView) findViewById(R.id.number_of_attempts);
        numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));
        signup = (TextView) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUp();
            }
        });
       /* navDraw = (Button) findViewById(R.id.nd);
        navDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUp2();
            }
        });*/
/*
        dbHelper = new Register.DBHelper(this);

    }

  /*  public void onSignUp2() {
        Intent intent = new Intent(this, NavdrawActivity.class);
        startActivity(intent);
    }*/


/*

    public void onSignUp() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }


    public void onClick(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytableIONKidV1", null, null, null, null, null, null);


        // определяем номера столбцов по имени в выборке

        if (c.moveToFirst()) {

            int nameColIndex = c.getColumnIndex("name");
            int emailColIndex = c.getColumnIndex("email");


            do {
                if (username.getText().toString().equals(c.getString(nameColIndex)) &&
                        password.getText().toString().equals(c.getString(emailColIndex))) {
                    Toast.makeText(getApplicationContext(), "Вхід виконано!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, Menu.class);
                    startActivity(intent);
                }

            } while (c.moveToNext());
        } else
            Toast.makeText(getApplicationContext(), "Неправильні дані!", Toast.LENGTH_SHORT).show();
        numberOfRemainingLoginAttempts--;
        attempts.setVisibility(View.VISIBLE);
        numberOfAttempts.setVisibility(View.VISIBLE);
        numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));

        if (numberOfRemainingLoginAttempts == 0) {
            login.setEnabled(false);
            loginLocked.setVisibility(View.VISIBLE);
            loginLocked.setBackgroundColor(Color.RED);
            loginLocked.setText("Вхід заблоковано!");
            c.close();
        }
        c.close();


    }


}*/