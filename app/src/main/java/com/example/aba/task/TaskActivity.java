package com.example.aba.task;

import android.content.Intent;
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
import com.example.aba.kids.Kids;
import com.example.aba.menuActivity.Doctors;
import com.example.aba.menuActivity.Settings;
import com.example.aba.task.day.TaskActivityDay;
import com.example.aba.task.day.TaskActivityDay2;
import com.example.aba.task.day.TaskActivityDay3;
import com.example.aba.task.day.TaskActivityDay4;
import com.example.aba.task.day.TaskActivityDay5;
import com.example.aba.task.day.TaskActivityDay6;
import com.example.aba.task.day.TaskActivityDay7;
import com.example.aba.users.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class TaskActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);
        floatingActionButton();
        drawerLayoutAndToolbar();
        navigationView();
        Button mondayBar = findViewById(R.id.mondayBar);

        mondayBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMondayBar();
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

        Button mondayBar = findViewById(R.id.mondayBar);
        Button tuesdayBar = findViewById(R.id.tuesdayBar);
        Button wednesdayBar = findViewById(R.id.wednesdayBar);
        Button thursdayBar = findViewById(R.id.thursdayBar);
        Button fridayBar = findViewById(R.id.fridayBar);
        Button saturdayBar = findViewById(R.id.saturdayBar);
        Button sundayBar = findViewById(R.id.sundayBar);

        mondayBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMondayBar();
            }
        });
        tuesdayBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTuesdayBar();
            }
        });
        wednesdayBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWednesdayBar();
            }
        });
        thursdayBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openthursdayBar();
            }
        });
        fridayBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFridayBar();
            }
        });
        saturdayBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSaturdayBar();
            }
        });
        sundayBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSundayBar();
            }
        });

    }

    public void openTaskMessage() {
        Intent intent = new Intent(this, Users.class);
        startActivity(intent);
    }

    public void openMondayBar() {
        Intent intent = new Intent(this, TaskActivityDay.class);
        startActivity(intent);
    }

    public void openTuesdayBar() {
        Intent intent = new Intent(this, TaskActivityDay2.class);
        startActivity(intent);
    }

    public void openWednesdayBar() {
        Intent intent = new Intent(this, TaskActivityDay3.class);
        startActivity(intent);
    }

    public void openthursdayBar() {
        Intent intent = new Intent(this, TaskActivityDay4.class);
        startActivity(intent);
    }

    public void openFridayBar() {
        Intent intent = new Intent(this, TaskActivityDay5.class);
        startActivity(intent);
    }

    public void openSaturdayBar() {
        Intent intent = new Intent(this, TaskActivityDay6.class);
        startActivity(intent);
    }

    public void openSundayBar() {
        Intent intent = new Intent(this, TaskActivityDay7.class);
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
        getMenuInflater().inflate(R.menu.task_activity, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_doctor) {
            startActivity(new Intent(TaskActivity.this, Doctors.class));
        } else if (id == R.id.nav_kids) {
            startActivity(new Intent(TaskActivity.this, Kids.class));
        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(TaskActivity.this, Users.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(TaskActivity.this, Settings.class));
        } else if (id == R.id.nav_taskaktivityday) {

            startActivity(new Intent(TaskActivity.this, TaskActivity.class));


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
