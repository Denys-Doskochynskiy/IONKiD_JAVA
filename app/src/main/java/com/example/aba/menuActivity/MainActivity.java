package com.example.aba.menuActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

public class MainActivity extends AppCompatActivity {
    private static int SPLASH = 13000;
    String serial_no = null;

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
                try {
                    Class<?> c = Class.forName("android.os.SystemProperties");
                    Method get = c.getMethod("get", String.class);
                    serial_no = (String) get.invoke(c, "ro.serialno");
                    serial_no+="test";
                    System.out.println("Device serial ID : " + serial_no);
                   UserDetails.SECRET_KEY=serial_no;

                } catch (Exception e) {
                    System.out.println("Some error occured : " + e.getMessage());
                }

                Intent homeIntent = new Intent(MainActivity.this, LoginWithFBAuth.class);
                startActivity(homeIntent);

            }

        }, SPLASH);

    }
}

