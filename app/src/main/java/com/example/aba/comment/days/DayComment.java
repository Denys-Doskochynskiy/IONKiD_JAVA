package com.example.aba.comment.days;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aba.comment.Comment;
import com.example.aba.R;
import com.example.aba.menuActivity.Menu;
import com.example.aba.users.UserDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class DayComment extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    Button btnAdd;
    EditText editComment;
    DatabaseReference reff;
    Comment comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_comment1);
        btnAdd = findViewById(R.id.btnReadAndAdd);
        Calendar calendar =Calendar.getInstance();
        String currentData = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        reff = FirebaseDatabase.getInstance().getReference().child("users/"+ UserDetails.username+"/kids/").child(UserDetails.kidName).child("comments/"+currentData+"/comment");
        comment = new Comment();
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                comment.setComment(editComment.getText().toString().trim());
                reff.setValue(comment);
                Toast.makeText(DayComment.this,"comment was sent",Toast.LENGTH_LONG).show();
                openMenu();
            }

        });


        editComment = findViewById(R.id.comment);
    }

    public void openMenu() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}