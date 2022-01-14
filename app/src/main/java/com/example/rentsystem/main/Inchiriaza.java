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

import Masini.DaciaLogan;
import Masini.DaciaSandero;
import Masini.DaciaSpring;
import Masini.FordFiesta;
import Masini.FordFocus;
import Masini.RenaultClio;
import Masini.RenaultKadjar;
import Masini.RenaultMegane;

public class Inchiriaza extends AppCompatActivity {

    Button daciaLoganBtn,daciaSanderobtn,daciaSpringbtn,renaultMeganebtn,renaultKadjarbtn,renaultCliebtn,fordFocusbtn,fordFiestabtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inchiriaza);

        daciaLoganBtn = findViewById(R.id.daciaLogan);
        daciaSanderobtn = findViewById(R.id.daciaSandero);
        daciaSpringbtn = findViewById(R.id.daciaSpring);
        renaultMeganebtn = findViewById(R.id.renaultMegane);
        renaultKadjarbtn = findViewById(R.id.renaultKadjar);
        renaultCliebtn = findViewById(R.id.renaultClio);
        fordFocusbtn = findViewById(R.id.fordFocus);
        fordFiestabtn = findViewById(R.id.fordFiesta);

        daciaLoganBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivitydaciaLogan();
            }
        });

        daciaSanderobtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivitydaciaSandero();
            }
        });
        daciaSpringbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivitydaciaSpring();
            }
        });

        renaultMeganebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityrenaultMegane();
            }
        });

        renaultKadjarbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityrenaultKadjar();
            }
        });
        renaultCliebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityrenaultClio();
            }
        });
        fordFocusbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityfordFocus();
            }
        });
        fordFiestabtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityfordFiesta();
            }
        });

        bottomBar();

    }

    public void openNewActivitydaciaLogan(){
        Intent intent = new Intent(this, DaciaLogan.class);
        startActivity(intent);
    }
    public void openNewActivitydaciaSandero(){
        Intent intent = new Intent(this, DaciaSandero.class);
        startActivity(intent);
    }
    public void openNewActivitydaciaSpring(){
        Intent intent = new Intent(this, DaciaSpring.class);
        startActivity(intent);
    }
    public void openNewActivityrenaultMegane(){
        Intent intent = new Intent(this, RenaultMegane.class);
        startActivity(intent);
    }
    public void openNewActivityrenaultKadjar(){
        Intent intent = new Intent(this, RenaultKadjar.class);
        startActivity(intent);
    }
    public void openNewActivityrenaultClio(){
        Intent intent = new Intent(this, RenaultClio.class);
        startActivity(intent);
    }
    public void openNewActivityfordFocus(){
        Intent intent = new Intent(this, FordFocus.class);
        startActivity(intent);
    }
    public void openNewActivityfordFiesta(){
        Intent intent = new Intent(this, FordFiesta.class);
        startActivity(intent);
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