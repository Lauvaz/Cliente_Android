package edu.upc.dsa.client_g04.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.client_g04.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        Intent intentLogin = new Intent(this, Login.class);
        startActivity(intentLogin);
    }
}
