package DetaliiInchiriere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rentsystem.R;

public class DetaliiClient extends AppCompatActivity {

    EditText numeTxt,prenumeTxt,CNPTXT,telefonTXT;
    String nume,prenume,cnp,telefon;
    Button urmatorBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_client);

        numeTxt = findViewById(R.id.numeClient);
        prenumeTxt = findViewById(R.id.prenumeClient);
        CNPTXT = findViewById(R.id.CNPClient);
        telefonTXT = findViewById(R.id.telefonClient);
        urmatorBtn = findViewById(R.id.urmator);

        nume = numeTxt.getText().toString();
        prenume = prenumeTxt.getText().toString();
        cnp = CNPTXT.getText().toString();
        telefon = telefonTXT.getText().toString();

        urmatorBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                openNewActivityurmator();
            }
        });
    }
    public void openNewActivityurmator(){
        Intent intent = new Intent(this, VerificareKilometrii.class);
        startActivity(intent);
    }
}