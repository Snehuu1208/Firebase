package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginForm extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button button4;
    private Button otp;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        editTextEmail=findViewById(R.id.editTextTextEmailAddress);
        editTextPassword=findViewById(R.id.editTextTextPassword);
        button4=findViewById(R.id.button4);
        otp=findViewById(R.id.otp);
        auth=FirebaseAuth.getInstance();
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editTextEmail.getText().toString();
                String password=editTextPassword.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginForm.this, "INVALID!", Toast.LENGTH_SHORT).show();
                }
                else {
                    login(email, password);
                }
            }
        });
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginForm.this, "Loading...", Toast.LENGTH_SHORT).show();
                Intent i =new Intent(LoginForm.this,OTPverify.class);
                startActivity(i);
            }
        });
    }
    private void login(String email,String password){
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(LoginForm.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginForm.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent i=new Intent (LoginForm.this,next.class);
                startActivity(i);
            }
        });
    }
}