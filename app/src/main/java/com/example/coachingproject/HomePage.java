package com.example.coachingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;




public class HomePage extends AppCompatActivity {

    CardView branches,email,phone_us,about_us;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ActionBarDrawerToggle actionBarDrawerToggle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpToolbar();

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);

        branches=findViewById(R.id.branches);
        email=findViewById(R.id.email);
        phone_us=findViewById(R.id.phone_us);
        about_us=findViewById(R.id.about_us);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if(item.getItemId()==R.id.your_account_profile){
                    startActivity(new Intent(HomePage.this,Account.class));

                }

                if(item.getItemId()==R.id.buy_courses){
                    startActivity(new Intent(HomePage.this,BuyCourses.class));

                }

                if(item.getItemId()==R.id.log_out_drawer_item_id){
                    FirebaseAuth.getInstance().signOut(); // logout
                    startActivity(new Intent(HomePage.this, MainActivity.class));
                    Toast.makeText(HomePage.this, "LOGGED OUT", Toast.LENGTH_SHORT).show();
                    finish();

                }



                if(item.getItemId()==R.id.your_courses){
                    startActivity(new Intent(HomePage.this,EnrolledCourses.class));

                }

                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });

        branches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,Branches.class));
            }
        });


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipientEmail = "coaching.academy@gmail.com";


                String uriText = "mailto:" + Uri.encode(recipientEmail);


                Uri uri = Uri.parse(uriText);

                Intent emailIntent = new Intent(Intent.ACTION_VIEW, uri);
                emailIntent.setPackage("com.google.android.gm"); // Gmail's package name

                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(HomePage.this, "Gmail is not installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        phone_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:123456789"));
                startActivity(intent);
            }
        });
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,AboutUs.class));
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;}

    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId()==R.id.account) {
            Intent intent = new Intent(HomePage.this, Account.class);
            startActivity(intent);

        }
        return true;
    }





}