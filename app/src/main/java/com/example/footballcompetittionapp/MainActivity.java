package com.example.footballcompetittionapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText teamName, captainName, secondPlayer, thirdPlayer, forthPlayer, fifthPlayer;
    Button regBtn;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamName = findViewById(R.id.teamNameField);
        captainName = findViewById(R.id.captainNameField);
        secondPlayer = findViewById(R.id.secondPlayerField);
        thirdPlayer = findViewById(R.id.thirdPlayerField);
        forthPlayer = findViewById(R.id.forthPlayerField);
        fifthPlayer = findViewById(R.id.fifthPlayerField);
        regBtn = findViewById(R.id.readyBtn);

        reference = FirebaseDatabase.getInstance().getReference();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String team = teamName.getText().toString();
                String captain = captainName.getText().toString();
                String sp = secondPlayer.getText().toString();
                String tp = thirdPlayer.getText().toString();
                String forthPl = forthPlayer.getText().toString();
                String fifthPl = fifthPlayer.getText().toString();

                TeamDetails helper = new TeamDetails(team, captain, sp, tp, forthPl, fifthPl);

                if (team.isEmpty() || captain.isEmpty() || sp.isEmpty() || tp.isEmpty() || forthPl.isEmpty() || fifthPl.isEmpty()) {
                    Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {

                    reference.child("Team details").push().setValue(helper, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(MainActivity.this, "Team was created successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), CompetitionInfoActivity.class));
                        }
                    });


                }
                cleanFields();
            }
        });


    }

    private void cleanFields() {
        teamName.setText("");
        captainName.setText("");
        secondPlayer.setText("");
        thirdPlayer.setText("");
        forthPlayer.setText("");
        fifthPlayer.setText("");
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}