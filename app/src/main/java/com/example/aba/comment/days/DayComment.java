package com.example.aba.comment.days;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DayComment extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    Button btnAdd;
    EditText editComment;
    DatabaseReference reff;
    FirebaseDatabase db1;
    Comment comment;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_comment1);
        btnAdd = findViewById(R.id.btnReadAndAdd);
        reff = db1.getInstance().getReference().child("users/"+ UserDetails.username+"/kids/").child(UserDetails.kidName).child("tasks/"+UserDetails.numberOfDay+"/comment");
        reff.addValueEventListener(new ValueEventListener() {
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

        comment = new Comment();
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                comment.setComment(editComment.getText().toString().trim());

                reff.child(String.valueOf(i)).setValue(comment);
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