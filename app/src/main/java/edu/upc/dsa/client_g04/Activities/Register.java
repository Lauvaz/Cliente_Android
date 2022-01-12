package edu.upc.dsa.client_g04.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
        bProgreso.setVisibility(v.VISIBLE);

        Intent intentLogin = new Intent(this, Login.class);

        User user = new User(name.getText().toString(),mail.getText().toString(),password.getText().toString()); //Cambio de CharSequence a String

        Call<User> call = apiRest.addUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User user = response.body();
                    log.info("Usuario registrado con nombre de usuario: "+ user.getName());
                    Toast.makeText(getApplicationContext(), "Usuario registrado: " + name.getText().toString(), Toast.LENGTH_LONG).show();
                    startActivity(intentLogin);
                } else {
                    log.info("Error al registrarse");
                    Toast.makeText(getApplicationContext(), "Error al registrarse. Code: " + response.code(), Toast.LENGTH_LONG).show();
                    bProgreso.setVisibility(v.GONE);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //t.printStackTrace();
                log.info("estamos aqui");
                Toast.makeText(getApplicationContext(), "Error Code: " + t.getMessage(), Toast.LENGTH_LONG).show();
                bProgreso.setVisibility(v.GONE);
            }
        });
    }

    public void onBackPressed() {
    }
}
