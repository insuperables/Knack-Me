package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
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
import com.shashank.sony.fancytoastlib.FancyToast;

public class signInAndSignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editEmail,editPassword;
    private Button btnSignUP;
    TextView signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_and);
        editEmail=findViewById(R.id.editEmail1);
        editPassword=findViewById(R.id.editPass2);
        btnSignUP=findViewById(R.id.signUP);
        signIn=findViewById(R.id.signIn);
        mAuth=FirebaseAuth.getInstance();
        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUP();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


    }
    @Override
    protected void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null) {
            // Transition To Next Activity
            trasitionToKnackMe();

        }
        }
     private void signUP(){
         mAuth.createUserWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if (task.isSuccessful()) {
                     // Sign in success, update UI with the signed-in user's information
                     FancyToast.makeText(signInAndSignUp.this,"Authentication Successful",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                     FirebaseUser user = mAuth.getCurrentUser();
                     trasitionToKnackMe();

                 } else {
                     // If sign in fails, display a message to the user.

                     FancyToast.makeText(signInAndSignUp.this,"Authentication Error",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();

                 }
             }
         });

     }
     private void signIn(){
        mAuth.signInWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FancyToast.makeText(signInAndSignUp.this,"SignIN Successful",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                    trasitionToKnackMe();

                }else{
                    FancyToast.makeText(signInAndSignUp.this,"SignIN Fail ",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();

                }


            }
        });
     }
     private void trasitionToKnackMe(){
         Intent intent=new Intent(this ,MainActivity.class);
         startActivity(intent);
     }
    }

