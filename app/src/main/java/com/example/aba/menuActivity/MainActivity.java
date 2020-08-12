package com.example.aba.menuActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.aba.R;
import com.example.aba.users.LoginWithFBAuth;
import com.example.aba.users.UserDetails;

import java.lang.reflect.Method;

import static java.lang.Class.*;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading1);
        UserDetails.SECRET_KEY = Build.BOARD;
        System.out.println("Board ID:"+UserDetails.SECRET_KEY);
        load();
    }


    public void load() {
        new Handler().postDelayed(new Runnable() {

            @SuppressLint("HardwareIds")
            @Override
            public void run() {

                Intent homeIntent = new Intent(MainActivity.this, LoginWithFBAuth.class);
                startActivity(homeIntent);

            }

        }, SPLASH);

    }
}

