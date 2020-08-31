package com.example.aba.menuActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aba.R;
import com.example.aba.users.UserDetails;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONException;
import org.json.JSONObject;

public class SensorKit extends AppCompatActivity {

    Button activateSensorKit, deactivateSensorKit;
    String isActivated;

    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_kit);
        findView();
        Firebase.setAndroidContext(this);

        activateSensorKit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final ProgressDialog pd = new ProgressDialog(SensorKit.this);
                pd.setMessage("Loading...");
                pd.show();

                String url = "https://ionkid-abd2f.firebaseio.com/Arduino/ActivateCode.json";

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Firebase reference = new Firebase("https://ionkid-abd2f.firebaseio.com/Arduino/ActivateCode");

                        if (s.equals("null")) {

                            reference.child("Activate").setValue("1");
                            reference.child("User").setValue(UserDetails.username);

                            Toast.makeText(SensorKit.this, "Device is activated", Toast.LENGTH_SHORT).show();

                        } else {
                            try {
                                JSONObject obj = new JSONObject(s);

                                if (!obj.has(UserDetails.username)) {
                                    reference.child("Activate").setValue("1");
                                    reference.child("User").setValue(UserDetails.username);

                                    Toast.makeText(SensorKit.this, "Device is activated", Toast.LENGTH_SHORT).show();
                                } else {
                                    reference.child("Activate").setValue("0");

                                    Toast.makeText(SensorKit.this, "device is deactivated", Toast.LENGTH_LONG).show();
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

                RequestQueue rQueue = Volley.newRequestQueue(SensorKit.this);
                rQueue.add(request);


            }
        });
        deactivateSensorKit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final ProgressDialog pd = new ProgressDialog(SensorKit.this);
                pd.setMessage("Loading...");
                pd.show();

                String url = "https://ionkid-abd2f.firebaseio.com/Arduino/ActivateCode.json";

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Firebase reference = new Firebase("https://ionkid-abd2f.firebaseio.com/Arduino/ActivateCode");

                        if (s.equals("null")) {

                            reference.child("Activate").setValue("0");
                            reference.child("User").setValue("");


                            Toast.makeText(SensorKit.this, "Device is deactivated", Toast.LENGTH_SHORT).show();

                        } else {
                            try {
                                JSONObject obj = new JSONObject(s);

                                if (!obj.has(UserDetails.username)) {

                                    reference.child("Activate").setValue("0");
                                    reference.child("User").setValue("");


                                    Toast.makeText(SensorKit.this, "Device is deactivated", Toast.LENGTH_SHORT).show();

                                } else {
                                    reference.child("Activate").setValue("0");

                                    Toast.makeText(SensorKit.this, "device is deactivated", Toast.LENGTH_LONG).show();
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

                RequestQueue rQueue = Volley.newRequestQueue(SensorKit.this);
                rQueue.add(request);


            }
        });
    }

    public void findView() {


        activateSensorKit = findViewById(R.id.activateSensorKit);
        deactivateSensorKit=findViewById(R.id.deactivateSensorKit);
    }


}
