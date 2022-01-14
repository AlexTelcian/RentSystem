package DetaliiInchiriere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rentsystem.R;

public class VerificareKilometrii extends AppCompatActivity {

    TextView nrKilometrii,intrebare;
    Button btnDA, btnNU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificare_masina);

        nrKilometrii = findViewById(R.id.nrkilometriiAfisat);
        intrebare = findViewById(R.id.intrebaretxt);
        btnDA = findViewById(R.id.buttonDa);
        btnNU = findViewById(R.id.buttonNu);

        nrKilometrii.setText("75 000");

        btnDA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityurmator();
            }
        });
    }
    public void openNewActivityurmator(){
        Intent intent = new Intent(this, VerificareRezervor.class);
        startActivity(intent);
    }

}