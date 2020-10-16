package com.example.aba.skip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.aba.R;
import com.example.aba.users.UserDetails;
import com.example.aba.users.registration.FirstStepOfRegistrationWithOAuth;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SkipData extends AppCompatActivity {
    DatabaseReference reff;
    String status ="";
    String log ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip_data);
        final FirebaseFirestore dbFirestore = FirebaseFirestore.getInstance();
        reff = FirebaseDatabase.getInstance().getReference().child("users/" + UserDetails.username + "/kids/").child(UserDetails.kidName).child("SKIP");
        reff.addValueEventListener(new ValueEventListener() {

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 status = dataSnapshot.child("status").getValue().toString();
                 log = dataSnapshot.child("LogTest").getValue().toString();

                Map<String, Object> skipData = new HashMap<>();
                skipData.put("Status",status);
                skipData.put("Log",log);

                dbFirestore.collection("UserInfo").document(UserDetails.username).collection("kids").document(UserDetails.kidName).set(skipData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SkipData.this,status,
                                Toast.LENGTH_SHORT).show();
                        Toast.makeText(SkipData.this,"success",
                                Toast.LENGTH_SHORT).show();
                    }
                }) .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SkipData.this,"shit happens",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                ;
                /*
                String bpm = dataSnapshot.child("Blood Type").getValue().toString();
                String noiseSensor = dataSnapshot.child("Width").getValue().toString();
                String accelerometer = dataSnapshot.child("date").getValue().toString();
                */

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}