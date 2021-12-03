package edu.upc.dsa.client_g04;

import retrofit2.*;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIREST {

    @POST("user/register")
    Call<User> addUser(@Body User user);

    @POST("user/login")
    Call<LoginUser> loginUser(@Body LoginUser user);
}
