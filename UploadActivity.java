package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadActivity extends AppCompatActivity {

    EditText gametitle, lendpricing, timeframe, username;
    Button uploadButton;

    DatabaseReference gameDBref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upload);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);



            gametitle = findViewById(R.id.gameTitle);
            lendpricing = findViewById(R.id.lendprice);
            timeframe = findViewById(R.id.timeframe);
            username = findViewById(R.id.username);
            uploadButton = findViewById(R.id.uploadBtn);

            gameDBref = FirebaseDatabase.getInstance().getReference().child("Games Lent");

            //insert data when buttton is pressed
            uploadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     insertUserGame();
                }
            });


            return insets;

        });
    }

    private void insertUserGame() {
        String user = username.getText().toString();
        String pricing = lendpricing.getText().toString();
        String time = timeframe.getText().toString();
        String game = gametitle.getText().toString();

        UserGame usergame = new UserGame(user,pricing,time,game);
        gameDBref.push().setValue(usergame);
        //creates a unique key to upload to the database
        Toast.makeText(UploadActivity.this,  "Data inserted", Toast.LENGTH_SHORT).show();

    }
}