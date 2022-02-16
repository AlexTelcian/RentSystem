package com.example.rentsystem.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rentsystem.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import DetaliiInchiriere.Inchiriaza;
import DetaliiInchiriere.Inchirieri;
import Profil.Profil;
import Profil.LogIn;

public class MainPage extends AppCompatActivity {

    private Button btnInchiriaza, btnInchirieri,connect;
    private TextView name,mesajTXT;
    private ImageView profileIMG;
    private String uriimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        bottomBar();

        btnInchiriaza = findViewById(R.id.rentBtn);
        btnInchirieri = findViewById(R.id.inchirieriBtn);
        profileIMG = findViewById(R.id.profilePicture);
        name = findViewById(R.id.name);
        mesajTXT = findViewById(R.id.mesaj1);
        connect = findViewById(R.id.connect);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            name.setText("Buna " + signInAccount.getDisplayName());
            uriimg = signInAccount.getPhotoUrl().toString();
            Glide.with(getApplicationContext()).load(uriimg)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profileIMG);

            mesajTXT.setVisibility(View.INVISIBLE);
            connect.setVisibility(View.INVISIBLE);
        }
        else
        {
            mesajTXT.setVisibility(View.VISIBLE);
            connect.setVisibility(View.VISIBLE);
        }

        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityLogIn();
            }
        });

        btnInchiriaza.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityInchiriaza();
            }
        });

        btnInchirieri.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openNewActivityInchirieri();
            }
        });


    }
    public void openNewActivityLogIn(){
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }

    public void openNewActivityHome(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
    public void openNewActivityInchiriaza(){
        Intent intent = new Intent(this, Inchiriaza.class);
        startActivity(intent);
    }
    public void openNewActivityInchirieri(){
        Intent intent = new Intent(this, Inchirieri.class);
        startActivity(intent);
    }
    public void openNewActivityProfil(){
        Intent intent = new Intent(this, Profil.class);
        startActivity(intent);
    }
    public void bottomBar(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        openNewActivityHome();
                        break;
                    case R.id.inchiriazaBTM:
                        openNewActivityInchiriaza();
                        break;
                    case R.id.inchirieriBTM:
                        openNewActivityInchirieri();
                        break;
                    case R.id.profilBTM:
                        openNewActivityProfil();
                        break;
                }
                return true;
            }
        });
    }
}