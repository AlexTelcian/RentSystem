package Masini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentsystem.R;
import com.example.rentsystem.main.Inchiriaza;

import DetaliiInchiriere.DetaliiClient;

public class DaciaLogan extends AppCompatActivity {

    TextView brandTxt,anFabricatieTxt,capacitateTxt,cutieVitezeTxt,numarLocuriTxt,capaciteteRezervorTxt,numarKmTxt,combustibilTxt,pretInchiriereTxt;
    ImageView masinaImg;
    Button selectMasina;

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

        brandTxt.setText("Dacia Logan");
        anFabricatieTxt.setText("2015");
        capacitateTxt.setText("1.5 dCi");
        cutieVitezeTxt.setText("Manual");
        numarLocuriTxt.setText("5");
        numarKmTxt.setText("75 000");
        capaciteteRezervorTxt.setText("60L");
        pretInchiriereTxt.setText("100 Lei");
        combustibilTxt.setText("Diesel" +
                "");
        masinaImg.setImageResource(R.mipmap.ic_dacia);

        selectMasina.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityselectareMasina();
            }
        });

    }
    public void openNewActivityselectareMasina(){
        Intent intent = new Intent(this, DetaliiClient.class);
        startActivity(intent);
    }
}