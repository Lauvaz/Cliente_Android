package edu.upc.dsa.client_g04.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

public class Login extends AppCompatActivity {
    TextView name;
    TextView password;
    APIREST apiRest;

    private ProgressBar bProgreso;
    final Logger log = Logger.getLogger(String.valueOf(Register.class));

    //static final String BASEURL = "http://10.0.2.2:8080/dsaApp/";
    static final String BASEURL = "http://147.83.7.205:8080/dsaApp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiRest = retrofit.create(APIREST.class);
    }

    public void loginClick(View v){
        this.name = (TextView) findViewById(R.id.editUsernameLogin);
        this.password = (TextView) findViewById(R.id.editPasswordLogin);

        bProgreso = findViewById(R.id.progressBar);
        bProgreso.setVisibility(v.VISIBLE);

        LoginUser user = new LoginUser(name.getText().toString(),password.getText().toString());

        Intent intentDashboard = new Intent(this, Dashboard.class);
        intentDashboard.putExtra("username",user.getName());

        Call<LoginUser> call = apiRest.loginUser(user);
        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                if(response.isSuccessful()){
                    LoginUser user = response.body();
                    log.info("Inicio de sesion con nombre de usuario: "+ user.getName());
                    startActivity(intentDashboard);
                } else {
                    log.info("Error al inicio de sesion");
                    bProgreso.setVisibility(v.GONE);
                }
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                log.info("estamos aqui");
                bProgreso.setVisibility(v.GONE);
            }
        });
    }

    public void registerLoginClick(View v){
        Intent intentRegister = new Intent(this, Register.class);
        startActivity(intentRegister);
    }

    public void onBackPressed() {
    }
}
