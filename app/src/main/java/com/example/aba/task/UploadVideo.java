/*
 *
 *   Created Your Name on 30.10.20 15:32
 *   Copyright Ⓒ 2020. All rights reserved Ⓒ 2020 http://freefuninfo.com/
 *   Last modified: 30.10.20 15:31
 *
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 *   except in compliance with the License. You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENS... Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *    either express or implied. See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package com.example.aba.task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aba.R;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


        import android.content.Intent;
        import android.net.Uri;
        import android.view.View;

        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.MediaController;
        import android.widget.ProgressBar;
        import android.widget.Toast;
        import android.widget.VideoView;

        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;

        import com.google.firebase.storage.FirebaseStorage;
        import com.google.firebase.storage.StorageReference;

        import com.google.firebase.storage.UploadTask;


public class UploadVideo extends AppCompatActivity {


    private static final int PICK_VIDEO_REQUEST = 1;


    private Button choosebtn;
    private Button uploadbtn;
    private   ProgressBar progressBar;
    private VideoView videoView;
    private EditText videoname;
    private Uri videoUri;
    MediaController mediaController;
    private StorageReference mStorageRef;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video);


        choosebtn = findViewById(R.id.choose_btn);
        uploadbtn = findViewById(R.id.upload_btn);
        videoView = findViewById(R.id.video_view);
        progressBar = findViewById(R.id.progress_bar);
        videoname = findViewById(R.id.video_name);

        mediaController = new MediaController(this);

        mStorageRef = FirebaseStorage.getInstance().getReference("videos");


        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();


        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseVideo();
            }
        });

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadVideo();
            }
        });
    }


    private  void ChooseVideo(){
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_VIDEO_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            videoUri = data.getData();

            videoView.setVideoURI(videoUri);


        }}


    private void UploadVideo() {

        progressBar.setVisibility(View.VISIBLE);
        if (videoUri != null){
            String name =videoname.getText().toString();
            StorageReference reference = mStorageRef.child(
                    name);

            reference.putFile(videoUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(),"Upload successful",Toast.LENGTH_SHORT).show();


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });


        }else {
            Toast.makeText(getApplicationContext(),"No file selected",Toast.LENGTH_SHORT).show();
        }


    }}