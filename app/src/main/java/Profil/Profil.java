package Profil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rentsystem.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profil extends AppCompatActivity {
    private TextView CNPTXT, telefonTXT, adresaTXT, numarTXT;
    private String numeComplet;
    private Button saveBtn, logout;
    private TextView name, email;
    private ImageView profileIMG;
    private String uriimg;
    private GoogleSignInAccount signInAccount;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        CNPTXT = findViewById(R.id.CNPClient);
        telefonTXT = findViewById(R.id.telefonClient);
        saveBtn = findViewById(R.id.urmator);
        logout = findViewById(R.id.logout);
        email = findViewById(R.id.email);
        profileIMG = findViewById(R.id.profilePicture);
        name = findViewById(R.id.name);
        adresaTXT = findViewById(R.id.adresaCLIENT);
        numarTXT = findViewById(R.id.numarCLIENT);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);

        signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            name.setText(signInAccount.getDisplayName());
            numeComplet = String.valueOf(signInAccount.getDisplayName());
            uriimg = signInAccount.getPhotoUrl().toString();
            Glide.with(getApplicationContext()).load(uriimg)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profileIMG);
            email.setText(signInAccount.getEmail());
        }

        logout.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {

                                          signOut();
                                      }
                                  });

        DatabaseReference clientDB = FirebaseDatabase.getInstance().getReference("Clienti");
        clientDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String cnp = String.valueOf(dataSnapshot.child(numeComplet).child("cnp").getValue(String.class));
                CNPTXT.setText(cnp);
                String telefon = String.valueOf(dataSnapshot.child(numeComplet).child("telefon").getValue(String.class));
                telefonTXT.setText(telefon);
                String adresa = String.valueOf(dataSnapshot.child(numeComplet).child("adresa").getValue(String.class));
                adresaTXT.setText(adresa);
                String numar = String.valueOf(dataSnapshot.child(numeComplet).child("numar").getValue(String.class));
                numarTXT.setText(numar);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityModifica();
            }
        });
    }

            private void signOut() {
                mAuth.getInstance().signOut();
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity();
                                finish();
                            }
                        });
            }

    private void startActivity(){
        Intent intent = new Intent(Profil.this,LogIn.class);
        startActivity(intent);
    }
    private void startActivityModifica(){
        Intent intent = new Intent(Profil.this,DetaliiClient.class);
        startActivity(intent);
    }
}