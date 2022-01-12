package edu.upc.dsa.client_g04.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.List;
import java.util.logging.Logger;

import edu.upc.dsa.client_g04.APIREST;
import edu.upc.dsa.client_g04.Models.LoginUser;
import edu.upc.dsa.client_g04.Models.Object;
import edu.upc.dsa.client_g04.Models.User;
import edu.upc.dsa.client_g04.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dashboard extends AppCompatActivity {

    ProgressBar bProgreso;
    List<User> usersList;

    APIREST apiRest;
    final Logger log = Logger.getLogger(String.valueOf(Dashboard.class));

    //static final String BASEURL = "http://10.0.2.2:8080/dsaApp/";
    static final String BASEURL = "http://147.83.7.205:8080/dsaApp/";
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiRest = retrofit.create(APIREST.class);
    }

    public void listObjectsClick(View v) {
        //bProgreso = findViewById(R.id.progressBar);
        Intent intentListObjects = new Intent(this, ListObjects.class);
        //bProgreso.setVisibility(View.VISIBLE);
        startActivity(intentListObjects);
    }

    public void perfilClick(View v) {
        //bProgreso = findViewById(R.id.progressBar);
        Intent intentPerfil = new Intent(this, Perfil.class);
        String username = getIntent().getStringExtra("username");
        intentPerfil.putExtra("username", username);
        //bProgreso.setVisibility(View.VISIBLE);
        startActivity(intentPerfil);
    }

    public void cerrarSesionClick(View v) {
        bProgreso = findViewById(R.id.progressBar);
        bProgreso.setVisibility(View.VISIBLE);
        Intent intentLogin = new Intent(this, Login.class);SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Dashboard.this);
        id = preferences.getInt("userId", 0);

        Call<Void> call = apiRest.logoutUser(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    log.info("Sesión cerrada correctamente");
                    preferences.edit().clear().commit();
                    Toast.makeText(getApplicationContext(), "Sesión cerrada correctamente", Toast.LENGTH_LONG).show();
                    startActivity(intentLogin);
                    finish();
                } else {
                    log.info("Error al cerrar sesion");
                    Toast.makeText(getApplicationContext(), "Error al cerrar sesión. Code: " + response.code(), Toast.LENGTH_LONG).show();
                    bProgreso.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                log.info("estamos aqui");
                Toast.makeText(getApplicationContext(), "Error Code: " + t.getMessage(), Toast.LENGTH_LONG).show();
                bProgreso.setVisibility(View.GONE);
            }
        });
    }

    public void onBackPressed() {
    }

}
