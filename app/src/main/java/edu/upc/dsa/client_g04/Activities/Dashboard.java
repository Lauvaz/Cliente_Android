package edu.upc.dsa.client_g04.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.client_g04.R;

public class Dashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }

    public void ListObjectsClick(View v){
        Intent intentListObjects = new Intent(this, ListObjects.class);
        startActivity(intentListObjects);
    }
}
