package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPverify extends AppCompatActivity {
    EditText editTextPhone;
    Button getOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverify);
        editTextPhone=findViewById(R.id.editTextPhone);
        getOTP=findViewById(R.id.getOTP);
        final ProgressBar progressBar=findViewById(R.id.progressbar);

        getOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextPhone.getText().toString().trim().isEmpty()){
                   if((editTextPhone.getText().toString().trim()).length()==10){
                       progressBar.setVisibility(View.VISIBLE);
                       getOTP.setVisibility(View.INVISIBLE);

                       PhoneAuthProvider.getInstance().verifyPhoneNumber(
                               "+91" + editTextPhone.getText().toString(),
                               60,
                               TimeUnit.SECONDS,
                               OTPverify.this,
                               new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                   @Override
                                   public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                      progressBar.setVisibility(View.GONE);
                                      getOTP.setVisibility(View.VISIBLE);
                                   }

                                   @Override
                                   public void onVerificationFailed(@NonNull FirebaseException e) {
                                       progressBar.setVisibility(View.GONE);
                                       getOTP.setVisibility(View.VISIBLE);
                                       Toast.makeText(OTPverify.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                   }

                                   @Override
                                   public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                       progressBar.setVisibility(View.GONE);
                                       getOTP.setVisibility(View.VISIBLE);
                                       Intent i= new Intent(OTPverify.this,verifyNext.class);
                                       i.putExtra("mobile",editTextPhone.getText().toString());
                                       i.putExtra("backendotp",backendotp);
                                       startActivity(i);
                                   }
                               }
                       );
                   }else {
                       Toast.makeText(OTPverify.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                   }
                }else {
                    Toast.makeText(OTPverify.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}