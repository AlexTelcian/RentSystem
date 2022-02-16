package DetaliiInchiriere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import Masini.DaciaSandero;
import Masini.DaciaSpring;
import Masini.FordFiesta;
import Masini.FordFocus;
import Masini.RenaultClio;
import Masini.RenaultKadjar;
import Masini.RenaultMegane;

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