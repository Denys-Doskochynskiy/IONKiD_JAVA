package com.example.aba.unimplementedOrUnused;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aba.R;
import com.example.aba.kids.KidList;
import com.example.aba.users.RecoverPasswordWithFBAuth;
import com.example.aba.users.registration.SecondStepOfRegistration;
import com.example.aba.users.UserDetails;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity {
    TextView registerUser,recoverPassword;
    EditText username, password;
    Button loginButton;
    String user, pass;
    final String SAVED_TEXT = "test";

    SharedPreferences sp, sharePrefEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = getSharedPreferences("login", MODE_PRIVATE);
        if (sp.getBoolean("loggeded", false)) {
            loadText();
            goToKidList();
        }
        findViewAndClickListener();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString().replace(".", ",");
                pass = password.getText().toString();

                if (user.equals("")) {
                    username.setError("can't be blank");
                } else if (pass.equals("")) {
                    password.setError("can't be blank");
                } else {
                    String url = "https://ionkid-abd2f.firebaseio.com/users.json";
                    final ProgressDialog pd = new ProgressDialog(Login.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            if (s.equals("null")) {
                                Toast.makeText(Login.this, "user not found", Toast.LENGTH_LONG).show();
                            } else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(user)) {
                                        Toast.makeText(Login.this, "user not found", Toast.LENGTH_LONG).show();
                                    } else if (obj.getJSONObject(user).getString("password").equals(pass)) {
                                        UserDetails.username = user;
                                        UserDetails.password = pass;
                                        saveText();
                                        startActivity(new Intent(Login.this, KidList.class));
                                        sp.edit().putBoolean("loggeded", true).apply();
                                    } else {
                                        Toast.makeText(Login.this, "incorrect password", Toast.LENGTH_LONG).show();
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

                    RequestQueue rQueue = Volley.newRequestQueue(Login.this);
                    rQueue.add(request);
                }

            }
        });
    }

    void saveText() {
        sharePrefEmail = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor edit = sharePrefEmail.edit();
        edit.putString(SAVED_TEXT, UserDetails.username);
        edit.commit();

    }

    public void loadText() {
        sharePrefEmail = getPreferences(MODE_PRIVATE);
        String savedText = sharePrefEmail.getString(SAVED_TEXT, "");
        UserDetails.username = savedText;


    }

    public void findViewAndClickListener() {

        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        registerUser = findViewById(R.id.register);
        recoverPassword=findViewById(R.id.forgotPassword);
        recoverPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, RecoverPasswordWithFBAuth.class));
            }
        });
        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SecondStepOfRegistration.class));
            }
        });
    }

    public void goToKidList() {
        Intent intent = new Intent(this, KidList.class);
        startActivity(intent);
    }
}
