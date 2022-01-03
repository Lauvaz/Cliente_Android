package edu.upc.dsa.client_g04;

import java.util.List;

import edu.upc.dsa.client_g04.Models.Object;
import edu.upc.dsa.client_g04.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIREST {

    @POST("GameServer/register")
    Call<User> addUser(@Body User user);

    @POST("GameServer/login")
    Call<User> loginUser(@Body User user);

    @GET("GameServer/itemList")
    Call<List<Object>> getItemList();

    @GET("GameServer/userList")
    Call<User> getUserList();

    @GET("GameServer/logout/{id}")
    Call<Void> logoutUser(@Path("id") int numID);
}
