package com.example.rentsystem.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.rentsystem.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {

    Button btnInchiriaza, btnRetur, btnInchirieri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        bottomBar();

        btnInchiriaza = findViewById(R.id.rentBtn);
        btnRetur = findViewById(R.id.returBtn);
        btnInchirieri = findViewById(R.id.inchirieriBtn);

        btnInchiriaza.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityInchiriaza();
            }
        });

        btnRetur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityRetur();
            }
        });

        btnInchirieri.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityInchirieri();
            }
        });
    }


    public void openNewActivityInchiriaza(){
        Intent intent = new Intent(this, Inchiriaza.class);
        startActivity(intent);
    }
    public void openNewActivityRetur(){
        Intent intent = new Intent(this, Retur.class);
        startActivity(intent);
    }
    public void openNewActivityInchirieri(){
        Intent intent = new Intent(this, Inchirieri.class);
        startActivity(intent);
    }
    public void bottomBar(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.inchiriazaBTM:
                        openNewActivityInchiriaza();
                        break;
                    case R.id.returBTM:
                        openNewActivityRetur();
                        break;
                    case R.id.inchirieriBTM:
                        openNewActivityInchirieri();
                        break;
                }
                return true;
            }
        });
    }
}