package com.example.arogya;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class HomeActivity extends AppCompatActivity {
    ImageView logOut,uploadImg,getHosLoc,profile,callDoctor;
    FirebaseAuth myFirebaseAuth;
    private FirebaseAuth.AuthStateListener myAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logOut = findViewById(R.id.logOut);
        uploadImg = findViewById(R.id.uploadImg);
        getHosLoc = findViewById(R.id.getHosLoc);
        profile = findViewById(R.id.profile);
        callDoctor = findViewById(R.id.callDoctor);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent sig = new Intent(HomeActivity.this,MainActivity.class);
                finish();
                startActivity(sig);
            }
        });

        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(camera_intent);
            }
        });

        getHosLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,HosLocation.class);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,getMyUser.class));
            }
        });
        callDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,callDoc.class));
            }
        });
    }
}