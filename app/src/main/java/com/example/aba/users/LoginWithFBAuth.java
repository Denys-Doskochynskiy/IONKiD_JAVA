/*
 *
 *   Created Your Name on 16.10.20 16:43
 *   Copyright Ⓒ 2020. All rights reserved Ⓒ 2020 http://freefuninfo.com/
 *   Last modified: 16.10.20 16:43
 *
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 *   except in compliance with the License. You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENS... Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *    either express or implied. See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package com.example.aba.users;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aba.R;
import com.example.aba.kids.KidList;

import com.example.aba.menuActivity.Menu;
import com.example.aba.users.registration.FirstStepOfRegistrationWithOAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginWithFBAuth extends AppCompatActivity {
    ProgressBar progressBar;
    TextView registerUser,recoverPassword;
    EditText userEmail;
    EditText userPass;
    Button loginUser;
    FirebaseAuth firebaseAuth;
    final String SAVED_TEXT = "test";

    SharedPreferences sp, sharePrefEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

progressBar=findViewById(R.id.progressBar);
            userEmail = findViewById(R.id.email);
            userPass = findViewById(R.id.password);
            loginUser = findViewById(R.id.login);

        registerUser = findViewById(R.id.register);
        recoverPassword=findViewById(R.id.forgotPassword);
        recoverPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginWithFBAuth.this, RecoverPasswordWithFBAuth.class));
            }
        });
        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginWithFBAuth.this, FirstStepOfRegistrationWithOAuth.class));
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        sp = getSharedPreferences("login", MODE_PRIVATE);
        if (sp.getBoolean("loggeded", false)) {
            loadText();
            goToKidList();
        }
        loginUser.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View view){
               progressBar.setVisibility(View.VISIBLE);
               firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),
                       userPass.getText().toString())
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       progressBar.setVisibility(View.GONE);
                       if(task.isSuccessful()){
                           if(firebaseAuth.getCurrentUser().isEmailVerified()){

                               UserDetails.username = userEmail.getText().toString().replace(".", ",");
                               saveText();
                               startActivity(new Intent(LoginWithFBAuth.this, KidList.class));
                               sp.edit().putBoolean("loggeded", true).apply();


                           }else{  Toast.makeText(LoginWithFBAuth.this,"Please verify your email"
                                   ,Toast.LENGTH_LONG).show();}
                           }else{
                           Toast.makeText(LoginWithFBAuth.this,task.getException().getMessage()
                                   ,Toast.LENGTH_LONG).show();
                       }

                   }
               });
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
    public void goToKidList() {
        Intent intent = new Intent(this, KidList.class);
        startActivity(intent);
    }
}
