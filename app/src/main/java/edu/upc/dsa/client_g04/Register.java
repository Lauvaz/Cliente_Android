package edu.upc.dsa.client_g04;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    TextView name;
    TextView username;
    TextView email;
    TextView password;
    APIREST apiRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://147.83.7.205")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiRest = retrofit.create(APIREST.class);
    }

    public void registerClick(View v){
        this.name = (TextView) findViewById(R.id.editName);
        this.username = (TextView) findViewById(R.id.editUsername);
        this.email = (TextView) findViewById(R.id.editEmail);
        this.password = (TextView) findViewById(R.id.editPassword);

        User user = new User(username.getText().toString(),name.getText().toString(),email.getText().toString(),password.getText().toString()); //Cambio de CharSequence a String

        Call<User> call = apiRest.addUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User user = response.body();
                    System.out.println("Usuario registrado con nombre de usuario:" + user.getUserName());
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
