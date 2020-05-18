package com.example.aba.task.day;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.aba.R;
import com.example.aba.comment.days.TestComment4;
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

public class TaskActivityDay4 extends AppCompatActivity implements OnClickListener {


    CheckBox box41, box42, box43, box44, box45;
    Button save4;
    FirebaseDatabase db4;
    DatabaseReference ref4;
    TaskFB task;
    int i = 0;
    ProgressBar pb41;
    int incFuel = 0;
    final String FUELBAR = "fuelBar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_day4);

        ref4 = db4.getInstance().getReference().child("users/"+ UserDetails.username+"/kids/").child(UserDetails.kidName).child("tasks/tasksOfThursday");

        task = new TaskFB();

        box41 = findViewById(R.id.box41);
        box42 = findViewById(R.id.box42);
        box43 = findViewById(R.id.box43);
        box44 = findViewById(R.id.box44);
        box45 = findViewById(R.id.box45);
        pb41 = findViewById(R.id.pb41);
        save4 = findViewById(R.id.save4);
        save4.setOnClickListener(this);

        ref4.setValue(null);
        ref4.addValueEventListener(new ValueEventListener() {
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
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPref.edit();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();
        if (preferences.contains("box41") && preferences.getBoolean("box41", false) == true) {
            box41.setChecked(true);
        } else {
            box41.setChecked(false);

        }
        box41.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box41.isChecked()) {
                    editor.putBoolean("box41", true);
                    pb41.setProgress(pb41.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box41", false);
                    pb41.setProgress(pb41.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box42") && preferences.getBoolean("box42", false) == true) {
            box42.setChecked(true);
        } else {
            box42.setChecked(false);

        }
        box42.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box42.isChecked()) {
                    editor.putBoolean("box42", true);
                    pb41.setProgress(pb41.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box42", false);
                    pb41.setProgress(pb41.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box43") && preferences.getBoolean("box43", false) == true) {
            box43.setChecked(true);
        } else {
            box43.setChecked(false);

        }
        box43.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box43.isChecked()) {
                    editor.putBoolean("box43", true);
                    pb41.setProgress(pb41.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box43", false);
                    pb41.setProgress(pb41.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box44") && preferences.getBoolean("box44", false) == true) {
            box44.setChecked(true);
        } else {
            box44.setChecked(false);

        }
        box44.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box44.isChecked()) {
                    editor.putBoolean("box44", true);
                    pb41.setProgress(pb41.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box44", false);
                    pb41.setProgress(pb41.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box45") && preferences.getBoolean("box45", false) == true) {
            box45.setChecked(true);
        } else {
            box45.setChecked(false);

        }
        box45.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box45.isChecked()) {
                    editor.putBoolean("box45", true);
                    pb41.setProgress(pb41.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box45", false);
                    pb41.setProgress(pb41.getProgress()-20);
                    editor.apply();
                }
            }
        });
    }

    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPref.edit();
        editor1.putInt(FUELBAR, pb41.getProgress());
        editor1.commit();

    }

    public void onResume(){
        super.onResume();
        pb41 = (ProgressBar) findViewById(R.id.pb41);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        incFuel = sharedPref.getInt(FUELBAR, 0);
        pb41.setProgress(incFuel);
    }

    public void onStop(){
        super.onStop();
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
            case R.id.save4:
                Intent intent = new Intent(this, TestComment4.class);
                startActivity(intent);
        }

        if (box41.isChecked()) {
            task.setTask(s1);
            ref4.child(String.valueOf(i + 1)).setValue(task);
        } else {
            task.setTask(p1);
            ref4.child(String.valueOf(i + 1)).setValue(task);
        }
        if (box42.isChecked()) {
            task.setTask(s2);
            ref4.child(String.valueOf(i + 2)).setValue(task);
        } else {
            task.setTask(p2);
            ref4.child(String.valueOf(i + 2)).setValue(task);
        }
        if (box43.isChecked()) {
            task.setTask(s3);
            ref4.child(String.valueOf(i + 3)).setValue(task);
        } else {
            task.setTask(p3);
            ref4.child(String.valueOf(i + 3)).setValue(task);
        }
        if (box44.isChecked()) {
            task.setTask(s4);
            ref4.child(String.valueOf(i + 4)).setValue(task);
        } else {
            task.setTask(p4);
            ref4.child(String.valueOf(i + 4)).setValue(task);
        }
        if (box45.isChecked()) {
            task.setTask(s5);
            ref4.child(String.valueOf(i + 5)).setValue(task);
        } else {
            task.setTask(p5);
            ref4.child(String.valueOf(i + 5)).setValue(task);
        }

    }
}