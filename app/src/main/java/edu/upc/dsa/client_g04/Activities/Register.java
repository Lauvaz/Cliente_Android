package edu.upc.dsa.client_g04.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Logger;

import edu.upc.dsa.client_g04.APIREST;
import edu.upc.dsa.client_g04.Models.User;
import edu.upc.dsa.client_g04.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    TextView name;
    TextView mail;
    TextView password;
    APIREST apiRest;

    private ProgressBar bProgreso;
    private boolean cargando;
    final Logger log = Logger.getLogger(String.valueOf(Register.class));

    //static final String BASEURL = "http://10.0.2.2:8080/dsaApp/";
    static final String BASEURL = "http://147.83.7.205:8080/dsaApp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiRest = retrofit.create(APIREST.class);
    }

    public void registerClick(View v){
        this.name = (TextView) findViewById(R.id.editUsername);
        this.mail = (TextView) findViewById(R.id.editEmail);
        this.password = (TextView) findViewById(R.id.editPassword);
        bProgreso = findViewById(R.id.progressBar);
        View tittle = findViewById(R.id.titulo);
        View userRegister = findViewById(R.id.username);
        View passwordRegister = findViewById(R.id.password);
        View emailRegister = findViewById(R.id.email);
        View editUser = findViewById(R.id.editUsername);
        View editPassword = findViewById(R.id.editPassword);
        View editEmail = findViewById(R.id.editEmail);
        View buttonRegister = findViewById(R.id.registerClick);

        Intent intentLogin = new Intent(this, Login.class);

        User user = new User(name.getText().toString(),mail.getText().toString(),password.getText().toString()); //Cambio de CharSequence a String

        Call<User> call = apiRest.addUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User user = response.body();
                    log.info("Usuario registrado con nombre de usuario: "+user.getName());
                    if (cargando){
                        bProgreso.setVisibility(v.GONE);
                        tittle.setVisibility(v.VISIBLE);
                        userRegister.setVisibility(v.VISIBLE);
                        passwordRegister.setVisibility(v.VISIBLE);
                        emailRegister.setVisibility(v.VISIBLE);
                        editUser.setVisibility(v.VISIBLE);
                        editPassword.setVisibility(v.VISIBLE);
                        editEmail.setVisibility(v.VISIBLE);
                        buttonRegister.setVisibility(v.VISIBLE);
                    }
                    else{
                        bProgreso.setVisibility(v.VISIBLE);
                        tittle.setVisibility(v.GONE);
                        userRegister.setVisibility(v.GONE);
                        passwordRegister.setVisibility(v.GONE);
                        emailRegister.setVisibility(v.GONE);
                        editUser.setVisibility(v.GONE);
                        editPassword.setVisibility(v.GONE);
                        editEmail.setVisibility(v.GONE);
                        buttonRegister.setVisibility(v.GONE);
                    }
                    cargando = !cargando;
                    startActivity(intentLogin);
                } else {
                    log.info("Error al registrarse");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //t.printStackTrace();
                log.info("estamos aqui");
            }
        });
    }
}
