package com.example.arogya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    public EditText emailId, password;
    Button signUp;
    TextView register;
    FirebaseAuth myFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.emailId);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.signIn);
        register = findViewById(R.id.register1);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Enter Details",Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    emailId.setError("Please Enter Email Id");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please fill in the password");
                    password.requestFocus();
                }

                else if(!(email.isEmpty() && pwd.isEmpty())){
                    myFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "SignUp Unsuccessful, Please try again", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(MainActivity.this,"Sign Up Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Some Error occurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }
}