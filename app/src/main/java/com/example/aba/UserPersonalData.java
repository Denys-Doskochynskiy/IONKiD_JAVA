package com.example.aba;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;

public class UserPersonalData extends AppCompatActivity {
TextView first,second,surName,number;
DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_personal_data);
        first=findViewById(R.id.firstName);
        second=findViewById(R.id.secondName);
        number=findViewById(R.id.country);

        surName=findViewById(R.id.surname);


        reff= FirebaseDatabase.getInstance().getReference().child("users").child(UserDetails.username);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            /*
            *  reference.child(email).child("firstName").setValue(firstNameUser);
                                        reference.child(email).child("lastName").setValue(lastNameUser);
                                        reference.child(email).child("surname").setValue(surnameUser);

                                        reference.child(email).child("password").setValue(pass);

                                        reference.child(email).child("phoneNumber").setValue(phone);
*/
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child("firstName").getValue().toString();
                String sur=dataSnapshot.child("surname").getValue().toString();
                String nextName=dataSnapshot.child("lastName").getValue().toString();
                String  phone=dataSnapshot.child("phoneNumber").getValue().toString();

                first.setText(name);
                second.setText(nextName);
                surName.setText(sur);
                number.setText(phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
