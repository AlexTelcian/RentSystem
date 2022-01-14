package Masini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentsystem.R;

public class DaciaSandero extends AppCompatActivity {

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

        brandTxt.setText("Dacia Sandero");
        anFabricatieTxt.setText("2014");
        capacitateTxt.setText("1.0 SCE");
        cutieVitezeTxt.setText("Manual");
        numarLocuriTxt.setText("5");
        numarKmTxt.setText("47 000");
        capaciteteRezervorTxt.setText("60L");
        pretInchiriereTxt.setText("100 Lei");
        combustibilTxt.setText("Benzina");
        masinaImg.setImageResource(R.mipmap.ic_sandero);

        selectMasina.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityselectareMasina();
            }
        });

    }
    public void openNewActivityselectareMasina(){
        /*Intent intent = new Intent(this, NumeClient.class);
        startActivity(intent);*/
    }
}