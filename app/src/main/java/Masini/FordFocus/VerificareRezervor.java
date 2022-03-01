package Masini.FordFocus;

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

public class VerificareRezervor extends AppCompatActivity {

    TextView brandTxt,anFab;
    String brand,an;
    TextView nrKilometrii,intrebare;
    ImageView img;
    String verif;
    Button btnDA, btnNU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificare_masina);

        nrKilometrii = findViewById(R.id.nrkilometriiAfisat);
        nrKilometrii.setVisibility(View.INVISIBLE);
        intrebare = findViewById(R.id.intrebaretxt);
        intrebare.setText("Rezervorul este plin?");
        btnDA = findViewById(R.id.buttonDa);
        btnNU = findViewById(R.id.buttonNu);
        img = findViewById(R.id.imagineMasina);
        brandTxt = findViewById(R.id.numeBrand);
        anFab = findViewById(R.id.anFabricatie);
        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        img.setImageResource(R.mipmap.ic_focus);
        img.startAnimation(aniFade);

        DatabaseReference logan = FirebaseDatabase.getInstance().getReference("Ford Focus");

        logan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                brand = String.valueOf(dataSnapshot.child("Brand").getValue(String.class));
                brandTxt.setText(brand);
                an = String.valueOf(dataSnapshot.child("An fabricatie").getValue(String.class));
                anFab.setText(an);
                verif = dataSnapshot.child("Rezervor plin").getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });



        btnDA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(verif!="Da")
                    verif = "Da";
                logan.child("Rezervor plin").setValue(verif);
                openNewActivityurmator();
            }
        });

        btnNU.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(verif!="Nu")
                    verif = "Nu";
                logan.child("Rezervor plin").setValue(verif);
                openNewActivityModificareKm();
            }
        });
    }
    public void openNewActivityurmator(){
        Intent intent = new Intent(this, Masini.FordFocus.PerioadaInchiriere.class);
        startActivity(intent);
    }
    public void openNewActivityModificareKm(){
        Intent intent = new Intent(this, PerioadaInchiriere.class);
        startActivity(intent);
    }
}