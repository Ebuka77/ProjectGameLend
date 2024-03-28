package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginButton;
    Button redirectButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEmail = findViewById(R.id.email_login);
        loginPassword = findViewById(R.id.password_login);
        loginButton = findViewById(R.id.login_button);
        redirectButton = findViewById(R.id.redirect_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateEmail() | !validatePassword()) {

                }else{
                    checkUserDat();
                }
            }
        });

        redirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    //validates user email
    public Boolean validateEmail() {
        String val = loginEmail.getText().toString();
        if(val.isEmpty()){
            loginEmail.setError("Email field cannot be empty!");
            return false;
        }else {
            loginEmail.setError(null);
            return true;
        }

    }
    //validates user password
    public Boolean validatePassword() {
        String val = loginPassword.getText().toString();
        if(val.isEmpty()){
            loginPassword.setError("Password field cannot be empty!");
            return false;
        }else {
            loginPassword.setError(null);
            return true;
        }

    }
    //check if user is already present in the database
    public void checkUserDat() {
        String emailLogin = loginEmail.getText().toString().trim();
        String passwordLogin = loginPassword.getText().toString().trim();

        //refer to parent name "users", use query to compare email stored in database and email typed
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUserDatabase = reference.orderByChild("email").equalTo(emailLogin);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //if email exists then error set to null
                if(snapshot.exists()) {
                    loginEmail.setError(null);
                    String passwordFromDb = snapshot.child(emailLogin).child("password").getValue(String.class);
                    if(passwordFromDb.equals(passwordLogin)) {
                        loginEmail.setError(null);
                        //if it matches then take user to the home activity.
                        Intent intent = new Intent( MainActivity.this, HomeActivity.class);
                        startActivity(intent);

                    }
                    else{ //if incorrect password then output message to user
                        loginPassword.setError("invalid Password");
                        loginPassword.requestFocus();


                    }
                }
                else{
                    loginEmail.setError("user does not exist");
                    loginEmail.requestFocus();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }


}