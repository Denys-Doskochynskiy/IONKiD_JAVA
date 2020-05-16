package com.example.aba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ProgressBar;

public class TaskActivityDay3 extends AppCompatActivity implements OnClickListener {

    CheckBox box31, box32, box33, box34, box35;
    Button save3;
    FirebaseDatabase db3;
    DatabaseReference ref3;
    TaskFB task;
    int i = 0;
    ProgressBar pb31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_day3);

        ref3 = db3.getInstance().getReference().child("tasksOfWednesday");

        task = new TaskFB();

        box31 = findViewById(R.id.box31);
        box32 = findViewById(R.id.box32);
        box33 = findViewById(R.id.box33);
        box34 = findViewById(R.id.box34);
        box35 = findViewById(R.id.box35);
        pb31 = findViewById(R.id.pb31);
        save3 = findViewById(R.id.save3);
        save3.setOnClickListener(this);

        ref3.setValue(null);
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    i = (int) dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();
        if (preferences.contains("box31") && preferences.getBoolean("box31", false) == true) {
            box31.setChecked(true);
        } else {
            box31.setChecked(false);

        }
        box31.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box31.isChecked()) {
                    editor.putBoolean("box31", true);
                    pb31.setProgress(pb31.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box31", false);
                    pb31.setProgress(pb31.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box32") && preferences.getBoolean("box32", false) == true) {
            box32.setChecked(true);
        } else {
            box32.setChecked(false);

        }
        box32.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box32.isChecked()) {
                    editor.putBoolean("box32", true);
                    pb31.setProgress(pb31.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box32", false);
                    pb31.setProgress(pb31.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box33") && preferences.getBoolean("box33", false) == true) {
            box33.setChecked(true);
        } else {
            box33.setChecked(false);

        }
        box33.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box33.isChecked()) {
                    editor.putBoolean("box33", true);
                    pb31.setProgress(pb31.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box33", false);
                    pb31.setProgress(pb31.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box34") && preferences.getBoolean("box34", false) == true) {
            box34.setChecked(true);
        } else {
            box34.setChecked(false);

        }
        box34.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box34.isChecked()) {
                    editor.putBoolean("box34", true);
                    pb31.setProgress(pb31.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box34", false);
                    pb31.setProgress(pb31.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box35") && preferences.getBoolean("box35", false) == true) {
            box35.setChecked(true);
        } else {
            box35.setChecked(false);

        }
        box35.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box35.isChecked()) {
                    editor.putBoolean("box35", true);
                    pb31.setProgress(pb31.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box35", false);
                    pb31.setProgress(pb31.getProgress()-20);
                    editor.apply();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        final String s1 = "Task1 is checked";
        final String s2 = "Task2 is checked";
        final String s3 = "Task3 is checked";
        final String s4 = "Task4 is checked";
        final String s5 = "Task5 is checked";

        final String p1 = "Task1 is not checked";
        final String p2 = "Task2 is not checked";
        final String p3 = "Task3 is not checked";
        final String p4 = "Task4 is not checked";
        final String p5 = "Task5 is not checked";

        switch (v.getId()) {
            case R.id.save3:
                Intent intent = new Intent(this, TestComment3.class);
                startActivity(intent);
        }

        if (box31.isChecked()) {
            task.setTask(s1);
            ref3.child(String.valueOf(i + 1)).setValue(task);
        } else {
            task.setTask(p1);
            ref3.child(String.valueOf(i + 1)).setValue(task);
        }
        if (box32.isChecked()) {
            task.setTask(s2);
            ref3.child(String.valueOf(i + 2)).setValue(task);
        } else {
            task.setTask(p2);
            ref3.child(String.valueOf(i + 2)).setValue(task);
        }
        if (box33.isChecked()) {
            task.setTask(s3);
            ref3.child(String.valueOf(i + 3)).setValue(task);
        } else {
            task.setTask(p3);
            ref3.child(String.valueOf(i + 3)).setValue(task);
        }
        if (box34.isChecked()) {
            task.setTask(s4);
            ref3.child(String.valueOf(i + 4)).setValue(task);
        } else {
            task.setTask(p4);
            ref3.child(String.valueOf(i + 4)).setValue(task);
        }
        if (box35.isChecked()) {
            task.setTask(s5);
            ref3.child(String.valueOf(i + 5)).setValue(task);
        } else {
            task.setTask(p5);
            ref3.child(String.valueOf(i + 5)).setValue(task);
        }

    }
}