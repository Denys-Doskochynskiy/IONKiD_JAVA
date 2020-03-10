package com.example.aba;


import android.content.ContentValues;
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


public class Login extends AppCompatActivity{
        private EditText username;
        private EditText password;
        private Button login;
        private TextView loginLocked;
        private TextView attempts;
        private TextView numberOfAttempts;
        private Button signup;


        int numberOfRemainingLoginAttempts = 3;
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            username = (EditText) findViewById(R.id.edit_user);
            password = (EditText) findViewById(R.id.edit_password);
            login = (Button) findViewById(R.id.singin);
            loginLocked = (TextView) findViewById(R.id.login_locked);
            attempts = (TextView) findViewById(R.id.attempts);
            numberOfAttempts = (TextView) findViewById(R.id.number_of_attempts);
            numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));
            signup = (Button) findViewById(R.id.signup);
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSignUp();
                }
            });



        }
    public void onSignUp()
    {
        Intent intent = new Intent(this, Reg.class);
        startActivity(intent);
    }



        public void onClick(View view)
        {
            if (username.getText().toString().equals("admin") &&
                    password.getText().toString().equals("admin"))
            {
                Toast.makeText(getApplicationContext(), "Вхід виконано!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Menu.class);
                startActivity(intent);
            }

            else
            {
                Toast.makeText(getApplicationContext(), "Неправильні дані!",Toast.LENGTH_SHORT).show();
                numberOfRemainingLoginAttempts--;

                attempts.setVisibility(View.VISIBLE);
                numberOfAttempts.setVisibility(View.VISIBLE);
                numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));

                if (numberOfRemainingLoginAttempts == 0)
                {
                    login.setEnabled(false);
                    loginLocked.setVisibility(View.VISIBLE);
                    loginLocked.setBackgroundColor(Color.RED);
                    loginLocked.setText("Вхід заблоковано!");
                }
            }

        }



}
