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

public class TaskActivityDay2 extends AppCompatActivity implements OnClickListener {


    CheckBox cb1, cb2, cb3, cb4, cb5;
    Button buttonSave;
    FirebaseDatabase database;
    DatabaseReference reference;
    TaskFB taskFB;
    int i = 0;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_day2);

        reference = database.getInstance().getReference().child("tasksOfTuesday");


        taskFB = new TaskFB();
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);
        progressBar = findViewById(R.id.pb);
        buttonSave = findViewById(R.id.buttonsave);
        buttonSave.setOnClickListener(this);


        /*final String d1 = "Task1";
        final String d2 = "Task2";
        final String d3 = "Task3";
        final String d4 = "Task4";
        final String d5 = "Task5";*/

               /* reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            i = (int) dataSnapshot.getChildrenCount();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    i = (int) dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();
        if (preferences.contains("checkbox1") && preferences.getBoolean("checkbox1", false) == true) {
            cb1.setChecked(true);
        } else {
            cb1.setChecked(false);

        }
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cb1.isChecked()) {
                    editor.putBoolean("checkbox1", true);
                    progressBar.setProgress(progressBar.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("checkbox1", false);
                    progressBar.setProgress(progressBar.getProgress()-20);
                    editor.apply();
                }
            }
        });


        if (preferences.contains("checkbox2") && preferences.getBoolean("checkbox2", false) == true) {
            cb2.setChecked(true);
        } else {
            cb2.setChecked(false);

        }
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cb2.isChecked()) {
                    editor.putBoolean("checkbox2", true);
                    progressBar.setProgress(progressBar.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("checkbox2", false);
                    progressBar.setProgress(progressBar.getProgress()-20);
                    editor.apply();
                }
            }
        });


        if (preferences.contains("checkbox3") && preferences.getBoolean("checkbox3", false) == true) {
            cb3.setChecked(true);
        } else {
            cb3.setChecked(false);

        }
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cb3.isChecked()) {
                    editor.putBoolean("checkbox3", true);
                    progressBar.setProgress(progressBar.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("checkbox3", false);
                    progressBar.setProgress(progressBar.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("checkbox4") && preferences.getBoolean("checkbox4", false) == true) {
            cb4.setChecked(true);
        } else {
            cb4.setChecked(false);

        }
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cb4.isChecked()) {
                    editor.putBoolean("checkbox4", true);
                    progressBar.setProgress(progressBar.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("checkbox4", false);
                    progressBar.setProgress(progressBar.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("checkbox5") && preferences.getBoolean("checkbox5", false) == true) {
            cb5.setChecked(true);
        } else {
            cb5.setChecked(false);

        }
        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cb5.isChecked()) {
                    editor.putBoolean("checkbox5", true);
                    progressBar.setProgress(progressBar.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("checkbox5", false);
                    progressBar.setProgress(progressBar.getProgress()-20);
                    editor.apply();
                }
            }
        });

    }

    public void onClick(View v) {

        final String d1 = "Task1 is checked";
        final String d2 = "Task2 is checked";
        final String d3 = "Task3 is checked";
        final String d4 = "Task4 is checked";
        final String d5 = "Task5 is checked";

        final String n1 = "Task1 is not checked";
        final String n2 = "Task2 is not checked";
        final String n3 = "Task3 is not checked";
        final String n4 = "Task4 is not checked";
        final String n5 = "Task5 is not checked";

        switch (v.getId()) {
            case R.id.buttonsave:
                Intent intent = new Intent(this, TestComment2.class);
                startActivity(intent);
        }

        if (cb1.isChecked()) {
            taskFB.setTask(d1);
            reference.child(String.valueOf(i + 1)).setValue(taskFB);
        } else {
            taskFB.setTask(n1);
            reference.child(String.valueOf(i + 1)).setValue(taskFB);
        }
        if (cb2.isChecked()) {
            taskFB.setTask(d2);
            reference.child(String.valueOf(i + 2)).setValue(taskFB);
        } else {
            taskFB.setTask(n2);
            reference.child(String.valueOf(i + 2)).setValue(taskFB);
        }
        if (cb3.isChecked()) {
            taskFB.setTask(d3);
            reference.child(String.valueOf(i + 3)).setValue(taskFB);
        } else {
            taskFB.setTask(n3);
            reference.child(String.valueOf(i + 3)).setValue(taskFB);
        }
        if (cb4.isChecked()) {
            taskFB.setTask(d4);
            reference.child(String.valueOf(i + 4)).setValue(taskFB);
        } else {
            taskFB.setTask(n4);
            reference.child(String.valueOf(i + 4)).setValue(taskFB);
        }
        if (cb5.isChecked()) {
            taskFB.setTask(d5);
            reference.child(String.valueOf(i + 5)).setValue(taskFB);
        } else {
            taskFB.setTask(n5);
            reference.child(String.valueOf(i + 5)).setValue(taskFB);
        }
    }
}