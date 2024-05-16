package com.example.coachingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

class login {
    private static login instance = null;    // *********************** Singleton  **********************
    String email;
    String password;

    private login() {

    }

    public static login getInstance() {
        if (instance == null) {
            instance = new login();
        }
        return instance;
    }

    void printDetails() {
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }
}

public class MainActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLogin;
    TextView notRegister;
    FirebaseAuth fAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmail = findViewById(R.id.loginEmail);
        mPassword = findViewById(R.id.regPassword);
        mLogin = findViewById(R.id.registration);
        notRegister = findViewById(R.id.notRegister);
        progressBar = findViewById(R.id.progressBarLogin);

        fAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                    finish();
                }
            }
        };

        final login userLogin = login.getInstance(); // Get singleton instance

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                userLogin.email = mEmail.getText().toString().trim();
                userLogin.password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(userLogin.email)) {
                    mEmail.setError("Email is Required.");
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(userLogin.password)) {
                    mPassword.setError("Password is Required.");
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (userLogin.password.length() < 6) {
                    mPassword.setError("Password must be greater or equal to 6 letters");
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                fAuth.signInWithEmailAndPassword(userLogin.email, userLogin.password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, HomePage.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Error !" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mEmail.setText("");
                mPassword.setText("");
            }
        });

        notRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            fAuth.removeAuthStateListener(authStateListener);
        }
    }
}
