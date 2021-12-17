package edu.upc.dsa.client_g04.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.client_g04.R;

public class Dashboard extends AppCompatActivity {

    private ProgressBar bProgreso;
    private boolean cargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }

    public void listObjectsClick(View v){
        bProgreso = findViewById(R.id.progressBar);
        View buttonList = findViewById(R.id.ListObjectsClick);
        View imageList = findViewById(R.id.imageList);
        View imagePerfil = findViewById(R.id.imagePerfil);
        View buttonPerfil = findViewById(R.id.PerfilClick);
        View buttonLogout = findViewById(R.id.CerrarSesionClick);
        Intent intentListObjects = new Intent(this, ListObjects.class);
        if (cargando){
            bProgreso.setVisibility(v.GONE);
            buttonList.setVisibility(v.VISIBLE);
            imageList.setVisibility(v.VISIBLE);
            imagePerfil.setVisibility(v.VISIBLE);
            buttonPerfil.setVisibility(v.VISIBLE);
            buttonLogout.setVisibility(v.VISIBLE);
        }
        else{
            bProgreso.setVisibility(v.VISIBLE);
            buttonList.setVisibility(v.GONE);
            imageList.setVisibility(v.GONE);
            imagePerfil.setVisibility(v.GONE);
            buttonPerfil.setVisibility(v.GONE);
            buttonLogout.setVisibility(v.GONE);
        }
        cargando = !cargando;
        startActivity(intentListObjects);
    }

    public void perfilClick(View v) {
        bProgreso = findViewById(R.id.progressBar);
        View buttonList = findViewById(R.id.ListObjectsClick);
        View imageList = findViewById(R.id.imageList);
        View imagePerfil = findViewById(R.id.imagePerfil);
        View buttonPerfil = findViewById(R.id.PerfilClick);
        View buttonLogout = findViewById(R.id.CerrarSesionClick);
        Intent intentPerfil = new Intent(this, Perfil.class);
        if (cargando){
            bProgreso.setVisibility(v.GONE);
            buttonList.setVisibility(v.VISIBLE);
            imageList.setVisibility(v.VISIBLE);
            imagePerfil.setVisibility(v.VISIBLE);
            buttonPerfil.setVisibility(v.VISIBLE);
            buttonLogout.setVisibility(v.VISIBLE);
        }
        else{
            bProgreso.setVisibility(v.VISIBLE);
            buttonList.setVisibility(v.GONE);
            imageList.setVisibility(v.GONE);
            imagePerfil.setVisibility(v.GONE);
            buttonPerfil.setVisibility(v.GONE);
            buttonLogout.setVisibility(v.GONE);
        }
        cargando = !cargando;
        startActivity(intentPerfil);
    }

    public void cerrarSesionClick(View v) {
        bProgreso = findViewById(R.id.progressBar);
        View buttonList = findViewById(R.id.ListObjectsClick);
        View imageList = findViewById(R.id.imageList);
        View imagePerfil = findViewById(R.id.imagePerfil);
        View buttonPerfil = findViewById(R.id.PerfilClick);
        View buttonLogout = findViewById(R.id.CerrarSesionClick);
        Intent intentLogin = new Intent(this, Login.class);
        if (cargando){
            bProgreso.setVisibility(v.GONE);
            buttonList.setVisibility(v.VISIBLE);
            imageList.setVisibility(v.VISIBLE);
            imagePerfil.setVisibility(v.VISIBLE);
            buttonPerfil.setVisibility(v.VISIBLE);
            buttonLogout.setVisibility(v.VISIBLE);
        }
        else{
            bProgreso.setVisibility(v.VISIBLE);
            buttonList.setVisibility(v.GONE);
            imageList.setVisibility(v.GONE);
            imagePerfil.setVisibility(v.GONE);
            buttonPerfil.setVisibility(v.GONE);
            buttonLogout.setVisibility(v.GONE);
        }
        cargando = !cargando;
        startActivity(intentLogin);
    }
}
