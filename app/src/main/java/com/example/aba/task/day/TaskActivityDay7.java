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
import android.widget.CompoundButton;
import android.widget.ProgressBar;

import com.example.aba.R;
import com.example.aba.comment.days.TestComment7;
import com.example.aba.task.TaskFB;
import com.example.aba.users.UserDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TaskActivityDay7 extends AppCompatActivity implements View.OnClickListener {

    CheckBox box1, box2, box3, box4, box5;
    Button save;
    FirebaseDatabase db;
    DatabaseReference ref;
    TaskFB task;
    int i = 0;
    ProgressBar pb1;
    int incFuel = 0;
    final String FUELBAR = "fuelBar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_day7);

        ref = db.getInstance().getReference().child("users/"+ UserDetails.username+"/kids/").child(UserDetails.kidName).child("tasks/tasksOfSunday");

        task = new TaskFB();

        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
        box5 = findViewById(R.id.box5);
        pb1 = findViewById(R.id.pb1);
        save = findViewById(R.id.save);
        save.setOnClickListener(this);

        ref.setValue(null);
        ref.addValueEventListener(new ValueEventListener() {
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
        if (preferences.contains("box1") && preferences.getBoolean("box1", false) == true) {
            box1.setChecked(true);
        } else {
            box1.setChecked(false);

        }
        box1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box1.isChecked()) {
                    editor.putBoolean("box1", true);
                    pb1.setProgress(pb1.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box1", false);
                    pb1.setProgress(pb1.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box2") && preferences.getBoolean("box2", false) == true) {
            box2.setChecked(true);
        } else {
            box2.setChecked(false);

        }
        box2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box2.isChecked()) {
                    editor.putBoolean("box2", true);
                    pb1.setProgress(pb1.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box2", false);
                    pb1.setProgress(pb1.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box3") && preferences.getBoolean("box3", false) == true) {
            box3.setChecked(true);
        } else {
            box3.setChecked(false);

        }
        box3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box3.isChecked()) {
                    editor.putBoolean("box3", true);
                    pb1.setProgress(pb1.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box3", false);
                    pb1.setProgress(pb1.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box4") && preferences.getBoolean("box4", false) == true) {
            box4.setChecked(true);
        } else {
            box4.setChecked(false);

        }
        box4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box4.isChecked()) {
                    editor.putBoolean("box4", true);
                    pb1.setProgress(pb1.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box4", false);
                    pb1.setProgress(pb1.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box5") && preferences.getBoolean("box5", false) == true) {
            box5.setChecked(true);
        } else {
            box5.setChecked(false);

        }
        box5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box5.isChecked()) {
                    editor.putBoolean("box5", true);
                    pb1.setProgress(pb1.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box5", false);
                    pb1.setProgress(pb1.getProgress()-20);
                    editor.apply();
                }
            }
        });
    }

    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPref.edit();
        editor1.putInt(FUELBAR, pb1.getProgress());
        editor1.commit();

    }

    public void onResume(){
        super.onResume();
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        incFuel = sharedPref.getInt(FUELBAR, 0);
        pb1.setProgress(incFuel);
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
            case R.id.save:
                Intent intent = new Intent(this, TestComment7.class);
                startActivity(intent);
        }

        if (box1.isChecked()) {
            task.setTask(s1);
            ref.child(String.valueOf(i + 1)).setValue(task);
        } else {
            task.setTask(p1);
            ref.child(String.valueOf(i + 1)).setValue(task);
        }
        if (box2.isChecked()) {
            task.setTask(s2);
            ref.child(String.valueOf(i + 2)).setValue(task);
        } else {
            task.setTask(p2);
            ref.child(String.valueOf(i + 2)).setValue(task);
        }
        if (box3.isChecked()) {
            task.setTask(s3);
            ref.child(String.valueOf(i + 3)).setValue(task);
        } else {
            task.setTask(p3);
            ref.child(String.valueOf(i + 3)).setValue(task);
        }
        if (box4.isChecked()) {
            task.setTask(s4);
            ref.child(String.valueOf(i + 4)).setValue(task);
        } else {
            task.setTask(p4);
            ref.child(String.valueOf(i + 4)).setValue(task);
        }
        if (box5.isChecked()) {
            task.setTask(s5);
            ref.child(String.valueOf(i + 5)).setValue(task);
        } else {
            task.setTask(p5);
            ref.child(String.valueOf(i + 5)).setValue(task);
        }

    }
}
