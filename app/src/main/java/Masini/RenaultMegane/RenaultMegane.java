package Masini.RenaultMegane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentsystem.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import DetaliiInchiriere.InchiriereNoua;
import Masini.DaciaLogan.DaciaLogan;

public class RenaultMegane extends AppCompatActivity {

    TextView brandTxt, anFabricatieTxt, capacitateTxt, cutieVitezeTxt, numarLocuriTxt, capaciteteRezervorTxt, numarKmTxt, combustibilTxt, pretInchiriereTxt;
    ImageView masinaImg;
    Button selectMasina;
    public String brand, km, anFab, pretStr, nume, cnp, telefon, numeComplet;
    public static String inchiriereNoua;
    public static Integer id;
    int imgCode;
    Drawable drawableImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specificatii);

        brandTxt = findViewById(R.id.numeBrand);
        anFabricatieTxt = findViewById(R.id.anFabricatie);
        capacitateTxt = findViewById(R.id.capacitate);
        cutieVitezeTxt = findViewById(R.id.cutieviteze);
        numarLocuriTxt = findViewById(R.id.nrlocuri);
        capaciteteRezervorTxt = findViewById(R.id.capacitateRezervor);
        numarKmTxt = findViewById(R.id.nrKM);
        combustibilTxt = findViewById(R.id.tipcombustibil);
        masinaImg = findViewById(R.id.imagineMasina);
        selectMasina = findViewById(R.id.selecteaza);
        pretInchiriereTxt = findViewById(R.id.pretinchiriere);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            numeComplet = String.valueOf(signInAccount.getDisplayName());
        }

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Renault Megane");
        DatabaseReference index = FirebaseDatabase.getInstance().getReference("indexClient");
        DatabaseReference inchiriere = FirebaseDatabase.getInstance().getReference("Inchirieri");
        DatabaseReference clientDB = FirebaseDatabase.getInstance().getReference("Clienti");

        myRef.child("Imagine").setValue(R.mipmap.ic_megane);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                brand = String.valueOf(dataSnapshot.child("Brand").getValue(String.class));
                brandTxt.setText(brand);
                pretStr = String.valueOf(dataSnapshot.child("Pret").getValue(String.class));
                km = String.valueOf(dataSnapshot.child("Nr kilometrii").getValue(String.class));
                numarKmTxt.setText(km);
                anFab = String.valueOf(dataSnapshot.child("An fabricatie").getValue(String.class));
                imgCode = Integer.valueOf(dataSnapshot.child("Imagine").getValue(Integer.class));

                drawableImg = getResources().getDrawable(imgCode);
                Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                masinaImg.setImageDrawable(drawableImg);
                masinaImg.startAnimation(aniFade);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        index.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                id = Integer.valueOf(dataSnapshot.child(numeComplet).child("index").getValue(Integer.class));

                inchiriereNoua = "inchiriere" + " " + RenaultMegane.id;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        clientDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                nume = String.valueOf(dataSnapshot.child(numeComplet).child("nume").getValue(String.class));
                cnp = String.valueOf(dataSnapshot.child(numeComplet).child("cnp").getValue(String.class));
                telefon = String.valueOf(dataSnapshot.child(numeComplet).child("telefon").getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        anFabricatieTxt.setText("2004");
        capacitateTxt.setText("1.6 MPI");
        cutieVitezeTxt.setText("Manual");
        numarLocuriTxt.setText("5");
        capaciteteRezervorTxt.setText("50L");
        pretInchiriereTxt.setText("200 Lei");
        combustibilTxt.setText("Benzina" +
                "");

        selectMasina.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                inchiriere.child(numeComplet).child(inchiriereNoua).setValue(new InchiriereNoua(imgCode, nume, cnp, telefon, brand, anFab, km, "", "", pretStr));
                id++;
                openNewActivityselectareMasina();
            }
        });

    }

    public void openNewActivityselectareMasina() {
        Intent intent = new Intent(this, VerificareKilometrii.class);
        startActivity(intent);
    }
}