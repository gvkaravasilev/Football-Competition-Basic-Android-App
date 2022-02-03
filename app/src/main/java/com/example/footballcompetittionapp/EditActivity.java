package com.example.footballcompetittionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditActivity extends AppCompatActivity {

    Button submitBtn, deleteBtn;
    EditText teamName, captain, sPl, tPl, forthPl, fifthPl;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        teamName = findViewById(R.id.newTeamName);
        captain = findViewById(R.id.newCaptain);
        sPl = findViewById(R.id.newSecondPl);
        tPl = findViewById(R.id.newThirdPl);
        forthPl = findViewById(R.id.newForthPl);
        fifthPl = findViewById(R.id.newFifthPl);

        submitBtn = findViewById(R.id.submitBtn);
        deleteBtn = findViewById(R.id.dltBtn);


        reference = FirebaseDatabase.getInstance().getReference();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(teamName.toString().trim()) || TextUtils.isEmpty(captain.toString().trim()) ||
                        TextUtils.isEmpty(sPl.toString().trim()) || TextUtils.isEmpty(tPl.toString().trim()) || TextUtils.isEmpty(forthPl.toString().trim())
                        || TextUtils.isEmpty(fifthPl.toString().trim())){
                    Toast.makeText(EditActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }else{
                    editTeam();
                    Toast.makeText(EditActivity.this, "Team details were updated !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), CompetitionInfoActivity.class));
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTeam();
            }
        });
    }

    public void editTeam() {
        final TeamDetails details = new TeamDetails(teamName.getText().toString(),
                captain.getText().toString(),
                sPl.getText().toString(),
                tPl.getText().toString(),
                forthPl.getText().toString(),
                fifthPl.getText().toString()
        );

        Query query = reference.child("Team details").orderByChild("Team details").equalTo(details.getFirstPl());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String key = "";
                for (DataSnapshot ds  : snapshot.getChildren()) {
                    key = ds.getKey();
                }
                reference.child("Team details").child(key).child(key).setValue(details);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void deleteTeam(){

        reference.setValue(null);

        Toast.makeText(EditActivity.this, "Team was successfully deleted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}