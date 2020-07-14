package com.example.aba.unimplementedORunused;
/*
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aba.R;
import com.example.aba.users.UserDetails;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

public class RecoverPassword extends AppCompatActivity {
    EditText emailUser, password;
    EditText confirmPassword;
    Button recover;
    String email, pass, confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password_with_f_b_auth);
        findView();
        Firebase.setAndroidContext(this);
        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailUser.getText().toString();
                UserDetails.usernameRecover=email.replace(".", ",");
                pass = password.getText().toString();
                confirm = confirmPassword.getText().toString();
                if (pass.equals("")) {
                    password.setError("can't be blank");

                } else if (confirm.equals("")) {
                    confirmPassword.setError("can't be blank");

                } else if (!pass.matches("[A-Za-z0-9]+")) {
                    password.setError("only alphabet or number allowed");
                } else if (pass.length() < 5) {
                    password.setError("at least 5 characters long");
                } else if (!pass.equals(confirm)) {
                    confirmPassword.setError("password does not match");
                } else {
                    final ProgressDialog pd = new ProgressDialog(RecoverPassword.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://ionkid-abd2f.firebaseio.com/users.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://ionkid-abd2f.firebaseio.com/users");

                            if (s.equals("null")) {
                                reference.child(UserDetails.usernameRecover ).child("password").setValue(pass);
                                Toast.makeText(RecoverPassword.this, "registration successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RecoverPassword.this, Login.class));
                            } else {
                                try {
                                    JSONObject obj = new JSONObject(s);
                                    if (obj.has(UserDetails.usernameRecover)) {
                                        reference.child(UserDetails.usernameRecover).child("password").setValue(pass);
                                        Toast.makeText(RecoverPassword.this, "recover successful", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RecoverPassword.this, Login.class));
                                    } else {
                                        Toast.makeText(RecoverPassword.this, "username does not exists", Toast.LENGTH_LONG).show();
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

                    RequestQueue rQueue = Volley.newRequestQueue(RecoverPassword.this);
                    rQueue.add(request);
                }

            }
        });
    }

    public void findView() {
        confirmPassword = findViewById(R.id.confirmRecover);
        emailUser = findViewById(R.id.emailUser);
        password = findViewById(R.id.passwordRecover);
        recover=findViewById(R.id.recover);
    }

}
*/
