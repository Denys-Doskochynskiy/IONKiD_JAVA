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
import com.example.aba.comment.days.DayComment;
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

public class DayTaskActivity extends AppCompatActivity implements OnClickListener {


    CheckBox box11, box12, box13, box14, box15;
    Button save1;
    FirebaseDatabase db1;
    DatabaseReference ref1;
    TaskFB task;
    int i = 0;
    ProgressBar pb11;
    int incFuel = 0;
    final String FUELBAR = "fuelBar";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_day);

        ref1 = db1.getInstance().getReference().child("users/"+ UserDetails.username+"/kids/").child(UserDetails.kidName).child("tasks/"+UserDetails.numberOfDay);

        task = new TaskFB();

        box11 = findViewById(R.id.box11);
        box12 = findViewById(R.id.box12);
        box13 = findViewById(R.id.box13);
        box14 = findViewById(R.id.box14);
        box15 = findViewById(R.id.box15);
        pb11 = findViewById(R.id.pb11);
        save1 = findViewById(R.id.save1);
        save1.setOnClickListener(this);

        ref1.setValue(null);
        ref1.addValueEventListener(new ValueEventListener() {
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
        if (preferences.contains("box11") && preferences.getBoolean("box11", false) == true) {
            box11.setChecked(true);
        } else {
            box11.setChecked(false);

        }
        box11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box11.isChecked()) {
                    editor.putBoolean("box11", true);
                    pb11.setProgress(pb11.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box11", false);
                    pb11.setProgress(pb11.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box12") && preferences.getBoolean("box12", false) == true) {
            box12.setChecked(true);
        } else {
            box12.setChecked(false);

        }
        box12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box12.isChecked()) {
                    editor.putBoolean("box12", true);
                    pb11.setProgress(pb11.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box12", false);
                    pb11.setProgress(pb11.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box13") && preferences.getBoolean("box13", false) == true) {
            box13.setChecked(true);
        } else {
            box13.setChecked(false);

        }
        box13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box13.isChecked()) {
                    editor.putBoolean("box13", true);
                    pb11.setProgress(pb11.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box13", false);
                    pb11.setProgress(pb11.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box14") && preferences.getBoolean("box14", false) == true) {
            box14.setChecked(true);
        } else {
            box14.setChecked(false);

        }
        box14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box14.isChecked()) {
                    editor.putBoolean("box14", true);
                    pb11.setProgress(pb11.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box14", false);
                    pb11.setProgress(pb11.getProgress()-20);
                    editor.apply();
                }
            }
        });

        if (preferences.contains("box15") && preferences.getBoolean("box15", false) == true) {
            box15.setChecked(true);
        } else {
            box15.setChecked(false);

        }
        box15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (box15.isChecked()) {
                    editor.putBoolean("box15", true);
                    pb11.setProgress(pb11.getProgress()+20);
                    editor.apply();
                } else {
                    editor.putBoolean("box15", false);
                    pb11.setProgress(pb11.getProgress()-20);
                    editor.apply();
                }
            }
        });
    }

    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPref.edit();
        editor1.putInt(FUELBAR, pb11.getProgress());
        editor1.commit();

    }

    public void onResume(){
        super.onResume();
        pb11 = (ProgressBar) findViewById(R.id.pb11);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        incFuel = sharedPref.getInt(FUELBAR, 0);
        pb11.setProgress(incFuel);
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
            case R.id.save1:
                Intent intent = new Intent(this, DayComment.class);
                startActivity(intent);
        }

        if (box11.isChecked()) {
            task.setTask(s1);
            ref1.child(String.valueOf(i + 1)).setValue(task);
        } else {
            task.setTask(p1);
            ref1.child(String.valueOf(i + 1)).setValue(task);
        }
        if (box12.isChecked()) {
            task.setTask(s2);
            ref1.child(String.valueOf(i + 2)).setValue(task);
        } else {
            task.setTask(p2);
            ref1.child(String.valueOf(i + 2)).setValue(task);
        }
        if (box13.isChecked()) {
            task.setTask(s3);
            ref1.child(String.valueOf(i + 3)).setValue(task);
        } else {
            task.setTask(p3);
            ref1.child(String.valueOf(i + 3)).setValue(task);
        }
        if (box14.isChecked()) {
            task.setTask(s4);
            ref1.child(String.valueOf(i + 4)).setValue(task);
        } else {
            task.setTask(p4);
            ref1.child(String.valueOf(i + 4)).setValue(task);
        }
        if (box15.isChecked()) {
            task.setTask(s5);
            ref1.child(String.valueOf(i + 5)).setValue(task);
        } else {
            task.setTask(p5);
            ref1.child(String.valueOf(i + 5)).setValue(task);
        }

    }
}