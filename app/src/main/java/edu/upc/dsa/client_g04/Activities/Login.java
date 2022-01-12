package edu.upc.dsa.client_g04.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class Login extends AppCompatActivity {

    TextView name;
    TextView password;
    APIREST apiRest;

    List<User> usersList;
    private int position;
    public static int numID;
    public static int id;

    int userId;

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

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
        userId = preferences.getInt("userId", 0);
        if(userId>0){
            Intent intentDashboard = new Intent(this, Dashboard.class);
            startActivity(intentDashboard);
        }
    }

    public void loginClick(View v){

        this.name = (TextView) findViewById(R.id.editUsernameLogin);
        this.password = (TextView) findViewById(R.id.editPasswordLogin);

        bProgreso = findViewById(R.id.progressBar);
        bProgreso.setVisibility(v.VISIBLE);

        LoginUser user = new LoginUser(name.getText().toString(),password.getText().toString());

        Intent intentDashboard = new Intent(this, Dashboard.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);

        Call<LoginUser> call = apiRest.loginUser(user);
        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                if(response.isSuccessful()){
                    LoginUser user = response.body();
                    log.info("Inicio de sesion con nombre de usuario: "+ user.getName());

                    Call<List<User>> callUser = apiRest.getUserList();
                    callUser.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            if(!response.isSuccessful()){
                                log.info("Error" + response.code());
                                Toast.makeText(getApplicationContext(), "El usuario no existe. Error: " + response.code(), Toast.LENGTH_LONG).show();
                                return;
                            }
                            usersList = response.body();
                            id = getUser(usersList, name.getText().toString());
                            preferences.edit().putInt("userId", id).commit();
                            Toast.makeText(getApplicationContext(), "Bienvenid@ " + name.getText().toString(), Toast.LENGTH_LONG).show();
                            startActivity(intentDashboard);
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error Code " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                } else {
                    log.info("Error al inicio de sesion");
                    Toast.makeText(getApplicationContext(), "El usuario no existe. Error: " + response.code(), Toast.LENGTH_LONG).show();
                    bProgreso.setVisibility(v.GONE);
                }
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                log.info("estamos aqui");
                Toast.makeText(getApplicationContext(), "Error Code " + t.getMessage(), Toast.LENGTH_LONG).show();
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

    public int getUser(List<User> listUser, String username){
        for (position = 0;position<listUser.size();position=position+1){
            User user = listUser.get(position);
            if(user.getName().equals(username)) numID = user.getId();
        }
        return numID;
    }
}
