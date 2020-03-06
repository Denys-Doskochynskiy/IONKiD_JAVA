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

        Button task = findViewById(R.id.task);
        Button doctors = findViewById(R.id.doctors);
        Button kids = findViewById(R.id.kids);
        Button chat = findViewById(R.id.chat);
        Button profil = findViewById(R.id.profil);

        task.setOnClickListener(this);
        doctors.setOnClickListener(this);
        kids.setOnClickListener(this);
        chat.setOnClickListener(this);
        profil.setOnClickListener(this);
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest2();
            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.doctors:
                Toast.makeText(this , "Doctors temporarily unavailable", Toast.LENGTH_SHORT).show();
                break;
            case R.id.kids:
                Toast.makeText(this , "Kids temporarily unavailable", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat:
                Toast.makeText(this , "Chat temporarily unavailable", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profil:
                Toast.makeText(this , "Profile", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public void openTaskTest2 () {
        Intent intent = new Intent(this, Task.class);
        startActivity(intent);
    }
    public void openTaskTest () {
        Intent intent = new Intent(this, Chose
                .class);
        startActivity(intent);
    }
}