package DetaliiInchiriere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rentsystem.R;
import com.example.rentsystem.main.MainPage;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import Profil.Profil;
import Masini.DaciaLogan.DaciaLogan;
import Masini.DaciaSandero.DaciaSandero;
import Masini.DaciaSpring.DaciaSpring;
import Masini.FordFiesta.FordFiesta;
import Masini.FordFocus.FordFocus;
import Masini.RenaultClio.RenaultClio;
import Masini.RenaultKadjar.RenaultKadjar;
import Masini.RenaultMegane.RenaultMegane;

public class Inchiriaza extends AppCompatActivity {

    Button daciaLoganBtn,daciaSanderobtn,daciaSpringbtn,renaultMeganebtn,renaultKadjarbtn,renaultCliebtn,fordFocusbtn,fordFiestabtn;
    TextView numeClientAfisat;
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
        numeClientAfisat = findViewById(R.id.numeClientAfisat);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            numeClientAfisat.setText("Draga " + signInAccount.getDisplayName() + ",");
        }

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
        DaciaLoganDisponibilitate();
        DaciaSanderoDisponibilitate();
        DaciaSpringDisponibilitate();
        FordFiestaDisponibilitate();
        FordFocusDisponibilitate();
        RenaultClioDisponibilitate();
        RenaultKadjarDisponibilitate();
        RenaultMeganeDisponibilitate();
    }

    private void DaciaLoganDisponibilitate(){
        DatabaseReference dacia = FirebaseDatabase.getInstance().getReference("Dacia Logan");
        dacia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String retur = String.valueOf(dataSnapshot.child("perioada_retur").getValue(String.class));
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                String currentDate = sdf.format(new Date());
                if(CalculZile(currentDate,retur) > 0) {
                    daciaLoganBtn.setText("Disponibil \n din data \n" + retur);
                    daciaLoganBtn.setClickable(false);
                }
                else {
                    daciaLoganBtn.setText("Dacia Logan");
                    daciaLoganBtn.setClickable(true);
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
    private void DaciaSanderoDisponibilitate(){
        DatabaseReference dacia = FirebaseDatabase.getInstance().getReference("Dacia Sandero");
        dacia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String retur = String.valueOf(dataSnapshot.child("perioada_retur").getValue(String.class));
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                String currentDate = sdf.format(new Date());
                if(CalculZile(currentDate,retur) > 0) {
                    daciaSanderobtn.setText("Disponibil \n din data \n" + retur);
                    daciaSanderobtn.setClickable(false);
                }
                else {
                    daciaSanderobtn.setText("Dacia Sandero");
                    daciaSanderobtn.setClickable(true);
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
    private void DaciaSpringDisponibilitate(){
        DatabaseReference dacia = FirebaseDatabase.getInstance().getReference("Dacia Spring");
        dacia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String retur = String.valueOf(dataSnapshot.child("perioada_retur").getValue(String.class));
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                String currentDate = sdf.format(new Date());
                if(CalculZile(currentDate,retur) > 0) {
                    daciaSpringbtn.setText("Disponibil \n din data \n" + retur);
                    daciaSpringbtn.setClickable(false);
                }
                else {
                    daciaSpringbtn.setText("Dacia Spring");
                    daciaSpringbtn.setClickable(true);
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
    private void FordFiestaDisponibilitate(){
        DatabaseReference dacia = FirebaseDatabase.getInstance().getReference("Ford Fiesta");
        dacia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String retur = String.valueOf(dataSnapshot.child("perioada_retur").getValue(String.class));
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                String currentDate = sdf.format(new Date());
                if(CalculZile(currentDate,retur) > 0) {
                    fordFiestabtn.setText("Disponibil \n din data \n" + retur);
                    fordFiestabtn.setClickable(false);
                }
                else {
                    fordFiestabtn.setText("Ford Fiesta");
                    fordFiestabtn.setClickable(true);
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
    private void FordFocusDisponibilitate(){
        DatabaseReference dacia = FirebaseDatabase.getInstance().getReference("Ford Focus");
        dacia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String retur = String.valueOf(dataSnapshot.child("perioada_retur").getValue(String.class));
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                String currentDate = sdf.format(new Date());
                if(CalculZile(currentDate,retur) > 0) {
                    fordFocusbtn.setText("Disponibil \n din data \n" + retur);
                    fordFocusbtn.setClickable(false);
                }
                else {
                    fordFocusbtn.setText("Ford Focus");
                    fordFocusbtn.setClickable(true);
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
    private void RenaultClioDisponibilitate(){
        DatabaseReference dacia = FirebaseDatabase.getInstance().getReference("Renault Clio");
        dacia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String retur = String.valueOf(dataSnapshot.child("perioada_retur").getValue(String.class));
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                String currentDate = sdf.format(new Date());
                if(CalculZile(currentDate,retur) > 0) {
                    renaultCliebtn.setText("Disponibil \n din data \n" + retur);
                   renaultCliebtn.setClickable(false);
                }
                else {
                    renaultCliebtn.setText("Renault Clio");
                    renaultCliebtn.setClickable(true);
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
    private void RenaultKadjarDisponibilitate(){
        DatabaseReference dacia = FirebaseDatabase.getInstance().getReference("Renault Kadjar");
        dacia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String retur = String.valueOf(dataSnapshot.child("perioada_retur").getValue(String.class));
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                String currentDate = sdf.format(new Date());
                if(CalculZile(currentDate,retur) > 0) {
                    renaultKadjarbtn.setText("Disponibil \n din data \n" + retur);
                    renaultKadjarbtn.setClickable(false);
                }
                else {
                    renaultKadjarbtn.setText("Renault Kadjar");
                    renaultKadjarbtn.setClickable(true);
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
    private void RenaultMeganeDisponibilitate(){
        DatabaseReference dacia = FirebaseDatabase.getInstance().getReference("Renault Megane");
        dacia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String retur = String.valueOf(dataSnapshot.child("perioada_retur").getValue(String.class));
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                String currentDate = sdf.format(new Date());
                if(CalculZile(currentDate,retur) > 0) {
                    renaultMeganebtn.setText("Disponibil \n din data \n" + retur);
                    renaultMeganebtn.setClickable(false);
                }
                else {
                    renaultMeganebtn.setText("Renault Megane");
                    renaultMeganebtn.setClickable(true);
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
    private int CalculZile(String ziStart, String ziRetur){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate firstDate = LocalDate.parse(ziStart, formatter);
        LocalDate secondDate = LocalDate.parse(ziRetur, formatter);
        long days = ChronoUnit.DAYS.between(firstDate, secondDate);

        return (int) days;
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