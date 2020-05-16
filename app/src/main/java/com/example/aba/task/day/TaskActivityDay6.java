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

public class TaskActivityDay6 extends AppCompatActivity implements OnClickListener {

    CheckBox box61, box62, box63, box64, box65;
    Button save6;
    FirebaseDatabase db6;
    DatabaseReference ref6;
    TaskFB task;
    int i = 0;
    ProgressBar pb61;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_day6);

        ref6 = db6.getInstance().getReference().child("tasksOfSaturday");

        task = new TaskFB();

        box61 = findViewById(R.id.box61);
        box62 = findViewById(R.id.box62);
        box63 = findViewById(R.id.box63);
        box64 = findViewById(R.id.box64);
        box65 = findViewById(R.id.box65);
        pb61 = findViewById(R.id.pb61);
        save6 = findViewById(R.id.save6);
        save6.setOnClickListener(this);

        ref6.setValue(null);
        ref6.addValueEventListener(new ValueEventListener() {
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
        if (preferences.contains("box61") && preferences.getBoolean("box61", false) == true) {
            box61.setChecked(true);
        } else {
            box61.setChecked(false);

        }
        box61.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box61.isChecked()) {
                    editor.putBoolean("box61", true);
                    pb61.setProgress(pb61.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box61", false);
                    pb61.setProgress(pb61.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box62") && preferences.getBoolean("box62", false) == true) {
            box62.setChecked(true);
        } else {
            box62.setChecked(false);

        }
        box62.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box62.isChecked()) {
                    editor.putBoolean("box62", true);
                    pb61.setProgress(pb61.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box62", false);
                    pb61.setProgress(pb61.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box63") && preferences.getBoolean("box63", false) == true) {
            box63.setChecked(true);
        } else {
            box63.setChecked(false);

        }
        box63.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box63.isChecked()) {
                    editor.putBoolean("box63", true);
                    pb61.setProgress(pb61.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box63", false);
                    pb61.setProgress(pb61.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box64") && preferences.getBoolean("box64", false) == true) {
            box64.setChecked(true);
        } else {
            box64.setChecked(false);

        }
        box64.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box64.isChecked()) {
                    editor.putBoolean("box64", true);
                    pb61.setProgress(pb61.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box64", false);
                    pb61.setProgress(pb61.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box65") && preferences.getBoolean("box65", false) == true) {
            box65.setChecked(true);
        } else {
            box65.setChecked(false);

        }
        box65.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box65.isChecked()) {
                    editor.putBoolean("box65", true);
                    pb61.setProgress(pb61.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box65", false);
                    pb61.setProgress(pb61.getProgress()-20);
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
            case R.id.save6:
                Intent intent = new Intent(this, TestComment6.class);
                startActivity(intent);
        }

        if (box61.isChecked()) {
            task.setTask(s1);
            ref6.child(String.valueOf(i + 1)).setValue(task);
        } else {
            task.setTask(p1);
            ref6.child(String.valueOf(i + 1)).setValue(task);
        }
        if (box62.isChecked()) {
            task.setTask(s2);
            ref6.child(String.valueOf(i + 2)).setValue(task);
        } else {
            task.setTask(p2);
            ref6.child(String.valueOf(i + 2)).setValue(task);
        }
        if (box63.isChecked()) {
            task.setTask(s3);
            ref6.child(String.valueOf(i + 3)).setValue(task);
        } else {
            task.setTask(p3);
            ref6.child(String.valueOf(i + 3)).setValue(task);
        }
        if (box64.isChecked()) {
            task.setTask(s4);
            ref6.child(String.valueOf(i + 4)).setValue(task);
        } else {
            task.setTask(p4);
            ref6.child(String.valueOf(i + 4)).setValue(task);
        }
        if (box65.isChecked()) {
            task.setTask(s5);
            ref6.child(String.valueOf(i + 5)).setValue(task);
        } else {
            task.setTask(p5);
            ref6.child(String.valueOf(i + 5)).setValue(task);
        }

    }
}