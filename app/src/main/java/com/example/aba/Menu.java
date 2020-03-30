package com.example.aba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Button taskActivity = findViewById(R.id.taskActivity);
        Button doctors = findViewById(R.id.doctors);
        Button kids = findViewById(R.id.kids);
        Button chat = findViewById(R.id.chat);
        Button profil = findViewById(R.id.profil);

        taskActivity.setOnClickListener(this);
        doctors.setOnClickListener(this);
        kids.setOnClickListener(this);
        chat.setOnClickListener(this);
        profil.setOnClickListener(this);



/*Поки що знаходиться в розробці
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfil();
            }
        });*/


        taskActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTask();
            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRetrofitTest();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    public void openTask() {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }

    public void openRetrofitTest() {
        Intent intent = new Intent(this, RetrofitTest.class);
        startActivity(intent);
    }

    /*Поки що знаходиться в розробці
    public void openProfil () {
        Intent intent = new Intent(this, Chose
                .class);
        startActivity(intent);
    }*/
}