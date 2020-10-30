/*
 *
 *   Created Your Name on 30.10.20 15:32
 *   Copyright Ⓒ 2020. All rights reserved Ⓒ 2020 http://freefuninfo.com/
 *   Last modified: 30.10.20 14:29
 *
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 *   except in compliance with the License. You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENS... Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *    either express or implied. See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package com.example.aba.menuActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.aba.skip.SkipData;
import com.example.aba.task.TaskList;
import com.example.aba.task.UploadVideo;
import com.example.aba.users.LoginWithFBAuth;
import com.example.aba.users.registration.SecondStepOfRegistration;
import com.example.aba.users.registration.ThirdStepOfRegistrationAddKid;
import com.example.aba.unimplementedOrUnused.Login;
import com.example.aba.users.UserDetails;
import com.example.aba.users.UserPersonalData;
import com.example.aba.users.UsersList;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.UUID;

public class Settings extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
SharedPreferences sp;
ImageView profImg;
public Uri imageUri;
private FirebaseStorage storage;
private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        findView();
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

        Button sensorKitActivity= findViewById(R.id.sensorKitActivity);
       sensorKitActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorKit();
            }
        });
        Button exit = findViewById(R.id.exitFromAccount) ;
        final Button uploadVideo = findViewById(R.id.uploadVideo);
        uploadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUploadVideo();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });

        profImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosePicture();
            }
        });

    }
    void goToUploadVideo(){
        Intent intent = new Intent(this, UploadVideo.class);
        startActivity(intent);
    }
    void chosePicture(){
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK && data!=null&&data.getData()!=null){
            imageUri =data.getData();
            UserDetails.photoKey=imageUri;
            profImg.setImageURI(UserDetails.photoKey);
            uploadPicture();
        }
    }

    private void uploadPicture() {
        final ProgressDialog pd= new ProgressDialog(this);
        pd.setTitle("Uploading Image... ");
        pd.show();
        final String randomKey= UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/"+randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(Settings.this, "Image uploaded success"
                                , Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(Settings.this, "Image uploaded fail"
                                , Toast.LENGTH_LONG).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progressPercent= (100.00*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                pd.setMessage("Percentage: "+(int)progressPercent+";");
            }
        });
    }

    void findView(){
        profImg= findViewById(R.id.avatar);
    }
    public void openPersonalData() {
        Intent intent = new Intent(this, UserPersonalData.class);
        startActivity(intent);
    }
    public void sensorKit() {
        Intent intent = new Intent(this, SkipData.class);
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
            final ProgressDialog pd = new ProgressDialog(Settings.this);
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


                        Toast.makeText(Settings.this, "Device is deactivated", Toast.LENGTH_SHORT).show();

                    } else {
                        try {
                            JSONObject obj = new JSONObject(s);

                            if (!obj.has(UserDetails.username)) {

                                reference.child("Activate").setValue("0");
                                reference.child("User").setValue("");


                                Toast.makeText(Settings.this, "Device is deactivated", Toast.LENGTH_SHORT).show();

                            } else {
                                reference.child("Activate").setValue("0");

                                Toast.makeText(Settings.this, "device is deactivated", Toast.LENGTH_LONG).show();
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

            RequestQueue rQueue = Volley.newRequestQueue(Settings.this);
            rQueue.add(request);

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
            startActivity(new Intent(Settings.this, TaskList.class));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
