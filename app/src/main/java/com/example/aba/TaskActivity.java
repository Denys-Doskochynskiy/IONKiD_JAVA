package com.example.aba;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);

        ProgressBar mondayBar = findViewById(R.id.mondayBar);

        mondayBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMondayBar();
            }
        });
    }
    public void openMondayBar() {
        Intent intent = new Intent(this, TaskActivityDay.class);
        startActivity(intent);
    }

}