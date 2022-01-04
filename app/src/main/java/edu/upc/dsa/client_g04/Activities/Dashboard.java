package edu.upc.dsa.client_g04.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.logging.Logger;

import edu.upc.dsa.client_g04.APIREST;
import edu.upc.dsa.client_g04.Models.LoginUser;
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
        //bProgreso.setVisibility(View.VISIBLE);
        startActivity(intentPerfil);
    }

    public void cerrarSesionClick(View v) {
        bProgreso = findViewById(R.id.progressBar);
        bProgreso.setVisibility(View.VISIBLE);

        Call<List<User>> callUser = apiRest.getUserList();
        callUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> callUser, Response<List<User>> response) {
                if(!response.isSuccessful()){
                    log.info("Error" + response.code());
                    return;
                }
                usersList = response.body();
            }
            @Override
            public void onFailure(Call<List<User>> callUser, Throwable t) {
                log.info("Error");
            }
        });

        /*int numID = user.getId();
        Intent intentLogin = new Intent(this, Login.class);
        Call<Void> call = apiRest.logoutUser(numID);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    log.info("Sesión cerrada correctamente");
                    startActivity(intentLogin);
                    finish();
                } else {
                    log.info("Error al cerrar sesion");
                    bProgreso.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                log.info("estamos aqui");
                bProgreso.setVisibility(View.GONE);
            }
        });*/
    }

    public void onBackPressed() {
    }
}
