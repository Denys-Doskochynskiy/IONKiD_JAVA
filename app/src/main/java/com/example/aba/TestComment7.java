package com.example.aba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestComment7 extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    Button btnAdd, btnMenu;
    EditText editComment;
    DatabaseReference reff;
    Comment comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_comment7);
        btnAdd = (Button) findViewById(R.id.btnReadAndAdd);
        reff = FirebaseDatabase.getInstance().getReference().child("tasksOfSunday/comment");
        comment = new Comment();
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                comment.setComment(editComment.getText().toString().trim());
                reff.setValue(comment);
                Toast.makeText(TestComment7.this,"comment was sent",Toast.LENGTH_LONG).show();
            }

        });
        btnMenu = (Button) findViewById(R.id.btnBackMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });


        editComment = (EditText) findViewById(R.id.comment);
    }

    public void openMenu() {
        Intent intent = new Intent(this, com.example.aba.Menu.class);
        startActivity(intent);
    }
}