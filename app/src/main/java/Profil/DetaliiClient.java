package Profil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rentsystem.R;
import com.example.rentsystem.main.MainPage;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetaliiClient extends AppCompatActivity {

    private EditText CNPTXT,telefonTXT,adresaTXT,numarTXT;
    private String numeComplet;
    private Button saveBtn,logout;
    private int index = 0;
    private TextView name,email;
    private ImageView profileIMG;
    private String uriimg;
    private GoogleSignInAccount signInAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_client);

        DatabaseReference indexClientDB = FirebaseDatabase.getInstance().getReference("indexClient");

        CNPTXT = findViewById(R.id.CNPClient);
        telefonTXT = findViewById(R.id.telefonClient);
        saveBtn = findViewById(R.id.urmator);
        email = findViewById(R.id.email);
        profileIMG = findViewById(R.id.profilePicture);
        name = findViewById(R.id.name);
        adresaTXT = findViewById(R.id.adresaCLIENT);
        numarTXT = findViewById(R.id.numarCLIENT);

       signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            name.setText(signInAccount.getDisplayName());
            numeComplet = String.valueOf(signInAccount.getDisplayName());
            indexClientDB.child(numeComplet).child("indexClient").setValue(0);
            uriimg = signInAccount.getPhotoUrl().toString();
            Glide.with(getApplicationContext()).load(uriimg)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profileIMG);
            email.setText(signInAccount.getEmail());
        }

        indexClientDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                index = Integer.valueOf(dataSnapshot.child(numeComplet).child("indexClient").getValue(Integer.class));

                if (index == 4)
                    startActivityNext();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                creareClient();
                startActivityNext();

                }
        });
    }

    private void startActivityNext(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
    private void creareClient(){
        DatabaseReference clientDB = FirebaseDatabase.getInstance().getReference("Clienti");
        DatabaseReference indexClientDB = FirebaseDatabase.getInstance().getReference("indexClient");


        if (CNPTXT.length() == 0)
            CNPTXT.setHint("CNP necompletat");
        else
            index++;

        if (telefonTXT.length() == 0)
            telefonTXT.setHint("Telefon necompletat");
        else
            index++;

        if (adresaTXT.length() == 0)
            adresaTXT.setHint("Strada necompletata");
        else
            index++;

        if (numarTXT.length() == 0)
            numarTXT.setHint("Numar necompletat");
        else
            index++;

        indexClientDB.child(numeComplet).child("indexClient").setValue(index);
        clientDB.child(numeComplet).setValue(new InregistrareClient(numeComplet, CNPTXT.getText().toString(),
                telefonTXT.getText().toString(), adresaTXT.getText().toString(), numarTXT.getText().toString()));

    }
}