package com.example.aba.menuActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.aba.R;
import com.example.aba.users.LoginWithFBAuth;
import com.example.aba.users.registration.ThirdStepOfRegistrationAddKid;
import com.example.aba.task.day.DayTaskActivity;
import com.example.aba.unimplementedORunused.Login;
import com.example.aba.users.UserDetails;
import com.example.aba.users.UserPersonalData;
import com.example.aba.users.UsersList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Settings extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        floatingActionButton();
        drawerLayoutAndToolbar();
        navigationView();

        Button personalDataActivity = findViewById(R.id.personalData);
        personalDataActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPersonalData();
            }
        });

        Button exit = findViewById(R.id.exitFromAccount) ;
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });

    }
    public void openPersonalData() {
        Intent intent = new Intent(this, UserPersonalData.class);
        startActivity(intent);
    }
    public void exit() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
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
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void openTaskMessage() {
        Intent intent = new Intent(this, UsersList.class);
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

        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            UserDetails.registerCheck="1";
            UserDetails.kidName="";
            sp.edit().putBoolean("loggeded",false).apply();
            Intent intent = new Intent(this, LoginWithFBAuth.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_doctor) {
            startActivity(new Intent(Settings.this, Doctors.class));
        } else if (id == R.id.nav_kids) {
            startActivity(new Intent(Settings.this, ThirdStepOfRegistrationAddKid.class));
        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(Settings.this, UsersList.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(Settings.this, Settings.class));
        } else if (id == R.id.nav_taskaktivityday) {
            startActivity(new Intent(Settings.this, DayTaskActivity.class));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
