package com.example.aba;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskActivity extends AppCompatActivity {
    CheckBox checkBox, checkBoxSecond;
    Button save;
    TextView textView, textViewSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBoxSecond = (CheckBox) findViewById(R.id.checkBoxSecond);

        textView = (TextView) findViewById(R.id.textView);
        textViewSecond = (TextView) findViewById(R.id.textViewSecond);

        save = (Button) findViewById(R.id.buttonSave);

        //Saving CheckBox
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBox", false);
        checkBox.setChecked(checked);
        CheckBox checkBoxSecond = (CheckBox) findViewById(R.id.checkBoxSecond);
        checkBoxSecond.setChecked(checked);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
// TODO Auto-generated method stub
                SharedPreferences.Editor editor = getSharedPreferences("syllabus", 0).edit();
                editor.putBoolean("cbx1_ischecked", isChecked);
                editor.commit();
            }
        });
        checkBoxSecond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
// TODO Auto-generated method stub
                SharedPreferences.Editor editor = getSharedPreferences("syllabus", 0).edit();
                editor.putBoolean("cbx1_ischecked", isChecked);
                editor.commit();
            }
        });
        SharedPreferences settings = getSharedPreferences("syllabus", 0);
        Boolean isChecked = settings.getBoolean("cbx1_ischecked", false);
        {
            checkBox.setChecked(isChecked);
            if (isChecked) {
                textView.setBackgroundColor(Color.GREEN);
            } else {
                textView.setBackgroundColor(Color.WHITE);
            }
        }
        //Saving CheckBox


        {
            checkBoxSecond.setChecked(isChecked);
            if (isChecked) {
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