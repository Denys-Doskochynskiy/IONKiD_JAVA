package com.example.aba;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskActivityDay extends AppCompatActivity {

    CheckBox checkBox, checkBoxSecond;
    Button save;
    TextView textView, textViewSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_day);


        textView = findViewById(R.id.textView);
        textViewSecond = findViewById(R.id.textViewSecond);

        save = findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        checkBoxFirst();
        SharedPreferences settings = getSharedPreferences("syllabus", 0);
        Boolean isChecked = settings.getBoolean("cbx1_", false);

        checkBoxSecond();
        SharedPreferences setting = getSharedPreferences("syllabus", 0);
        Boolean isCheck = setting.getBoolean("cbx2_", false);


        {
            checkBox.setChecked(isChecked);
            if (isChecked) {
                textView.setBackgroundColor(Color.BLUE);
            } else {
                textView.setBackgroundColor(Color.WHITE);
            }


            checkBoxSecond.setChecked(isCheck);
            if (isCheck) {
                textViewSecond.setBackgroundColor(Color.BLUE);
            } else {
                textViewSecond.setBackgroundColor(Color.WHITE);
            }
        }
    }

    public void checkBoxSecond() {
        checkBoxSecond = findViewById(R.id.checkBoxSecond);
        checkBoxSecond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isCheck) {

                SharedPreferences.Editor editor = getSharedPreferences("syllabus", 0).edit();
                editor.putBoolean("cbx2_", isCheck);
                editor.commit();
            }
        });

    }

    public void checkBoxFirst() {
        checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {

                SharedPreferences.Editor editor = getSharedPreferences("syllabus", 0).edit();
                editor.putBoolean("cbx1_", isChecked);
                editor.commit();
            }
        });
    }

    public void OnCheck(View view) {

        if (checkBox.isChecked()) {
            textView.setBackgroundColor(Color.BLUE);
        } else {
            textView.setBackgroundColor(Color.WHITE);
        }


    }

    public void onCheckSecond(View view) {
        if (checkBoxSecond.isChecked()) {
            textViewSecond.setBackgroundColor(Color.BLUE);
        } else {
            textViewSecond.setBackgroundColor(Color.WHITE);
        }
    }

    public void onButton(View view) {

        if (checkBox.isChecked()) {
            checkBox.setEnabled(false);
        }
        if (checkBoxSecond.isChecked()) {
            checkBoxSecond.setEnabled(false);
        }


    }

    public void openMenu() {
        Intent intent = new Intent(this, TestComment.class);
        startActivity(intent);
    }

}




