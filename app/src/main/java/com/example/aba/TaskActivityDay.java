package com.example.aba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class TaskActivityDay extends AppCompatActivity {

    CheckBox checkBox, checkBoxSecond;
    Button save;
    TextView textView, textViewSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_day);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBoxSecond = (CheckBox) findViewById(R.id.checkBoxSecond);

        textView = (TextView) findViewById(R.id.textView);
        textViewSecond = (TextView) findViewById(R.id.textViewSecond);

        save = (Button) findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
        //Saving CheckBox

/////////////////////////////////////////////////////////////////////////////////////////////////////
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
// TODO Auto-generated method stub
                SharedPreferences.Editor editor = getSharedPreferences("syllabus", 0).edit();
                editor.putBoolean("cbx1_", isChecked);
                editor.commit();
            }
        });
        SharedPreferences settings = getSharedPreferences("syllabus", 0);
        Boolean isChecked = settings.getBoolean("cbx1_", false);
/////////////////////////////////////////////////////////////////////////////////////////////////////
        checkBoxSecond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isCheck) {
// TODO Auto-generated method stub
                SharedPreferences.Editor editor = getSharedPreferences("syllabus", 0).edit();
                editor.putBoolean("cbx2_", isCheck);
                editor.commit();
            }
        });

        SharedPreferences setting = getSharedPreferences("syllabus", 0);
        Boolean isCheck = setting.getBoolean("cbx2_", false);
        ////////////////////////////////////////////////////////////////////////////////////


        {
            checkBox.setChecked(isChecked);
            if (isChecked) {
                textView.setBackgroundColor(Color.BLUE);
            } else {
                textView.setBackgroundColor(Color.WHITE);
            }

            //Saving CheckBox


            checkBoxSecond.setChecked(isCheck);
            if (isCheck) {
                textViewSecond.setBackgroundColor(Color.GREEN);
            } else {
                textViewSecond.setBackgroundColor(Color.WHITE);
            }
        }
    }


    public void OnCheck(View view) {

        if (checkBox.isChecked()) {
            textView.setBackgroundColor(Color.GREEN);
        } else {
            textView.setBackgroundColor(Color.WHITE);
        }


    }

    public void onCheckSecond(View view) {
        if (checkBoxSecond.isChecked()) {
            textViewSecond.setBackgroundColor(Color.GREEN);
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
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

}


  /*
   Перехід на інше вікно


     next = (Button) findViewById(R.id.task);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest2();
            }
        });
    public void openTaskTest2 () {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

*/

