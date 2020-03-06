package com.example.aba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Task extends AppCompatActivity {
    TextView txt, txt1, txt2, txt3, txt4, txt5;
    CheckBox cb, cb1, cb2, cb3, cb4, cb5;
    Button btn,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        cb = (CheckBox) findViewById(R.id.checkBox);
        cb1 = (CheckBox) findViewById(R.id.checkBox2);
        cb2 = (CheckBox) findViewById(R.id.checkBox3);
        cb3 = (CheckBox) findViewById(R.id.checkBox4);
        cb4 = (CheckBox) findViewById(R.id.checkBox5);
        cb5 = (CheckBox) findViewById(R.id.checkBox6);
        txt = (TextView) findViewById(R.id.textView);
        txt1 = (TextView) findViewById(R.id.textView2);
        txt2 = (TextView) findViewById(R.id.textView3);
        txt3 = (TextView) findViewById(R.id.textView4);
        txt4 = (TextView) findViewById(R.id.textView5);
        txt5 = (TextView) findViewById(R.id.textView6);
        btn = (Button) findViewById(R.id.doctors);
        next = (Button) findViewById(R.id.task);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest2();
            }
        });
    }
    public void OnCheck(View view) {
        if (cb.isChecked()) {

            txt.setBackgroundColor(Color.GREEN);
        } else {
            txt.setBackgroundColor(Color.WHITE);
        }


    }
    public void onCheck1(View view){
        if(cb1.isChecked()) {
            txt1.setBackgroundColor(Color.GREEN);
        }else{
            txt1.setBackgroundColor(Color.WHITE);
        }
    }
    public void onCheck2(View view){
        if(cb2.isChecked()) {
            txt2.setBackgroundColor(Color.GREEN);
        }else{
            txt2.setBackgroundColor(Color.WHITE);
        }
    }
    public void onCheck3(View view){
        if(cb3.isChecked()) {
            txt3.setBackgroundColor(Color.GREEN);
        }else{
            txt3.setBackgroundColor(Color.WHITE);
        }
    }

    public void onCheck4(View view){
        if(cb4.isChecked()) {
            txt4.setBackgroundColor(Color.GREEN);
        }else{
            txt4.setBackgroundColor(Color.WHITE);
        }
    }
    public void onCheck5(View view){
        if(cb5.isChecked()) {
            txt5.setBackgroundColor(Color.GREEN);
        }else{
            txt5.setBackgroundColor(Color.WHITE);
        }
    }

    public void onButton(View view) {

        if (cb.isChecked()) {
            cb.setEnabled(false);
        }
        if (cb1.isChecked()) {
            cb1.setEnabled(false);
        }
        if (cb2.isChecked()) {
            cb2.setEnabled(false);
        }
        if (cb3.isChecked()) {
            cb3.setEnabled(false);
        }
        if (cb4.isChecked()) {
            cb4.setEnabled(false);
        }
        if (cb5.isChecked()) {
            cb5.setEnabled(false);
        }
    }
    public void openTaskTest2 () {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }


}
