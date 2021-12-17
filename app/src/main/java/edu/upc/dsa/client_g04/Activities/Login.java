package edu.upc.dsa.client_g04.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Logger;

import edu.upc.dsa.client_g04.APIREST;
import edu.upc.dsa.client_g04.Models.LoginUser;
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
    private boolean cargando;
    final Logger log = Logger.getLogger(String.valueOf(Register.class));

    static final String BASEURL = "http://10.0.2.2:8080/dsaApp/";

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
        View tittle = findViewById(R.id.titulologin);
        View userLogin = findViewById(R.id.usernamelogin);
        View passwordLogin = findViewById(R.id.passwordlogin);
        View editUser = findViewById(R.id.editUsernameLogin);
        View editPassword = findViewById(R.id.editPasswordLogin);
        View buttonLogin = findViewById(R.id.loginClick);
        View buttonRegister = findViewById(R.id.register2Click);

        LoginUser user = new LoginUser(name.getText().toString(),password.getText().toString());

        Intent intentDashboard = new Intent(this, Dashboard.class);

        Call<LoginUser> call = apiRest.loginUser(user);
        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                if(response.isSuccessful()){
                    LoginUser user = response.body();
                    log.info("Inicio de sesion con nombre de usuario: "+user.getName());
                    if (cargando){
                        bProgreso.setVisibility(v.GONE);
                        tittle.setVisibility(v.VISIBLE);
                        userLogin.setVisibility(v.VISIBLE);
                        passwordLogin.setVisibility(v.VISIBLE);
                        editUser.setVisibility(v.VISIBLE);
                        editPassword.setVisibility(v.VISIBLE);
                        buttonLogin.setVisibility(v.VISIBLE);
                        buttonRegister.setVisibility(v.VISIBLE);
                    }
                    else{
                        bProgreso.setVisibility(v.VISIBLE);
                        tittle.setVisibility(v.GONE);
                        userLogin.setVisibility(v.GONE);
                        passwordLogin.setVisibility(v.GONE);
                        editUser.setVisibility(v.GONE);
                        editPassword.setVisibility(v.GONE);
                        buttonLogin.setVisibility(v.GONE);
                        buttonRegister.setVisibility(v.GONE);
                    }
                    cargando = !cargando;
                    startActivity(intentDashboard);
                } else {
                    log.info("Error al inicio de sesion");
                }
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                log.info("estamos aqui");
            }
        });
    }

    public void registerLoginClick(View v){
        Intent intentRegister = new Intent(this, Register.class);
        startActivity(intentRegister);
    }
}
