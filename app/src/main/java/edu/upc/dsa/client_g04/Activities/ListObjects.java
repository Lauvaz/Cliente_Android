package edu.upc.dsa.client_g04.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.logging.Logger;

import edu.upc.dsa.client_g04.APIREST;
import edu.upc.dsa.client_g04.Models.Object;
import edu.upc.dsa.client_g04.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListObjects extends AppCompatActivity {

    RecyclerView recyclerView;

    List<Object> objectList;
    APIREST apiRest;

    final Logger log = Logger.getLogger(String.valueOf(Register.class));

    //static final String BASEURL = "http://10.0.2.2:8080/dsaApp/";
    static final String BASEURL = "http://147.83.7.205:8080/dsaApp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listobjects);

        recyclerView = findViewById(R.id.recyclerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiRest = retrofit.create(APIREST.class);


        Call<List<Object>> call = apiRest.getItemList();
        call.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                if(!response.isSuccessful()){
                    log.info("Error" + response.code());
                    Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                objectList = response.body();
                inicializarRecyclerView(objectList);
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Code: " + t.getMessage(), Toast.LENGTH_LONG).show();
                log.info("Error");
            }
        });
    }


    public void inicializarRecyclerView(List<Object> objects){ //Inicializar RecyclerView
        Adapter adapter= new Adapter(this,objects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Log.i("ActionBar", "Atr??s!");
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
