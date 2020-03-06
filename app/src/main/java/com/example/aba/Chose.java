package com.example.aba;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Chose extends AppCompatActivity {
    private Button Sign_in,Sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);
        Sign_in = (Button) findViewById(R.id.Sign_in);
        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest1();
            }
        });
        Sign_up = (Button) findViewById(R.id.Sign_up);
        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest2();
            }
        });
    }
    public void openTaskTest1 () {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
    public void openTaskTest2 () {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }
}

