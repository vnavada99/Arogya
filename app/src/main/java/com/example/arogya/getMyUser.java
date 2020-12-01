package com.example.arogya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class getMyUser extends AppCompatActivity {
    private TextView myUser,bloodGrp,displayMsg,hosLoc,hosName,docName,rbcCount;
    FirebaseAuth myFirebaseAuth;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_my_user);

        myUser = findViewById(R.id.myUser);
        bloodGrp = findViewById(R.id.bloodGrp);
        hosLoc = findViewById(R.id.hosLoc);
        hosName = findViewById(R.id.hosName);
        docName = findViewById(R.id.docName);
        rbcCount = findViewById(R.id.rbcCount);
        myFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser myFirebaseUser = myFirebaseAuth.getCurrentUser();
        displayMsg = findViewById(R.id.displayMsg);

        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(myFirebaseUser.getUid().toString());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue().toString();
                String bg = snapshot.child("bloodGrp").getValue().toString();
                String docN = snapshot.child("docName").getValue().toString();
                String hosL = snapshot.child("hosLoc").getValue().toString();
                String hosN = snapshot.child("hosName").getValue().toString();
                String rbcC = snapshot.child("rbcCount").getValue().toString();
                myUser.setText(name);
                bloodGrp.setText(bg);
                hosLoc.setText(hosL);
                hosName.setText(hosN);
                docName.setText(docN);
                rbcCount.setText(rbcC);
                displayMsg.setText("Welcome "+snapshot.child("name").getValue().toString()+", your medical details are");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}