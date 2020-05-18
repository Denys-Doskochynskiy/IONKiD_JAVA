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
import com.example.aba.comment.days.TestComment5;
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

public class TaskActivityDay5 extends AppCompatActivity implements OnClickListener {

    CheckBox box51, box52, box53, box54, box55;
    Button save5;
    FirebaseDatabase db5;
    DatabaseReference ref5;
    TaskFB task;
    int i = 0;
    ProgressBar pb51;
    int incFuel = 0;
    final String FUELBAR = "fuelBar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_day5);

        ref5 = db5.getInstance().getReference().child("users/"+ UserDetails.username+"/kids/").child(UserDetails.kidName).child("tasks/tasksOfFriday");

        task = new TaskFB();

        box51 = findViewById(R.id.box51);
        box52 = findViewById(R.id.box52);
        box53 = findViewById(R.id.box53);
        box54 = findViewById(R.id.box54);
        box55 = findViewById(R.id.box55);
        pb51 = findViewById(R.id.pb51);
        save5 = findViewById(R.id.save5);
        save5.setOnClickListener(this);

        ref5.setValue(null);
        ref5.addValueEventListener(new ValueEventListener() {
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
        if (preferences.contains("box51") && preferences.getBoolean("box51", false) == true) {
            box51.setChecked(true);
        } else {
            box51.setChecked(false);

        }
        box51.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box51.isChecked()) {
                    editor.putBoolean("box51", true);
                    pb51.setProgress(pb51.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box51", false);
                    pb51.setProgress(pb51.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box52") && preferences.getBoolean("box52", false) == true) {
            box52.setChecked(true);
        } else {
            box52.setChecked(false);

        }
        box52.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box52.isChecked()) {
                    editor.putBoolean("box52", true);
                    pb51.setProgress(pb51.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box52", false);
                    pb51.setProgress(pb51.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box53") && preferences.getBoolean("box53", false) == true) {
            box53.setChecked(true);
        } else {
            box53.setChecked(false);

        }
        box53.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box53.isChecked()) {
                    editor.putBoolean("box53", true);
                    pb51.setProgress(pb51.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box53", false);
                    pb51.setProgress(pb51.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box54") && preferences.getBoolean("box54", false) == true) {
            box54.setChecked(true);
        } else {
            box54.setChecked(false);

        }
        box54.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box54.isChecked()) {
                    editor.putBoolean("box54", true);
                    pb51.setProgress(pb51.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box54", false);
                    pb51.setProgress(pb51.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box55") && preferences.getBoolean("box55", false) == true) {
            box55.setChecked(true);
        } else {
            box55.setChecked(false);

        }
        box55.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box55.isChecked()) {
                    editor.putBoolean("box55", true);
                    pb51.setProgress(pb51.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box55", false);
                    pb51.setProgress(pb51.getProgress()-20);
                    editor.apply();
                }
            }
        });
    }

    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPref.edit();
        editor1.putInt(FUELBAR, pb51.getProgress());
        editor1.commit();

    }

    public void onResume(){
        super.onResume();
        pb51 = (ProgressBar) findViewById(R.id.pb51);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        incFuel = sharedPref.getInt(FUELBAR, 0);
        pb51.setProgress(incFuel);
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
            case R.id.save5:
                Intent intent = new Intent(this, TestComment5.class);
                startActivity(intent);
        }

        if (box51.isChecked()) {
            task.setTask(s1);
            ref5.child(String.valueOf(i + 1)).setValue(task);
        } else {
            task.setTask(p1);
            ref5.child(String.valueOf(i + 1)).setValue(task);
        }
        if (box52.isChecked()) {
            task.setTask(s2);
            ref5.child(String.valueOf(i + 2)).setValue(task);
        } else {
            task.setTask(p2);
            ref5.child(String.valueOf(i + 2)).setValue(task);
        }
        if (box53.isChecked()) {
            task.setTask(s3);
            ref5.child(String.valueOf(i + 3)).setValue(task);
        } else {
            task.setTask(p3);
            ref5.child(String.valueOf(i + 3)).setValue(task);
        }
        if (box54.isChecked()) {
            task.setTask(s4);
            ref5.child(String.valueOf(i + 4)).setValue(task);
        } else {
            task.setTask(p4);
            ref5.child(String.valueOf(i + 4)).setValue(task);
        }
        if (box55.isChecked()) {
            task.setTask(s5);
            ref5.child(String.valueOf(i + 5)).setValue(task);
        } else {
            task.setTask(p5);
            ref5.child(String.valueOf(i + 5)).setValue(task);
        }

    }
}