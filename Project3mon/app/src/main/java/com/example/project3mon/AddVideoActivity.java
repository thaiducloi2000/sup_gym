package com.example.project3mon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.project3mon.dao.VideoDAO;
import com.example.project3mon.dto.Video;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.sql.SQLException;
import java.util.HashMap;

public class AddVideoActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private EditText edtTitle;
    private VideoView videoView;
    private Button btnUploadVideo;
    private FloatingActionButton pickVideoTab;

    private static final int VIDEO_PICK_GALLERY_CODE = 100;
    private static final int VIDEO_PICK_CAMERA_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 102;

    private String[] cameraPermissions;
    private Uri videoUri;
    private String title;

    private ProgressDialog progressDialog;
    private Bundle bundle;
    private boolean check = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);

//        actionBar = getSupportActionBar();
//        actionBar.setTitle("Add New Video");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);

        edtTitle = findViewById(R.id.edtTitle);
        videoView = findViewById(R.id.videoVidew);
        btnUploadVideo = findViewById(R.id.btnUploadVideo);
        pickVideoTab = findViewById(R.id.pickVideoTab);

        bundle = getIntent().getExtras();
        if (bundle == null) {

        }
        try {
            Video video = (Video) bundle.get("video");
            if (video.getVideoUrl() != null || video.getVideoUrl() != "") {
                btnUploadVideo.setText("Edit Video");
                edtTitle.setText(video.getVideoName());

                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoView);

                Uri uri = Uri.parse(video.getVideoUrl());
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.start();
            }
        } catch (Exception e) {
            check = false;
        }

        // setup progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Vui Lòng Chờ");
        progressDialog.setMessage("Đang Đăng Tải Video");
        progressDialog.setCanceledOnTouchOutside(false);

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        btnUploadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = edtTitle.getText().toString().trim();
                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(AddVideoActivity.this, "Vui Lòng Nhập Tiêu Đề....", Toast.LENGTH_SHORT).show();
                }
                if (videoUri == null) {
                    Toast.makeText(AddVideoActivity.this, "Vui lòng chọn video trước khi đăng....", Toast.LENGTH_SHORT).show();
                } else {
                    uploadVideoToFirebase();
                }
            }
        });

        pickVideoTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoPickDialog();
            }
        });

    }

    private void uploadVideoToFirebase() {
        progressDialog.show();
        VideoDAO dao = new VideoDAO();

        String timestamp = "" + System.currentTimeMillis();
        String filePathName = "Videos/" + "video_" + timestamp;

        StorageReference storageReference = FirebaseStorage
                .getInstance("gs://supgym-fd72d.appspot.com")
                .getReference(filePathName);

        storageReference.putFile(videoUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful()) ;
                        Uri dowloadUri = uriTask.getResult();
                        if (uriTask.isSuccessful()) {
                            // url of upload video has received
                            // now add detail in to firebase database

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id", timestamp);
                            hashMap.put("title", title);
                            hashMap.put("timestamp", timestamp + "");
                            hashMap.put("videoUrl", dowloadUri + "");

                            Video video =
                                    new Video(timestamp, title, dowloadUri + "", "exercice1", "icon_checkmark_green");
                            if (check) {
                                try {
                                    if (dao.AddNewVideo(video, 3)) {
                                        Toast.makeText(AddVideoActivity.this, "Upload To Database Success", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            } else {
                                try {
                                    if (dao.AddNewVideo(video, 4)) {
                                        Toast.makeText(AddVideoActivity.this, "Upload To Database Success", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }

                            DatabaseReference databaseReference =
                                    FirebaseDatabase
                                            .getInstance("https://supgym-fd72d-default-rtdb.asia-southeast1.firebasedatabase.app")
                                            .getReference("Videos");

                            databaseReference.child(timestamp)
                                    .setValue(hashMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            progressDialog.dismiss();
                                            Toast.makeText(AddVideoActivity.this, "Video Đã Được Đăng...", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressDialog.dismiss();
                                            Toast.makeText(AddVideoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to update to storage
                        progressDialog.dismiss();
                        Toast.makeText(AddVideoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void videoPickDialog() {
        String[] option = {"Camera", "Gallery"};

        // dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Video From").setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    // camera clicked
                    if (!checkCameraPermission()) {
                        requestCameraPermission();
                    } else {
                        videoPickCamera();
                    }
                } else if (i == 1) {
                    // gallery clicked
                    videoPickGallery();
                }
            }
        }).show();
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) == PackageManager.PERMISSION_GRANTED;

        return result1 && result2;
    }

    private void videoPickGallery() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"), VIDEO_PICK_GALLERY_CODE);
    }

    private void videoPickCamera() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, VIDEO_PICK_CAMERA_CODE);
    }

    private void setVideoToVideoView() {
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.pause();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0) {
                    // check permission allow or not
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && storageAccepted) {
                        videoPickCamera();
                    } else {
                        // both or 1 are denied
                        Toast.makeText(this, "Camera & Storage permission are required", Toast.LENGTH_SHORT).show();
                    }
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // called after picking video from camera or galery
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == VIDEO_PICK_GALLERY_CODE) {
                videoUri = data.getData();
                // show picked video on videoView
                setVideoToVideoView();
            } else if (requestCode == VIDEO_PICK_CAMERA_CODE) {
                videoUri = data.getData();
                // show picked video on videoView
                setVideoToVideoView();
            }
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}