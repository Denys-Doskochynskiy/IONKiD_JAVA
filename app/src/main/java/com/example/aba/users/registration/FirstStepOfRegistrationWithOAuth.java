/*
 *
 *   Created Your Name on 16.10.20 16:37
 *   Copyright Ⓒ 2020. All rights reserved Ⓒ 2020 http://freefuninfo.com/
 *   Last modified: 16.10.20 16:23
 *
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 *   except in compliance with the License. You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENS... Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *    either express or implied. See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package com.example.aba.users.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aba.R;

import com.example.aba.users.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirstStepOfRegistrationWithOAuth extends AppCompatActivity {
    EditText emailUser;
    EditText passwordUser;
    Button registerButton;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findView();
        progressBar=findViewById(R.id.progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(emailUser.getText().toString()
                        ,passwordUser.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()){
                            firebaseAuth.getCurrentUser().sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            UserDetails.secretKey_1=emailUser.getText().toString();
                                            UserDetails.secretKey_2=passwordUser.getText().toString();
                                            Toast.makeText(FirstStepOfRegistrationWithOAuth.this,"reg is success,pleas check your email to verification",
                                                    Toast.LENGTH_LONG).show();
                                            UserDetails.username=emailUser.getText().toString().replace(".",",");
                                            startActivity(new Intent(FirstStepOfRegistrationWithOAuth.this, SecondStepOfRegistration.class));
                                        }
                                    });


                        }else{
                            Toast.makeText(FirstStepOfRegistrationWithOAuth.this,task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });
    }
    public void findView() {


        emailUser = findViewById(R.id.email);
        passwordUser = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);

    }
}