package edu.upc.dsa.client_g04.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.client_g04.APIREST;
import edu.upc.dsa.client_g04.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Perfil extends AppCompatActivity {

    APIREST apiRest;
    static final String BASEURL = "http://10.0.2.2:8080/dsaApp/";

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
}
