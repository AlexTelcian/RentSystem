package Retur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.rentsystem.R;
import com.example.rentsystem.main.MainActivity;
import com.example.rentsystem.main.MainPage;

import Profil.LogIn;

public class FinalRetur extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_retur);

        /* New Handler to start the Menu-Activity
                * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(FinalRetur.this, MainPage.class);
                FinalRetur.this.startActivity(mainIntent);
               FinalRetur.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}