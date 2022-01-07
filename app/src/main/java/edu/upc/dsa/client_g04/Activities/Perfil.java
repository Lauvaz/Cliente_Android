package edu.upc.dsa.client_g04.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.logging.Logger;

import edu.upc.dsa.client_g04.APIREST;
import edu.upc.dsa.client_g04.Models.User;
import edu.upc.dsa.client_g04.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Perfil extends AppCompatActivity {

    User user;
    protected TextView name;
    protected TextView mail;
    protected TextView highScore;
    APIREST apiRest;
    List<User> usersList;
    final Logger log = Logger.getLogger(String.valueOf(Dashboard.class));

    //static final String BASEURL = "http://10.0.2.2:8080/dsaApp/";
    static final String BASEURL = "http://147.83.7.205:8080/dsaApp/";
    private int position;
    public static int numID;
    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiRest = retrofit.create(APIREST.class);

        Call<List<User>> callUser = apiRest.getUserList();
        callUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> callUser, Response<List<User>> response) {
                if(!response.isSuccessful()){
                    log.info("Error" + response.code());
                    return;
                }
                usersList = response.body();
                String username = getIntent().getStringExtra("username");
                id = getUser(usersList, username);

                Call<User> call2 = apiRest.getUserInfo(id);
                call2.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call2, Response<User> response) {
                        if(!response.isSuccessful()){
                            log.info("Error" + response.code());
                            return;
                        }
                        user = response.body();

                        name = findViewById(R.id.usernamePerfilEdit);
                        mail = findViewById(R.id.emailPerfilEdit);
                        highScore = findViewById(R.id.nHighscorePerfil);
                        name.setText(user.getName());
                        mail.setText(user.getMail());
                        highScore.setText(Integer.toString(user.getHighScore()));
                    }

                    @Override
                    public void onFailure(Call<User> call2, Throwable t) {
                        log.info("Error");
                    }
                });
            }

            @Override
            public void onFailure(Call<List<User>> callUser, Throwable t) {
                log.info("Error");
            }
        });
    }

    public int getUser(List<User> listUser, String username){
        for (position = 0;position<listUser.size();position=position+1){
            User user = listUser.get(position);
            if(user.getName().equals(username)) numID = user.getId();
        }
        return numID;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Log.i("ActionBar", "Atrás!");
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
