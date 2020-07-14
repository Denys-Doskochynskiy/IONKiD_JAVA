package com.example.aba.users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aba.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RecoverPasswordWithFBAuth extends AppCompatActivity {
ProgressBar progressBar;
EditText userEmail;
Button recoverPass;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password_with_f_b_auth);
        findView();
        firebaseAuth=FirebaseAuth.getInstance();
        recoverPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()){
                                    Toast.makeText(RecoverPasswordWithFBAuth.this,
                                            "Please check your email",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(RecoverPasswordWithFBAuth.this,LoginWithFBAuth.class));
                                }else {
                                    Toast.makeText(RecoverPasswordWithFBAuth.this,
                                            task.getException().getMessage(),Toast.LENGTH_LONG).show();


                                }
                            }
                        });
            }
        });
    }
    public void findView(){
        userEmail=findViewById(R.id.emailUser);
        recoverPass=findViewById(R.id.recover);
        progressBar=findViewById(R.id.progressBar);
    }
}
