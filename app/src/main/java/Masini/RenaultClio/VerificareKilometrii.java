package Masini.RenaultClio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Masini.DaciaLogan.Modificare_km;

public class VerificareKilometrii extends AppCompatActivity {

    TextView nrKilometrii, intrebare;
    TextView brandTxt, anFab;
    String brand, an;
    ImageView img;
    String km;
    Button btnDA, btnNU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificare_masina);

        nrKilometrii = findViewById(R.id.nrkilometriiAfisat);
        intrebare = findViewById(R.id.intrebaretxt);
        btnDA = findViewById(R.id.buttonDa);
        btnNU = findViewById(R.id.buttonNu);
        brandTxt = findViewById(R.id.numeBrand);
        anFab = findViewById(R.id.anFabricatie);
        img = findViewById(R.id.imagineMasina);

        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        img.setImageResource(R.mipmap.ic_clio);
        img.startAnimation(aniFade);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Renault Clio");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                brand = String.valueOf(dataSnapshot.child("Brand").getValue(String.class));
                brandTxt.setText(brand);
                an = String.valueOf(dataSnapshot.child("An fabricatie").getValue(String.class));
                anFab.setText(an);
                km = dataSnapshot.child("Nr kilometrii").getValue(String.class);
                nrKilometrii.setText(km);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


        btnDA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityurmator();
            }
        });

        btnNU.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openNewActivityurmator();
            }
        });
    }

    public void openNewActivityurmator() {
        Intent intent = new Intent(this, VerificareRezervor.class);
        startActivity(intent);
    }

    public void openNewActivityModificareKm() {
        Intent intent = new Intent(this, Modificare_km.class);
        startActivity(intent);
    }
}