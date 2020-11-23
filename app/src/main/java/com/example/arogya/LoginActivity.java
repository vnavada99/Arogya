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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    public EditText emailId, password;
    Button signIn;
    TextView register;
    FirebaseAuth myFirebaseAuth;
    private FirebaseAuth.AuthStateListener myAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.emailId);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);
        register = findViewById(R.id.register1);

        myAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser myFirebaseUser = myFirebaseAuth.getCurrentUser();
                if(myFirebaseAuth != null){
                    Toast.makeText(LoginActivity.this,"You are logged in!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_SHORT);
                }
            }
        };
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Enter Details",Toast.LENGTH_SHORT).show();
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
                    myFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Error Logging In",Toast.LENGTH_SHORT);
                            }
                            else{
                                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT);
                                Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this,"Some Error occurred!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
    /*protected void onStart(){
        super.onStart();
        myFirebaseAuth.addAuthStateListener(myAuthStateListener);
    }
    */

}