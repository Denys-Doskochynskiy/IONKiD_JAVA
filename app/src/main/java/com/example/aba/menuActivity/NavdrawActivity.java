package com.example.aba.menuActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import com.example.aba.R;
import com.example.aba.kids.KidList;
import com.example.aba.task.TaskList;
import com.example.aba.users.LoginWithFBAuth;
import com.example.aba.users.UserDetails;
import com.example.aba.users.UsersList;
import com.firebase.client.Firebase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

public class NavdrawActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected FrameLayout frameLayout;

SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navdraw);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        floatingActionButton();
        drawerLayoutAndToolbar();
        navigationView();


    }

    public void floatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

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

    public void navigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (frameLayout != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            View stubView = inflater.inflate(layoutResID, frameLayout, false);
            frameLayout.addView(stubView, lp);
        }
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        if (frameLayout != null) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            frameLayout.addView(view, lp);
        }
        super.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (frameLayout != null) {
            frameLayout.addView(view, params);
        }
        super.setContentView(view, params);
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
        getMenuInflater().inflate(R.menu.navdraw, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            UserDetails.registerCheck="1";
            UserDetails.kidName="";
            sp.edit().putBoolean("loggeded",false).apply();
            final ProgressDialog pd = new ProgressDialog(NavdrawActivity.this);
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


                        Toast.makeText(NavdrawActivity.this, "Device is deactivated", Toast.LENGTH_SHORT).show();

                    } else {
                        try {
                            JSONObject obj = new JSONObject(s);

                            if (!obj.has(UserDetails.username)) {

                                reference.child("Activate").setValue("0");
                                reference.child("User").setValue("");


                                Toast.makeText(NavdrawActivity.this, "Device is deactivated", Toast.LENGTH_SHORT).show();

                            } else {
                                reference.child("Activate").setValue("0");

                                Toast.makeText(NavdrawActivity.this, "device is deactivated", Toast.LENGTH_LONG).show();
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

            RequestQueue rQueue = Volley.newRequestQueue(NavdrawActivity.this);
            rQueue.add(request);
            Intent intent = new Intent(this, LoginWithFBAuth.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_doctor) {
            startActivity(new Intent(NavdrawActivity.this, Doctors.class));
        } else if (id == R.id.nav_kids) {
            startActivity(new Intent(NavdrawActivity.this, KidList.class));
        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(NavdrawActivity.this, UsersList.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(NavdrawActivity.this, Settings.class));
        } else if (id == R.id.nav_taskaktivityday) {
            startActivity(new Intent(NavdrawActivity.this, TaskList.class));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
