package com.example.aba.users.registration;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aba.menuActivity.Menu;
import com.example.aba.R;
import com.example.aba.users.LoginWithFBAuth;
import com.example.aba.users.UserDetails;
import com.example.aba.working_and_test.EncryptAndDecryptData;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

public class ThirdStepOfRegistrationAddKid extends Activity {
    int DIALOG_DATE = 1;
    int myYear = 2011;
    int myMonth = 02;
    int myDay = 03;
    Button addKid;
    String widthKid, lastNameKid;
    String cityKid, countryKid;
    String growthKid, diagnoseKid, dateKid;
    String firstNameKid;
    EditText width;
    EditText city, country;
    TextView date;
    EditText growth, diagnose;
    String decryptNameKid;
    EditText first, lastKid, bloodType;
    String[] dataBlood = {"Select blood type", "|", "||", "|||", "|V"};
    String[] data = {"Select gender", "Male", "Female"};
    final String SAVED_TEXT = "test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_kid);
        date = findViewById(R.id.dateOfBirth);
        findView();
        Firebase.setAndroidContext(this);
        ArrayAdapter<String> adapterBlood = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataBlood);
        adapterBlood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinnerBlood = findViewById(R.id.spinnerBlood);
        spinnerBlood.setAdapter(adapterBlood);
        spinnerBlood.setPrompt("Title");

        spinnerBlood.setSelection(0);


        spinnerBlood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int positionBlood, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Title");

        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        addKid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                widthKid = width.getText().toString();//
                lastNameKid = lastKid.getText().toString();
                firstNameKid = first.getText().toString();//
                cityKid = city.getText().toString();//
                countryKid = country.getText().toString();//
                growthKid = growth.getText().toString();//
                diagnoseKid = diagnose.getText().toString();//
                dateKid = date.getText().toString();//


                if (firstNameKid.equals("")) {
                    first.setError("can't be blank");

                } else if (lastNameKid.equals("")) {
                    lastKid.setError("can't be blank");

                } else if (countryKid.equals("")) {
                    country.setError("can't be blank");

                } else if (cityKid.equals("")) {
                    city.setError("can't be blank");

                } else if (widthKid.equals("")) {
                    width.setError("can't be blank");
                } else if (dateKid.equals("")) {
                    date.setError("can't be blank");

                } else if (growthKid.equals("")) {
                    growth.setError("can't be blank");

                } else if (diagnoseKid.equals("")) {
                    diagnose.setError("can't be blank");
                } else {
                    final ProgressDialog pd = new ProgressDialog(ThirdStepOfRegistrationAddKid.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://ionkid-abd2f.firebaseio.com/users/kids.json";
                    final Firebase reference = new Firebase("https://ionkid-abd2f.firebaseio.com/users/" + UserDetails.username + "/kids");

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {


                            if (s.equals("null")) {

                                reference.child(lastNameKid).child("Blood Type").setValue("'Blood type is: " + spinnerBlood.getSelectedItemPosition());
                                reference.child(lastNameKid).child("Gender").setValue("'Value is: " + spinner.getSelectedItemPosition() + " '" + "if 1 it's Boy,if 2 it's Girl");
                                reference.child(lastNameKid).child("Width").setValue(widthKid);
                                try {
                                    decryptNameKid = EncryptAndDecryptData.encrypt(firstNameKid,UserDetails.SECRET_KEY);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                reference.child(lastNameKid).child("firstNameKid").setValue(decryptNameKid);

                               // reference.child(lastNameKid).child("firstNameKid").setValue(firstNameKid);
                                reference.child(lastNameKid).child("width").setValue(widthKid);

                                reference.child(lastNameKid).child("date").setValue(dateKid);
                                reference.child(lastNameKid).child("diagnose").setValue(diagnoseKid);

                                reference.child(lastNameKid).child("growth").setValue(growthKid);

                                reference.child(lastNameKid).child("country").setValue(countryKid);
                                reference.child(lastNameKid).child("city").setValue(cityKid);
                                if (!UserDetails.registerCheck.equals("0")) {

                                    UserDetails.registerCheck = "0";
                                    Toast.makeText(ThirdStepOfRegistrationAddKid.this, "registration successful", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(ThirdStepOfRegistrationAddKid.this, LoginWithFBAuth.class));
                                } else {
                                    UserDetails.kidName = lastNameKid;
                                    Toast.makeText(ThirdStepOfRegistrationAddKid.this, "registration successful", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(ThirdStepOfRegistrationAddKid.this, Menu.class));
                                }
                            } else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(lastNameKid)) {


                                        reference.child(lastNameKid).child("Gender").setValue("'Value is: " + spinner.getSelectedItemPosition() + " '" + "if 1 it's Boy,if 2 it's Girl");
                                        reference.child(lastNameKid).child("Width").setValue(widthKid);
                                        reference.child(lastNameKid).child("firstNameKid").setValue(firstNameKid);
                                        reference.child(lastNameKid).child("width").setValue(widthKid);
                                        reference.child(lastNameKid).child("Blood Type").setValue("'Blood type is: " + spinnerBlood.getSelectedItemPosition());
                                        reference.child(lastNameKid).child("date").setValue(dateKid);
                                        reference.child(lastNameKid).child("diagnose").setValue(diagnoseKid);
                                        reference.child(lastNameKid).child("growth").setValue(growthKid);


                                        reference.child(lastNameKid).child("country").setValue(countryKid);
                                        reference.child(lastNameKid).child("city").setValue(cityKid);
                                        Toast.makeText(ThirdStepOfRegistrationAddKid.this, "registration successful", Toast.LENGTH_LONG).show();
                                        if (!UserDetails.registerCheck.equals("0")) {
                                            UserDetails.kidName = lastNameKid;
                                            UserDetails.registerCheck = "0";
                                        }

                                        startActivity(new Intent(ThirdStepOfRegistrationAddKid.this, Menu.class));
                                    } else {
                                        Toast.makeText(ThirdStepOfRegistrationAddKid.this, "username already exists", Toast.LENGTH_LONG).show();
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

                    RequestQueue rQueue = Volley.newRequestQueue(ThirdStepOfRegistrationAddKid.this);
                    rQueue.add(request);
                }

            }
        });
    }

    public void onclick(View view) {
        showDialog(DIALOG_DATE);
    }


    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            date.setText(myDay + "/" + myMonth + "/" + myYear);
        }
    };

    public void findView() {

        width = findViewById(R.id.weidth);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        growth = findViewById(R.id.growth);
        diagnose = findViewById(R.id.diagnose);

        first = findViewById(R.id.firstName);
        lastKid = findViewById(R.id.lastKidName);

        addKid = findViewById(R.id.addKid);
    }


}
