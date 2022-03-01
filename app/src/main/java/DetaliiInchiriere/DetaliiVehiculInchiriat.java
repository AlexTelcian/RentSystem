package DetaliiInchiriere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentsystem.R;
import com.example.rentsystem.main.MainPage;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import Masini.DaciaLogan.Modificare_km;
import Masini.DaciaSpring.DaciaSpring;

public class DetaliiVehiculInchiriat extends AppCompatActivity {

    private TextView numeView, brandView, anFabView, nrkmView, startView, returView, plataView;
    private Button returBTN;
    private String nume,brand,anFab,nrKm,start,retur,plata,numeClientAfisat;
    private ImageView masinaImg;
    private int imgCode;
    private Drawable drawableImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_vehicul_inchiriat);

        numeView = findViewById(R.id.numeTxt);
        brandView = findViewById(R.id.brandTxt);
        anFabView = findViewById(R.id.anFabTxt);
        nrkmView = findViewById(R.id.nrKMTxt);
        startView = findViewById(R.id.dataStartTxt);
        returView = findViewById(R.id.dataReturTXT);
        plataView = findViewById(R.id.pretTxt);
        masinaImg = findViewById(R.id.imagineMasina);
        returBTN = findViewById(R.id.buttonRetur);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            numeClientAfisat = String.valueOf(signInAccount.getDisplayName());
        }

        DatabaseReference inchiriereDB = FirebaseDatabase.getInstance().getReference("Inchirieri");
        inchiriereDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                        nume = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + Inchirieri.position).child("nume").getValue(String.class));
                        anFab = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + Inchirieri.position).child("an_fabricatie").getValue(String.class));
                        nrKm = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + Inchirieri.position).child("nr_km").getValue(String.class));
                        brand = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + Inchirieri.position).child("masina_inchiriata").getValue(String.class));
                        start = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + Inchirieri.position).child("perioada_start").getValue(String.class));
                        retur = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + Inchirieri.position).child("perioada_retur").getValue(String.class));
                        plata = String.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + Inchirieri.position).child("pret").getValue(String.class));
                        imgCode = Integer.valueOf(dataSnapshot.child(numeClientAfisat).child("inchiriere" + " " + Inchirieri.position).child("imagine").getValue(Integer.class));
                        drawableImg = getResources().getDrawable(imgCode);
                        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                        masinaImg.setImageDrawable(drawableImg);
                        masinaImg.startAnimation(aniFade);
                        numeView.setText(nume);
                        anFabView.setText(anFab);
                        nrkmView.setText(nrKm);
                        brandView.setText(brand);
                        startView.setText(start);
                        returView.setText(retur);
                        plataView.setText(plata + " lei");

                SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
                String time = formatter.format(new Date());

                if(CalculZile(time,retur) == 1){
                    addNotification();
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        returBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openNewActivityRetur();
            }
        });

    }

    private void addNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "Channel name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel description");
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.mipmap.ic_logo)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_HIGH)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setContentText("A mai ramas o zi pana la data returnarii autovehiculului inchiriat !");
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        Intent notificationIntent = new Intent(this, MainPage.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
    private int CalculZile(String ziStart, String ziRetur){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate firstDate = LocalDate.parse(ziStart, formatter);
        LocalDate secondDate = LocalDate.parse(ziRetur, formatter);
        long days = ChronoUnit.DAYS.between(firstDate, secondDate);

        return (int) days;
    }

    public void openNewActivityRetur(){
        if(brand.equals("Dacia Logan")) {
            Intent intent = new Intent(this, Modificare_km.class);
            startActivity(intent);
        }
        if(brand.equals("Dacia Sandero")) {
            Intent intent = new Intent(this, Masini.DaciaSandero.Modificare_km.class);
            startActivity(intent);
        }
        if(brand.equals("Dacia Spring")) {
            Intent intent = new Intent(this, Masini.DaciaSpring.Modificare_km.class);
            startActivity(intent);
        }
        if(brand.equals("Ford Fiesta")) {
            Intent intent = new Intent(this, Masini.FordFiesta.Modificare_km.class);
            startActivity(intent);
        }
        if(brand.equals("Ford Focus")) {
            Intent intent = new Intent(this, Masini.FordFocus.Modificare_km.class);
            startActivity(intent);
        }
        if(brand.equals("Renault Clio")) {
            Intent intent = new Intent(this, Masini.RenaultClio.Modificare_km.class);
            startActivity(intent);
        }
        if(brand.equals("Renault Kadjar")) {
            Intent intent = new Intent(this, Masini.RenaultKadjar.Modificare_km.class);
            startActivity(intent);
        }
        if(brand.equals("Renault Megane")) {
            Intent intent = new Intent(this, Masini.RenaultMegane.Modificare_km.class);
            startActivity(intent);
        }
    }
}