package com.example.aba.kids;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aba.R;
import com.example.aba.users.UserDetails;
import com.example.aba.working_and_test.EncryptAndDecryptData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KidInfo extends AppCompatActivity {
    TextView first, second, cityKid, countryKid, genderKid, blood, diagnoseKid, dateKid, growthKid, widthKid;
    DatabaseReference reff;
    String outString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_kid);
        findView();


        reff = FirebaseDatabase.getInstance().getReference().child("users/" + UserDetails.username + "/kids/").child(UserDetails.kidName);
        reff.addValueEventListener(new ValueEventListener() {

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("firstNameKid").getValue().toString();
                String gender = dataSnapshot.child("Gender").getValue().toString();
                String city = dataSnapshot.child("city").getValue().toString();
                String bloodType = dataSnapshot.child("Blood Type").getValue().toString();
                String width = dataSnapshot.child("Width").getValue().toString();
                String date = dataSnapshot.child("date").getValue().toString();
                String diagnose = dataSnapshot.child("diagnose").getValue().toString();
                String growth = dataSnapshot.child("growth").getValue().toString();
                String country = dataSnapshot.child("country").getValue().toString();
                try {
                    outString= EncryptAndDecryptData.decrypt(name,UserDetails.SECRET_KEY);
                    first.setText(outString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //first.setText(name);
                second.setText(UserDetails.kidName);
                genderKid.setText(gender);
                countryKid.setText(country);
                cityKid.setText(city);
                blood.setText(bloodType);
                widthKid.setText(width);
                dateKid.setText(date);
                diagnoseKid.setText(diagnose);
                growthKid.setText(growth);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void findView() {
        first = findViewById(R.id.firstName);
        second = findViewById(R.id.lastKidName);
        countryKid = findViewById(R.id.country);

        cityKid = findViewById(R.id.city);
        widthKid = findViewById(R.id.weidth);

        growthKid = findViewById(R.id.growth);
        genderKid = findViewById(R.id.gender);
        blood = findViewById(R.id.blood);
        diagnoseKid = findViewById(R.id.diagnose);
        dateKid = findViewById(R.id.dateOfBirth);
    }
}
