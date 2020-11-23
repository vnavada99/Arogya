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
    private TextView myUser,bloodGrp,displayMsg;
    FirebaseAuth myFirebaseAuth;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_my_user);

        myUser = findViewById(R.id.myUser);
        bloodGrp = findViewById(R.id.bloodGrp);
        myFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser myFirebaseUser = myFirebaseAuth.getCurrentUser();
        displayMsg = findViewById(R.id.displayMsg);
        displayMsg.setText("Welcome "+myFirebaseUser.getEmail().toString()+", your medical details are");
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(myFirebaseUser.getUid().toString());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue().toString();
                String bg = snapshot.child("bloodGrp").getValue().toString();
                myUser.setText(name);
                bloodGrp.setText(bg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}