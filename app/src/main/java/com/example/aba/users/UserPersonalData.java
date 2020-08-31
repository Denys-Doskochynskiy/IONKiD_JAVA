package com.example.aba.users;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aba.R;
import com.example.aba.working_and_test.EncryptAndDecryptData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserPersonalData extends AppCompatActivity {
TextView first,surName,second,number;
DatabaseReference reff;
    String outString;

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

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child("firstName").getValue().toString();
                String sur=dataSnapshot.child("surname").getValue().toString();
                String nextName=dataSnapshot.child("lastName").getValue().toString();
                String  phone=dataSnapshot.child("phoneNumber").getValue().toString();

                try {
                    outString= EncryptAndDecryptData.decrypt(name,UserDetails.SECRET_KEY);
                    first.setText(outString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    outString= EncryptAndDecryptData.decrypt(sur,UserDetails.SECRET_KEY);
                    surName.setText(outString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    outString= EncryptAndDecryptData.decrypt(nextName,UserDetails.SECRET_KEY);
                    second.setText(outString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    outString= EncryptAndDecryptData.decrypt(phone,UserDetails.SECRET_KEY);
                    number.setText(outString);
                } catch (Exception e) {
                    e.printStackTrace();
                }





                /*first.setText(name);//
                second.setText(nextName);//
                surName.setText(sur);//
                number.setText(phone);//*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
