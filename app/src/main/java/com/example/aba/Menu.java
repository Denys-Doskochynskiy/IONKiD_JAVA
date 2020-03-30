package com.example.aba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Button taskActivity = findViewById(R.id.taskActivity);
        Button message = findViewById(R.id.messageActivity);
        Button kids = findViewById(R.id.kidsActivity);
        Button doctors = findViewById(R.id.doctorsActivity);
        Button settings = findViewById(R.id.settingsActivity);

        Button profil = findViewById(R.id.profil);

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

        /*Поки що знаходиться в розробці
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfil();
            }
        });*/

    }

    public void openTask() {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }

    public void openMessage() {
        Intent intent = new Intent(this, Chat.class);
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