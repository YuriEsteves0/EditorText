package com.yuri.ldt.Controller.Interfaces;

import com.yuri.ldt.Model.Server.User.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebAPIService {
    @GET("selectUsuario.php")
    Call<UserResponse> getUserData(
            @Query("id") String id,
            @Query("emailUsu") String emailUsu,
            @Query("senhaUsu") String senhaUsu
    );
}
