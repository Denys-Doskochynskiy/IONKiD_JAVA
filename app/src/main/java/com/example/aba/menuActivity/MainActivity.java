package com.example.aba.menuActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aba.R;
import com.example.aba.unimplementedORunused.Login;
import com.example.aba.users.LoginWithFBAuth;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH = 2800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading1);
        load();
    }

    public void load() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, LoginWithFBAuth.class);
                startActivity(homeIntent);

            }

        }, SPLASH);

    }
}

