package com.example.coachingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
import java.util.Objects;

public class Transaction extends AppCompatActivity {
    public static final String TAG = "TAG";
    Toolbar toolbar;
    Integer x, y;
    EditText TransactionNum;
    Button paymentButton;
    FirebaseAuth fAuth;
    String roll, CourseTitle;

    private DatabaseReference mDatabase, nDatabase, mnDatabase, rmDatabase;

    private DatabaseReference admin;

    String UserId;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        toolbar = findViewById(R.id.tlbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        TransactionNum = findViewById(R.id.TransactionNum);

        paymentButton = findViewById(R.id.payment);

        fAuth = FirebaseAuth.getInstance();
        UserId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        nDatabase = FirebaseDatabase.getInstance().getReference("users").child(UserId).child("z_Transaction_Number");
        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(UserId).child("z_Courses");

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                x = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error if needed
            }
        });

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x++;
                CourseTitle = Objects.requireNonNull(getIntent().getExtras()).getString("course");
                String TransactionNumber = TransactionNum.getText().toString();
                String SlNum = String.valueOf(x);

                mDatabase.child(SlNum).setValue(CourseTitle);
                nDatabase.child(SlNum).setValue(TransactionNumber);
                Toast.makeText(Transaction.this, "Course Enrolled", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Transaction.this, HomePage.class));
            }
        });
    }
}