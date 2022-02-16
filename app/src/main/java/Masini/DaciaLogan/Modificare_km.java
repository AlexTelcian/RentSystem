package Masini.DaciaLogan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import DetaliiInchiriere.DetaliiVehiculInchiriat;
import DetaliiInchiriere.Inchirieri;
import Retur.FinalRetur;

public class Modificare_km extends AppCompatActivity {

    private TextView brandTxt,anFab;
    private String brand,an;
    private EditText nrKilometrii;
    private ImageView img;
    private Button btnSave;
    private int id,idRetur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificare_km);

        nrKilometrii = findViewById(R.id.nrkilometriiAfisat);
        brandTxt = findViewById(R.id.numeBrand);
        anFab = findViewById(R.id.anFabricatie);
        img = findViewById(R.id.imagineMasina);
        btnSave = findViewById(R.id.buttonSave);

        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        img.setImageResource(R.mipmap.ic_dacia);
        img.startAnimation(aniFade);
        DatabaseReference logan = FirebaseDatabase.getInstance().getReference("Dacia Logan");
        DatabaseReference retur = FirebaseDatabase.getInstance().getReference("Retur");

        logan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                brand = String.valueOf(dataSnapshot.child("Brand").getValue(String.class));
                brandTxt.setText(brand);
                an = String.valueOf(dataSnapshot.child("An fabricatie").getValue(String.class));
                anFab.setText(an);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        final DatabaseReference index = FirebaseDatabase.getInstance().getReference("Index");
        index.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                id = Integer.valueOf(dataSnapshot.getValue(Integer.class));
                id--;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        DatabaseReference indexRetur = FirebaseDatabase.getInstance().getReference("indexRetur");
        indexRetur.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                idRetur = Integer.valueOf(dataSnapshot.getValue(Integer.class));
                idRetur++;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logan.child("Nr kilometrii").setValue(nrKilometrii.getText().toString());
                retur.child("Retur " + brand).child("Retur " + idRetur).child("nrkm").setValue(nrKilometrii.getText().toString());
                DatabaseReference inchiriereDB = FirebaseDatabase.getInstance().getReference("Inchirieri");
                inchiriereDB.child("inchiriere " + Inchirieri.position).getRef().removeValue();
                index.setValue(id--);

                indexRetur.setValue(idRetur);

                openNewActivityurmator();
            }
        });
    }

    public void openNewActivityurmator() {
        Intent intent = new Intent(this, FinalRetur.class);
        startActivity(intent);
    }

}
