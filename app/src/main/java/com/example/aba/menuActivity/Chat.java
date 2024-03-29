package com.example.aba.menuActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.aba.R;
import com.example.aba.task.TaskActivity;
import com.example.aba.kids.Kids;
import com.example.aba.users.UserDetails;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

public class Chat extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        LinearLayout layout;
        RelativeLayout layout_2;
        ImageView sendButton;
        EditText messageArea;
        ScrollView scrollView;
        Firebase reference1, reference2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chat);

            drawerLayoutAndToolbar();
            navigationView();

            layout = findViewById(R.id.layout1);
            layout_2 = findViewById(R.id.layout2);
            sendButton = findViewById(R.id.sendButton);
            messageArea = findViewById(R.id.messageArea);
            scrollView = findViewById(R.id.scrollView);

            Firebase.setAndroidContext(this);
            reference1 = new Firebase("https://ionkid-abd2f.firebaseio.com/messages/" + UserDetails.username + "_" + UserDetails.chatWith);
            reference2 = new Firebase("https://ionkid-abd2f.firebaseio.com/messages/" + UserDetails.chatWith + "_" + UserDetails.username);

            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String messageText = messageArea.getText().toString();

                    if(!messageText.equals("")){
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("message", messageText);
                        map.put("user", UserDetails.username);
                        reference1.push().setValue(map);
                        reference2.push().setValue(map);
                        messageArea.setText("");
                    }
                }
            });

            reference1.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Map map = dataSnapshot.getValue(Map.class);
                    String message = map.get("message").toString();
                    String userName = map.get("user").toString();

                    if(userName.equals(UserDetails.username)){
                        addMessageBox(message, 1);
                    }
                    else{
                        addMessageBox(message, 2);
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }

        public void addMessageBox(String message, int type){
            TextView textView = new TextView(Chat.this);
            textView.setText(message);

            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp2.weight = 7.0f;

            if(type == 1) {
                lp2.gravity = Gravity.LEFT;
                textView.setBackgroundResource(R.drawable.bubble_in);
            }
            else{
                lp2.gravity = Gravity.RIGHT;
                textView.setBackgroundResource(R.drawable.bubble_out);
            }
            textView.setLayoutParams(lp2);
            layout.addView(textView);
            scrollView.fullScroll(View.FOCUS_DOWN);
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
        getMenuInflater().inflate(R.menu.chat, menu);
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
            startActivity(new Intent(Chat.this, Doctors.class));
        } else if (id == R.id.nav_kids) {
            startActivity(new Intent(Chat.this, Kids.class));
        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(Chat.this, Chat.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(Chat.this, Settings.class));
        } else if (id == R.id.nav_taskaktivityday) {
            startActivity(new Intent(Chat.this, TaskActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
