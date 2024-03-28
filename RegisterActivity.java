package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText registerEmail, registerName, registerPassword;
    TextView loginRedirectText;
    Button signupButton;

    FirebaseDatabase database;
    DatabaseReference reference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registernew);

        registerEmail = findViewById(R.id.register_email);
        registerName = findViewById(R.id.register_name);
        registerPassword = findViewById(R.id.register_password);
        registerEmail = findViewById(R.id.register_email);
        signupButton = findViewById(R.id.register_button);
        loginRedirectText = findViewById(R.id.register_text);



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Users");

                String name = registerName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerName.getText().toString();

                HelperClass helperClass = new HelperClass(name, email, password);
                //reference.child(name).setValue(helperClass);
                reference.child(email).setValue(helperClass);

                Toast.makeText(RegisterActivity.this, "You have signed up successfully!", Toast.LENGTH_LONG).show();

                Intent intent= new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);






            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });


    }
}