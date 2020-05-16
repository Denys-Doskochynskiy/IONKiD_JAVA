package com.example.aba.task.day;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.aba.R;
import com.example.aba.comment.days.TestComment2;
import com.example.aba.task.TaskFB;
import com.example.aba.users.UserDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ProgressBar;

public class TaskActivityDay2 extends AppCompatActivity implements OnClickListener {


    CheckBox box21, box22, box23, box24, box25;
    Button save2;
    FirebaseDatabase db2;
    DatabaseReference ref2;
    TaskFB task;
    int i = 0;
    ProgressBar pb21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_day2);

        ref2 =  db2.getInstance().getReference().child("users/"+ UserDetails.username+"/kids/").child(UserDetails.kidName).child("tasks/tasksOfTuesday");

        task = new TaskFB();

        box21 = findViewById(R.id.box21);
        box22 = findViewById(R.id.box22);
        box23 = findViewById(R.id.box23);
        box24 = findViewById(R.id.box24);
        box25 = findViewById(R.id.box25);
        pb21 = findViewById(R.id.pb21);
        save2 = findViewById(R.id.save2);
        save2.setOnClickListener(this);

        ref2.setValue(null);
        ref2.addValueEventListener(new ValueEventListener() {
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
        if (preferences.contains("box21") && preferences.getBoolean("box21", false) == true) {
            box21.setChecked(true);
        } else {
            box21.setChecked(false);

        }
        box21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box21.isChecked()) {
                    editor.putBoolean("box21", true);
                    pb21.setProgress(pb21.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box21", false);
                    pb21.setProgress(pb21.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box22") && preferences.getBoolean("box22", false) == true) {
            box22.setChecked(true);
        } else {
            box22.setChecked(false);

        }
        box22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box22.isChecked()) {
                    editor.putBoolean("box22", true);
                    pb21.setProgress(pb21.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box22", false);
                    pb21.setProgress(pb21.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box23") && preferences.getBoolean("box23", false) == true) {
            box23.setChecked(true);
        } else {
            box23.setChecked(false);

        }
        box23.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box23.isChecked()) {
                    editor.putBoolean("box23", true);
                    pb21.setProgress(pb21.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box23", false);
                    pb21.setProgress(pb21.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box24") && preferences.getBoolean("box24", false) == true) {
            box24.setChecked(true);
        } else {
            box24.setChecked(false);

        }
        box24.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box24.isChecked()) {
                    editor.putBoolean("box24", true);
                    pb21.setProgress(pb21.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box24", false);
                    pb21.setProgress(pb21.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box25") && preferences.getBoolean("box25", false) == true) {
            box25.setChecked(true);
        } else {
            box25.setChecked(false);

        }
        box25.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box25.isChecked()) {
                    editor.putBoolean("box25", true);
                    pb21.setProgress(pb21.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box25", false);
                    pb21.setProgress(pb21.getProgress()-20);
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
            case R.id.save2:
                Intent intent = new Intent(this, TestComment2.class);
                startActivity(intent);
        }

        if (box21.isChecked()) {
            task.setTask(s1);
            ref2.child(String.valueOf(i + 1)).setValue(task);
        } else {
            task.setTask(p1);
            ref2.child(String.valueOf(i + 1)).setValue(task);
        }
        if (box22.isChecked()) {
            task.setTask(s2);
            ref2.child(String.valueOf(i + 2)).setValue(task);
        } else {
            task.setTask(p2);
            ref2.child(String.valueOf(i + 2)).setValue(task);
        }
        if (box23.isChecked()) {
            task.setTask(s3);
            ref2.child(String.valueOf(i + 3)).setValue(task);
        } else {
            task.setTask(p3);
            ref2.child(String.valueOf(i + 3)).setValue(task);
        }
        if (box24.isChecked()) {
            task.setTask(s4);
            ref2.child(String.valueOf(i + 4)).setValue(task);
        } else {
            task.setTask(p4);
            ref2.child(String.valueOf(i + 4)).setValue(task);
        }
        if (box25.isChecked()) {
            task.setTask(s5);
            ref2.child(String.valueOf(i + 5)).setValue(task);
        } else {
            task.setTask(p5);
            ref2.child(String.valueOf(i + 5)).setValue(task);
        }

    }
}