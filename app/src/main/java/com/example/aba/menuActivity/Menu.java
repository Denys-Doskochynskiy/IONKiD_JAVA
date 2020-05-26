package com.example.aba.menuActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.aba.R;
import com.example.aba.kids.KidInfo;
import com.example.aba.kids.KidList;
import com.example.aba.task.day.DayTaskActivity;
import com.example.aba.users.Login;
import com.example.aba.users.UserDetails;
import com.example.aba.users.UsersList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        floatingActionButton();
        drawerLayoutAndToolbar();
        navigationView();

        findViewAndClickListener();


    }


    public void findViewAndClickListener() {
        Button taskActivity = findViewById(R.id.taskActivity);
        Button message = findViewById(R.id.messageActivity);
        Button kids = findViewById(R.id.kidsActivity);
        Button doctors = findViewById(R.id.doctorsActivity);
        Button settings = findViewById(R.id.settingsActivity);
        //Button profil = findViewById(R.id.profil);

        taskActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTask();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskSettings();
            }
        });

        kids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskKids();
            }
        });

        doctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskDoctors();
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskMessage();
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


    public void openTask() {
        Intent intent = new Intent(this, DayTaskActivity.class);
        startActivity(intent);
    }



    public void openTaskSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void openTaskKids() {
        Intent intent = new Intent(this, KidInfo.class);
        startActivity(intent);
    }

    public void openTaskMessage() {
        Intent intent = new Intent(this, UsersList.class);
        startActivity(intent);
    }

    public void openTaskDoctors() {
        Intent intent = new Intent(this, Doctors.class);
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
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            UserDetails.kidName="";
            UserDetails.registerCheck="1";
            sp.edit().putBoolean("loggeded",false).apply();
            Intent intent = new Intent(this, Login.class);
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
            startActivity(new Intent(Menu.this, Doctors.class));
        } else if (id == R.id.nav_kids) {
            startActivity(new Intent(Menu.this, KidList.class));
        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(Menu.this, UsersList.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(Menu.this, Settings.class));
        } else if (id == R.id.nav_taskaktivityday) {
            startActivity(new Intent(Menu.this, DayTaskActivity.class));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}