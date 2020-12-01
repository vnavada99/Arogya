package com.example.arogya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class callDoc extends AppCompatActivity {
    FirebaseAuth myFirebaseAuth;
    String docP;
    DatabaseReference myRef;
    ImageView callNum;
    TextView docN,docNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_doc);

        callNum = findViewById(R.id.callNum);
        docN = findViewById(R.id.docN);
        docNumber = findViewById(R.id.docNumber);
        myFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser myFirebaseUser = myFirebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(myFirebaseUser.getUid().toString());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("docName").getValue().toString();
                docP = snapshot.child("docPhone").getValue().toString();
                docN.setText(name);
                docNumber.setText(docP);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        callNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+docP));
                startActivity(i);
            }
        });
    }
}