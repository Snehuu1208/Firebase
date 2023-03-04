package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class RegisterForm extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button button3;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        editTextEmail=findViewById(R.id.editTextTextEmailAddress1);
        editTextPassword=findViewById(R.id.editTextTextPassword1);
        button3=findViewById(R.id.button3);
        auth=FirebaseAuth.getInstance();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
                Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

                String email=editTextEmail.getText().toString();
                String password=editTextPassword.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterForm.this, "INVALID!", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(RegisterForm.this, "Invalid Email Format", Toast.LENGTH_SHORT).show();

                }
                else if(!pattern.matcher(password).matches()){
                    Toast.makeText(RegisterForm.this, "Password should contain \n"+
                            "\n"  +
                            "There should be atleast 8 character\n" +
                            "\n"  +
                            "One capital letter\n" +
                            "\n" +
                            "One number\n" +
                            "\n" +
                            "One symbol ", Toast.LENGTH_SHORT).show();
                }
                else{
                    regis(email,password);
                    Intent i=new Intent(RegisterForm.this,LoginForm.class);
                    startActivity(i);
                }
            }
        });
    }
    private void regis(String email,String password){
   auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterForm.this, new OnCompleteListener<AuthResult>() {
       @Override
       public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
               Toast.makeText(RegisterForm.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
           }
           else{
               Toast.makeText(RegisterForm.this, "Failed", Toast.LENGTH_SHORT).show();
           }
       }
   });

    }
}