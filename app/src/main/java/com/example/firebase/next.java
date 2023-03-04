package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class next extends AppCompatActivity {
     private EditText Name;
     private EditText Age;
     private Button Add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Name=findViewById(R.id.Name);
        Age=findViewById(R.id.Age);
        Add=findViewById(R.id.Add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> m=new HashMap<String, Object>();
                  m.put("name",Name.getText().toString());
                  m.put("age",Age.getText().toString());
                  FirebaseDatabase.getInstance().getReference().child("Vendor1").push().setValue(m);
                  Toast.makeText(next.this, "Successful...", Toast.LENGTH_SHORT).show();
                  Intent i=new Intent(next.this,Home.class);
                  i.putExtra("name",Name.getText().toString());
                  startActivity(i);
            }
        });
    }
}