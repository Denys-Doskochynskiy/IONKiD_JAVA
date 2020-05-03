package com.example.aba;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class AddKid extends AppCompatActivity {
    Button addKid;
    String genderKid, widthKid,lastNameKid;
    String cityKid, countryKid;
    String growthKid, diagnoseKid, dateKid;
    String firstNameKid,  bloodTypeKid;
    EditText gender, width;
    EditText  city, country;
    EditText growth, diagnose, date;
    EditText first, lastKid, bloodType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_kid);
        findView();
        Firebase.setAndroidContext(this);



        addKid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderKid = gender.getText().toString();
                widthKid = width.getText().toString();
                lastNameKid = lastKid.getText().toString();
                firstNameKid = first.getText().toString();

                cityKid = city.getText().toString();
                countryKid = country.getText().toString();
                growthKid = growth.getText().toString();
                diagnoseKid = diagnose.getText().toString();
                dateKid = date.getText().toString();
                bloodTypeKid = bloodType.getText().toString();

          if (genderKid.equals("")) {
              gender.setError("can't be blank");


                } else if (firstNameKid.equals("")) {
                    first.setError("can't be blank");

          } else if (lastNameKid.equals("")) {
              lastKid.setError("can't be blank");

                } else if (countryKid.equals("")) {
                    country.setError("can't be blank");

                } else if (cityKid.equals("")) {
                    city.setError("can't be blank");

/*
                } else if(widthKid.equals("")) {
                  last.setError("can't be blank");
*/
                } else if (dateKid.equals("")) {
                    date.setError("can't be blank");

                } else if (widthKid.equals("")) {
                    width.setError("can't be blank");

                } else if (growthKid.equals("")) {
                    growth.setError("can't be blank");


                } else if (bloodTypeKid.equals("")) {
                    bloodType.setError("can't be blank");

                } else if (diagnoseKid.equals("")) {
                    diagnose.setError("can't be blank");
                } else {
                    final ProgressDialog pd = new ProgressDialog(AddKid.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://ionkid-abd2f.firebaseio.com/users/kids.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                                Firebase reference = new Firebase("https://ionkid-abd2f.firebaseio.com/users/"+UserDetails.username+"/kids");

                            if (s.equals("null")) {
                                reference.child(lastNameKid).child("Width").setValue(widthKid);
                                reference.child(lastNameKid).child("Gennder").setValue(genderKid);
                                reference.child(lastNameKid).child("firstNameKid").setValue(firstNameKid);
                                reference.child(lastNameKid).child("width").setValue(widthKid);
                                reference.child(lastNameKid).child("blood").setValue(bloodTypeKid);
                                reference.child(lastNameKid).child("date").setValue(dateKid);
                                reference.child(lastNameKid).child("diagnose").setValue(diagnoseKid);
                                reference.child(lastNameKid).child("gender").setValue(genderKid);
                                reference.child(lastNameKid).child("growth").setValue(growthKid);


                                reference.child(lastNameKid).child("country").setValue(countryKid);
                                reference.child(lastNameKid).child("city").setValue(cityKid);
                                UserDetails.kidName=lastNameKid;
                                Toast.makeText(AddKid.this, "registration successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(AddKid.this, Menu.class));
                            } else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(lastNameKid) ){
                                        reference.child(lastNameKid).child("Width").setValue(widthKid);
                                        reference.child(lastNameKid).child("Gennder").setValue(genderKid);
                                        reference.child(lastNameKid).child("firstNameKid").setValue(firstNameKid);
                                        reference.child(lastNameKid).child("width").setValue(widthKid);
                                        reference.child(lastNameKid).child("blood").setValue(bloodTypeKid);
                                        reference.child(lastNameKid).child("date").setValue(dateKid);
                                        reference.child(lastNameKid).child("diagnose").setValue(diagnoseKid);
                                        reference.child(lastNameKid).child("gender").setValue(genderKid);
                                        reference.child(lastNameKid).child("growth").setValue(growthKid);


                                        reference.child(lastNameKid).child("country").setValue(countryKid);
                                        reference.child(lastNameKid).child("city").setValue(cityKid);
                                        Toast.makeText(AddKid.this, "registration successful", Toast.LENGTH_LONG).show();
                                        UserDetails.kidName=lastNameKid;
                                        startActivity(new Intent(AddKid.this, Menu.class));
                                    } else {
                                        Toast.makeText(AddKid.this, "username already exists", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError);
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(AddKid.this);
                    rQueue.add(request);
                }

            }
        });
    }

    public void findView() {
        gender = findViewById(R.id.gender);
        width = findViewById(R.id.weidth);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        growth = findViewById(R.id.growth);
        diagnose = findViewById(R.id.diagnose);
        date = findViewById(R.id.dateOfBirth);
        first = findViewById(R.id.firstName);
        lastKid= findViewById(R.id.lastKidName);
        bloodType = findViewById(R.id.bloodType);
        addKid = findViewById(R.id.addKid);
    }




}
