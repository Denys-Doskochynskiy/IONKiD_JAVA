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
import com.example.aba.users.LoginWithFBAuth;
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
                    Toast.makeText(FirstStepOfRegistrationWithOAuth.this,"reg is success,pleas check your email to verification",
                            Toast.LENGTH_LONG).show();
                    startActivity(new Intent(FirstStepOfRegistrationWithOAuth.this, LoginWithFBAuth.class));
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
