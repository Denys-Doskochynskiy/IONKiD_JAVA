package com.example.aba;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import android.view.View.OnClickListener;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class TestComment extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnClickListener {

    final String LOG_TAG = "myLogs";

    Button btnAdd, btnMenu;
    EditText editComment;

    Reg.DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskMessage();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        btnAdd = (Button) findViewById(R.id.btnReadAndAdd);
        btnAdd.setOnClickListener(this);
        btnMenu = (Button) findViewById(R.id.btnBackMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        editComment = (EditText) findViewById(R.id.editComment);


        // создаем объект для создания и управления версиями БД
        dbHelper = new Reg.DBHelper(this);
    }

    public void openTaskMessage() {
        Intent intent = new Intent(this, Chat.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_comment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_doctor) {
            startActivity(new Intent( TestComment.this, Doctors.class));
        } else if (id == R.id.nav_kids) {
            startActivity(new Intent( TestComment.this, Kids.class));
        } else if (id == R.id.nav_chat) {
            startActivity(new Intent( TestComment.this, Chat.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent( TestComment.this, Settings.class));
        } else if (id == R.id.nav_taskaktivityday) {
            startActivity(new Intent( TestComment.this, TaskActivityDay.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openMenu() {
        Intent intent = new Intent(this, com.example.aba.Menu.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        String comment = editComment.getText().toString();
        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        switch (v.getId()) {
            case R.id.btnReadAndAdd:
                Log.d(LOG_TAG, "--- Insert in mytableIONKidV3OnliComment: ---");
                // подготовим данные для вставки в виде пар: наименование столбца - значение
                cv.put("comment", comment);

                // вставляем запись и получаем ее ID
                long rowID = db.insert("mytableIONKidV3", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                Log.d(LOG_TAG, "--- Rows in mytableIONKidV3OnliComment: ---");
                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db.query("mytableIONKidV3", null, null, null, null, null, null);

                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                if (c.moveToFirst()) {

                    // определяем номера столбцов по имени в выборке
                    int commentColIndex = c.getColumnIndex("comment");
                    do {
                        // получаем значения по номерам столбцов и пишем все в лог
                        Log.d(LOG_TAG,
                                ", Comment = " + c.getString(commentColIndex)

                        ) ;
                        // переход на следующую строку
                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while (c.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                c.close();

        }
        // закрываем подключение к БД
        dbHelper.close();
    }

}
