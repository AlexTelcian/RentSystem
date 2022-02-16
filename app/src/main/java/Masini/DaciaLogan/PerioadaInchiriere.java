package Masini.DaciaLogan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentsystem.R;
import com.example.rentsystem.main.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import DetaliiInchiriere.Inchirieri;

public class PerioadaInchiriere extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    ImageView img;
    TextView brandTxt,anFab;
    String brand,an;
    TextView perioadastart,perioadafinal;
    int index;
    Button selecteazaStart,selecteazaFinal,save;
    String dStart,dRetur;
    public int pret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perioada_inchiriere);

        img = findViewById(R.id.imagineMasina);
        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        img.setImageResource(R.mipmap.ic_dacia);
        img.startAnimation(aniFade);
        selecteazaStart = findViewById(R.id.btnSelect1);
        selecteazaFinal = findViewById(R.id.btnSelect2);
        perioadastart = findViewById(R.id.perioadaStart);
        perioadafinal = findViewById(R.id.perioadaFinal);
        save = findViewById(R.id.buttonSave);
        brandTxt = findViewById(R.id.numeBrand);
        anFab = findViewById(R.id.anFabricatie);

        selecteazaStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog();
                index = 1;
            }
        });
        selecteazaFinal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog();
                index = 2;
            }
        });

        DatabaseReference logan = FirebaseDatabase.getInstance().getReference("Dacia Logan");

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
    }



    DatabaseReference inchiriere = FirebaseDatabase.getInstance().getReference("Inchirieri");
    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int mounth, int dayofmounth) {
        Integer m = Integer.valueOf(mounth);
        m++;
        String mth = String.valueOf(m);
        String date = dayofmounth + "/" + mth + "/" + year;

        DatabaseReference indexId = FirebaseDatabase.getInstance().getReference("Index");
        if(index == 1) {
            dStart = date;
            perioadastart.setText(dStart);
            inchiriere.child(DaciaLogan.inchiriereNoua).child("perioada_start").setValue(dStart);

        }

        if(index == 2) {
            dRetur = date;
            perioadafinal.setText(dRetur);
            inchiriere.child(DaciaLogan.inchiriereNoua).child("perioada_retur").setValue(dRetur);

        }
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatabaseReference logan = FirebaseDatabase.getInstance().getReference("Dacia Logan");
                logan.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.

                        String pretStr = String.valueOf(dataSnapshot.child("Pret").getValue(String.class));
                        pret = Integer.valueOf(pretStr);

                        int days = CalculZile(dStart,dRetur);
                        pret = pret * days;
                        pretStr = String.valueOf(pret);
                        inchiriere.child(DaciaLogan.inchiriereNoua).child("pret").setValue(pretStr);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                    }
                });
                indexId.setValue(DaciaLogan.id);
                openNewActivityUrmator();
            }
        });


    }

    private void openNewActivityUrmator(){
        Intent intent = new Intent(this,  Inchirieri.class);
        startActivity(intent);
    }
    private int CalculZile(String ziStart, String ziRetur){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate firstDate = LocalDate.parse(ziStart, formatter);
        LocalDate secondDate = LocalDate.parse(ziRetur, formatter);
        long days = ChronoUnit.DAYS.between(firstDate, secondDate);

        return (int) days;
    }

}