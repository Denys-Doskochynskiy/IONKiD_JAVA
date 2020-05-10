package com.example.aba;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class TestComment extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnClickListener {

    final String LOG_TAG = "myLogs";

    Button btnAdd, btnMenu;
    EditText editComment;

    Register.DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_comment);
        floatingActionButton();
        drawerLayoutAndToolbar();
        navigationView();

        findView();

        dbHelper = new Register.DBHelper(this);
    }

    public void findView() {
        btnAdd = findViewById(R.id.btnReadAndAdd);
        btnAdd.setOnClickListener(this);
        btnMenu = findViewById(R.id.btnBackMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        editComment = findViewById(R.id.editComment);
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

        getMenuInflater().inflate(R.menu.test_comment, menu);
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
            startActivity(new Intent(TestComment.this, Doctors.class));
        } else if (id == R.id.nav_kids) {
            startActivity(new Intent(TestComment.this, Kids.class));
        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(TestComment.this, Users.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(TestComment.this, Settings.class));
        } else if (id == R.id.nav_taskaktivityday) {
            startActivity(new Intent(TestComment.this, TaskActivityDay.class));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openMenu() {
        Intent intent = new Intent(this, com.example.aba.Menu.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {


        ContentValues cv = new ContentValues();


        String comment = editComment.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();


        switch (v.getId()) {
            case R.id.btnReadAndAdd:
                Log.d(LOG_TAG, "--- Insert in mytableIONKidV3OnliComment: ---");

                cv.put("comment", comment);


                long rowID = db.insert("mytableIONKidV3", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                Log.d(LOG_TAG, "--- Rows in mytableIONKidV3OnliComment: ---");

                Cursor c = db.query("mytableIONKidV3", null, null, null, null, null, null);


                if (c.moveToFirst()) {


                    int commentColIndex = c.getColumnIndex("comment");
                    do {

                        Log.d(LOG_TAG,
                                ", Comment = " + c.getString(commentColIndex)

                        );

                    } while (c.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                c.close();

        }

        dbHelper.close();
    }

}
