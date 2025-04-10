package com.yuri.ldt.Controller.Interfaces;

import com.yuri.ldt.Model.Server.User.CardResponse;
import com.yuri.ldt.Model.Server.User.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebAPIService {
    @GET("selectUsuario.php")
    Call<UserResponse> getUserData(
            @Query("id") String id,
            @Query("emailUsu") String emailUsu,
            @Query("senhaUsu") String senhaUsu
    );

    @GET("selectCards.php")
    Call<CardResponse> getCards(
        @Query("idUsu") String idUsu,
        @Query("nomeCard") String nomeCard,
        @Query("tipo") String tipo
    );

    @FormUrlEncoded
    @POST("insertCard.php")
    Call<CardResponse> insertCard(
            @Field("nomeCard") String nomeCard,
            @Field("idUsu") String idUsu,
            @Field("textoCard") String textoCard
    );
}
