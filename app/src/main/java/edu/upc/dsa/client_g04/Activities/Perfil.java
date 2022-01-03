package edu.upc.dsa.client_g04.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.client_g04.APIREST;
import edu.upc.dsa.client_g04.Models.User;
import edu.upc.dsa.client_g04.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Perfil extends AppCompatActivity {

    User user;
    TextView name;
    TextView mail;
    APIREST apiRest;

    //static final String BASEURL = "http://10.0.2.2:8080/dsaApp/";
    static final String BASEURL = "http://147.83.7.205:8080/dsaApp/";

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
