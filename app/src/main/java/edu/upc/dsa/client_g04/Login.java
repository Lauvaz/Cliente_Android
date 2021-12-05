package edu.upc.dsa.client_g04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    TextView username;
    TextView password;
    APIREST apiRest;

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

    protected void loginClick(View v){
        this.username = (TextView) findViewById(R.id.editUsernameLogin);
        this.password = (TextView) findViewById(R.id.editPasswordLogin);

        LoginUser user = new LoginUser(username.getText().toString(),password.getText().toString());

        Call<LoginUser> call = apiRest.loginUser(user);
        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                if(response.isSuccessful()){
                    LoginUser user = response.body();
                    log.info("Inicio de sesion con nombre de usuario:"+user.getUserName());
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

    protected void register2Click(View v){
        Intent intentRegister = new Intent(this, Register.class);
        startActivity(intentRegister);
    }
}
