
package DetaliiInchiriere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

import java.util.ArrayList;

import Profil.Profil;

public class Inchirieri extends AppCompatActivity {

    String brand,start,retur,anFab,nrKm;
    TextView textId;
    TextView numeClientAfisat;
    Integer id;
    ListView listView;
    public static int position;
    ArrayList<NouVehiculInchiriat> detaliiVehiculInchiriat = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inchirieri);

        listView = findViewById(R.id.listviewId);
        textId = findViewById(R.id.textid);
        numeClientAfisat = findViewById(R.id.numeClientAfisat);
        bottomBar();

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            numeClientAfisat.setText("Draga " + signInAccount.getDisplayName() + ",");
        }

        DatabaseReference inchiriereDB = FirebaseDatabase.getInstance().getReference("Inchirieri");
        final DatabaseReference[] index = {FirebaseDatabase.getInstance().getReference("Index")};
        index[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                id = Integer.valueOf(dataSnapshot.getValue(Integer.class));
                id--;

                if(id < 0)
                    textId.setText("Nu aveti inchiriata nici o masina.");

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        inchiriereDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (int i = 0; i<= id; i++) {
                    if(dataSnapshot.child("inchiriere " + i).exists()) {
                        anFab = String.valueOf(dataSnapshot.child("inchiriere" + " " + i).child("an_fabricatie").getValue(String.class));
                        nrKm = String.valueOf(dataSnapshot.child("inchiriere" + " " + i).child("nr_km").getValue(String.class));
                        brand = String.valueOf(dataSnapshot.child("inchiriere" + " " + i).child("masina_inchiriata").getValue(String.class));
                        start = String.valueOf(dataSnapshot.child("inchiriere" + " " + i).child("perioada_start").getValue(String.class));
                        retur = String.valueOf(dataSnapshot.child("inchiriere" + " " + i).child("perioada_retur").getValue(String.class));

                        NouVehiculInchiriat detaliiVehiculInchiriat = new NouVehiculInchiriat(brand, anFab, nrKm, start, retur);
                        addNewItem(detaliiVehiculInchiriat);
                    }
                    else
                        continue;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


    }
    public void addNewItem(NouVehiculInchiriat item){
            detaliiVehiculInchiriat.add(item);

        ListAdapter listAdapter = new ListAdapter(Inchirieri.this,detaliiVehiculInchiriat);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Intent intent = new Intent(Inchirieri.this, DetaliiVehiculInchiriat.class);
                position = index;
                startActivity(intent);
            }
        });
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