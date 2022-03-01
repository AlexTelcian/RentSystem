package Masini.RenaultMegane;

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

import com.example.rentsystem.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import DetaliiInchiriere.Inchirieri;
import Retur.FinalRetur;

public class Modificare_km extends AppCompatActivity {

    private TextView brandTxt,anFab;
    private String brand,an,numeClientAfisat;
    private EditText nrKilometrii;
    private ImageView img;
    private Button btnSave;
    private int id,idRetur,dbKM;
    private int j = Inchirieri.position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificare_km);

        nrKilometrii = findViewById(R.id.nrkilometriiAfisat);
        brandTxt = findViewById(R.id.numeBrand);
        anFab = findViewById(R.id.anFabricatie);
        img = findViewById(R.id.imagineMasina);
        btnSave = findViewById(R.id.buttonSave);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            numeClientAfisat = String.valueOf(signInAccount.getDisplayName());
        }

        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        img.setImageResource(R.mipmap.ic_megane);
        img.startAnimation(aniFade);
        DatabaseReference logan = FirebaseDatabase.getInstance().getReference("Renault Megane");

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

        final DatabaseReference index = FirebaseDatabase.getInstance().getReference("indexClient");
        index.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                id = Integer.valueOf(dataSnapshot.child(numeClientAfisat).child("index").getValue(Integer.class));
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

                verifKm();
            }
        });
    }

    private void verifKm(){
        int ok=0;
        DatabaseReference dacia = FirebaseDatabase.getInstance().getReference("Renault Megane");
        DatabaseReference retur = FirebaseDatabase.getInstance().getReference("Retur");
        DatabaseReference index = FirebaseDatabase.getInstance().getReference("indexClient");
        DatabaseReference indexRetur = FirebaseDatabase.getInstance().getReference("indexRetur");
        if(nrKilometrii.getText().length() > 0) {
            ok = 1;
            dacia.child("Nr kilometrii").setValue(nrKilometrii.getText().toString());
            retur.child("Retur " + brand).child("Retur " + idRetur).child("nrkm").setValue(nrKilometrii.getText().toString());
            updateDB();
            index.child(numeClientAfisat).child("index").setValue(id--);
            indexRetur.setValue(idRetur);
            SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
            String currentDate = sdf.format(new Date());
            dacia.child("perioada_retur").setValue(currentDate);
        }
        else
            nrKilometrii.setHint("Kilometrii necompletati !");

        if(ok == 1)
            openNewActivityurmator();
    }
    private void updateDB(){
        DatabaseReference inchiriereDB = FirebaseDatabase.getInstance().getReference("Inchirieri");
        inchiriereDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (int i = j+1; i<= id+1; i++) {
                    String an = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + i).child("an_fabricatie").getValue(String.class));
                    String nrKm = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + i).child("nr_km").getValue(String.class));
                    String brandAuto = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + i).child("masina_inchiriata").getValue(String.class));
                    String start = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + i).child("perioada_start").getValue(String.class));
                    String retur = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + i).child("perioada_retur").getValue(String.class));
                    String pret = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + i).child("pret").getValue(String.class));
                    String telefon = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + i).child("telefon").getValue(String.class));
                    String nume = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + i).child("nume").getValue(String.class));
                    String cnp = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + i).child("cnp").getValue(String.class));
                    int imagine = Integer.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + i).child("imagine").getValue(Integer.class));

                    i--;
                    inchiriereDB.child(numeClientAfisat).child("inchiriere " + i).child("an_fabricatie").setValue(an);
                    inchiriereDB.child(numeClientAfisat).child("inchiriere " + i).child("nr_km").setValue(nrKm);
                    inchiriereDB.child(numeClientAfisat).child("inchiriere " + i).child("masina_inchiriata").setValue(brandAuto);
                    inchiriereDB.child(numeClientAfisat).child("inchiriere " + i).child("perioada_start").setValue(start);
                    inchiriereDB.child(numeClientAfisat).child("inchiriere " + i).child("perioada_retur").setValue(retur);
                    inchiriereDB.child(numeClientAfisat).child("inchiriere " + i).child("pret").setValue(pret);
                    inchiriereDB.child(numeClientAfisat).child("inchiriere " + i).child("telefon").setValue(telefon);
                    inchiriereDB.child(numeClientAfisat).child("inchiriere " + i).child("cnp").setValue(cnp);
                    inchiriereDB.child(numeClientAfisat).child("inchiriere " + i).child("nume").setValue(nume);
                    inchiriereDB.child(numeClientAfisat).child("inchiriere " + i).child("imagine").setValue(imagine);
                    i++;
                    j++;
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
    public void openNewActivityurmator() {
        Intent intent = new Intent(this, FinalRetur.class);
        startActivity(intent);
    }

}
