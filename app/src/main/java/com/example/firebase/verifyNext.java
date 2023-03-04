package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class verifyNext extends AppCompatActivity {
    EditText inputNo1,inputNo2,inputNo3,inputNo4,inputNo5,inputNo6;
    Button submit;
    String getotpbackend;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_next);

        submit=findViewById(R.id.submit);
        inputNo1=findViewById(R.id.box1);
        inputNo2=findViewById(R.id.box2);
        inputNo3=findViewById(R.id.box3);
        inputNo4=findViewById(R.id.box4);
        inputNo5=findViewById(R.id.box5);
        inputNo6=findViewById(R.id.box6);

        TextView textView=findViewById(R.id.textmobileshownumber);
        textView.setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
        ));
           getotpbackend=getIntent().getStringExtra("backendotp");

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!inputNo1.getText().toString().trim().isEmpty() && !inputNo2.getText().toString().trim().isEmpty() &&
                       !inputNo3.getText().toString().trim().isEmpty() && !inputNo4.getText().toString().trim().isEmpty()
                         &&!inputNo5.getText().toString().trim().isEmpty() && !inputNo6.getText().toString().trim().isEmpty()){
                   String entercodeotp=inputNo1.getText().toString()+
                           inputNo2.getText().toString()+
                           inputNo3.getText().toString()+
                           inputNo4.getText().toString()+
                           inputNo5.getText().toString()+
                           inputNo6.getText().toString();
                   if(getotpbackend!=null){
                       PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                               getotpbackend,entercodeotp
                       );
                       FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                               .addOnCompleteListener(task -> {
                                   if(task.isSuccessful()){
                                       Toast.makeText(verifyNext.this, "WELCOME", Toast.LENGTH_SHORT).show();
                                       Intent i=new Intent(verifyNext.this,next.class);
                                       i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                       startActivity(i);
                                   }else{
                                       Toast.makeText(verifyNext.this, "Enter the correct otp", Toast.LENGTH_SHORT).show();
                                   }
                               });
                   }else{
                       Toast.makeText(verifyNext.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                   }
               }
               else{
                   Toast.makeText(verifyNext.this, "Please enter all number", Toast.LENGTH_SHORT).show();
               }
           }
       });
       numberotpmove();

       findViewById(R.id.resend).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

                       PhoneAuthProvider.getInstance().verifyPhoneNumber(
                               "+91" +getIntent().getStringExtra("mobile"),
                               60,
                               TimeUnit.SECONDS,
                               verifyNext.this,
                               new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                   @Override
                                   public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                   }

                                   @Override
                                   public void onVerificationFailed(@NonNull FirebaseException e) {
                                       Toast.makeText(verifyNext.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                   }

                                   @Override
                                   public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                       getotpbackend=newbackendotp;
                                       Toast.makeText(verifyNext.this, "OTP send successful", Toast.LENGTH_SHORT).show();
                                   }
                               }
                       );
           }
       });
    }

    private void numberotpmove() {
        inputNo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              if(!s.toString().trim().isEmpty()){
                  inputNo2.requestFocus();
              }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputNo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              if(!s.toString().trim().isEmpty()){
                  inputNo3.requestFocus();
              }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputNo3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              if(!s.toString().trim().isEmpty()){
                  inputNo4.requestFocus();
              }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputNo4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputNo5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputNo5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputNo6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}