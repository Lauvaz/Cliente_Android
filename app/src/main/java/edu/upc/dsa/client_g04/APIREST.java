package edu.upc.dsa.client_g04;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIREST {

    @POST("GameServer/register")
    Call<User> addUser(@Body User user);

    @POST("GameServer/login")
    Call<LoginUser> loginUser(@Body LoginUser user);
}
