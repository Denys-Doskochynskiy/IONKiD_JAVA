package com.example.aba.menuActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.aba.R;
import com.example.aba.task.TaskActivity;
import com.example.aba.kids.Kids;
import com.example.aba.users.UserDetails;
import com.example.aba.users.Users;
import com.firebase.client.Firebase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class Doctors extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RatingBar ratingbar;
    Button submit;
    String rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        drawerLayoutAndToolbar();

        floatingActionButton();

        navigationView();

        addListenerOnButtonClick();

    }

    public void addListenerOnButtonClick(){
        ratingbar=(RatingBar)findViewById(R.id.ratingBar);
        submit=(Button)findViewById(R.id.button);
        //Performing action on Button Click
        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                //Getting the rating and displaying it on the toast
                rating = String.valueOf(ratingbar.getRating());
                rating = rating.replace(".",",");
                // addition firebase
                final ProgressDialog pd = new ProgressDialog(Doctors.this);
                pd.setMessage("Loading...");
                pd.show();

                String url = "https://ionkid-abd2f.firebaseio.com/users/doctors.json";

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Firebase reference = new Firebase("https://ionkid-abd2f.firebaseio.com/users/" + UserDetails.username + "/doctors");

                        if (s.equals("null")) {
                            reference.child(rating).child("fatherEmail").setValue(UserDetails.username);
                            UserDetails.ratingDoc = rating;
                            Toast.makeText(Doctors.this, "rated", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Doctors.this, Menu.class));
                        } else {
                            try {
                                JSONObject obj = new JSONObject(s);

                                if (!obj.has(rating)) {
                                    reference.child(rating).child("fatherEmail").setValue(UserDetails.username);
                                    UserDetails.ratingDoc = rating;
                                    startActivity(new Intent(Doctors.this, Menu.class));
                                } else {
                                    Toast.makeText(Doctors.this, "rating has already been given ", Toast.LENGTH_LONG).show();
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
            }
        });
    }

    public void floatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskMessage();
            }
        });
    }

    public void navigationView() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void drawerLayoutAndToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void openTaskMessage() {
        Intent intent = new Intent(this, Users.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.doctors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_doctor) {
            startActivity(new Intent(Doctors.this, Doctors.class));
        } else if (id == R.id.nav_kids) {
            startActivity(new Intent(Doctors.this, Kids.class));
        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(Doctors.this, Users.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(Doctors.this, Settings.class));
        } else if (id == R.id.nav_taskaktivityday) {
            startActivity(new Intent(Doctors.this, TaskActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
