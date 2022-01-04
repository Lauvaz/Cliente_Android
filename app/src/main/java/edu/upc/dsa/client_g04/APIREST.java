package edu.upc.dsa.client_g04;

import java.util.List;

import edu.upc.dsa.client_g04.Models.Object;
import edu.upc.dsa.client_g04.Models.User;
import edu.upc.dsa.client_g04.Models.LoginUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIREST {

    @POST("GameServer/register")
    Call<User> addUser(@Body User user);

    @POST("GameServer/login")
    Call<LoginUser> loginUser(@Body LoginUser user);

    @GET("GameServer/itemList")
    Call<List<Object>> getItemList();

    @GET("GameServer/userList")
    Call<List<User>> getUserList();

    @GET("GameServer/logout/{id}")
    Call<Void> logoutUser(@Path("id") int numID);
}
