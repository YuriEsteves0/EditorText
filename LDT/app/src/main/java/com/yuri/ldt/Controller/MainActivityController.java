package com.yuri.ldt.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.yuri.ldt.Controller.Adapters.CardAdapter;
import com.yuri.ldt.Controller.Helpers.ActivityHelper;
import com.yuri.ldt.Controller.Helpers.AndroidHelper;
import com.yuri.ldt.Controller.Interfaces.WebAPIService;
import com.yuri.ldt.Model.CardModel;
import com.yuri.ldt.Model.Server.User.CardResponse;
import com.yuri.ldt.View.CardEdit;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityController {
    private static SharedPreferences sharedPreferences;

    private List<CardModel> cardModelList = new ArrayList<>();

    public MainActivityController(Activity activity){
        ActivityHelper.finishAll();
        ActivityHelper activityHelper = new ActivityHelper(activity);
    }

    public static void receberCards(Activity activity, List<CardModel> cardModelList, CardAdapter adapter){
        String idUsu = pegarIdUsu(activity);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.yuriesteves.x-br.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebAPIService apiService = retrofit.create(WebAPIService.class);

        Call<CardResponse> call = apiService.getCards(idUsu, "", "all");

        call.enqueue(new Callback<CardResponse>() {
            @Override
            public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<CardModel> cards = response.body().getData();
                    if (cards != null) {
                        cardModelList.clear();
                        cardModelList.addAll(cards);
                        adapter.notifyDataSetChanged();
                        Log.d("RETROFIT", "Dados recebidos com sucesso: " + response.body().getData().toString());
                    } else {
                        Log.e("RETROFIT", "Lista de cards veio nula.");
                    }
                } else {
                    Log.e("RETROFIT", "Resposta mal sucedida: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CardResponse> call, Throwable t) {
                Log.e("RETROFIT", "Erro ao buscar cards: " + t.getMessage());
            }
        });
    }

    public static void deletarCard(String idCard){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.yuriesteves.x-br.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebAPIService apiService = retrofit.create(WebAPIService.class);

        Call<CardResponse> call = apiService.deletarCard(idCard);

        call.enqueue(new Callback<CardResponse>() {
            @Override
            public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                if(response.isSuccessful()){
                    Log.d("RETROFIT", "Card deletado com sucesso! " + response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CardResponse> call, Throwable t) {
                Log.e("RETROFIT", "Erro: " + t.getMessage());
            }
        });
    }

    public static String pegarIdUsu(Activity activity){
        sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE);
        String idUsu = sharedPreferences.getString("idUsu", "");
        Log.d("TAG", "ID USUARIO: " +idUsu);
        return idUsu;
    }

    public static void criarCard(String nomeCard, Activity activity, List<CardModel> cardModelList, CardAdapter adapter){
        String idUsu = pegarIdUsu(activity);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.yuriesteves.x-br.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebAPIService apiService = retrofit.create(WebAPIService.class);

        Call<CardResponse> call = apiService.insertCard(nomeCard, idUsu, "Eu sou um texto default, me mude!");

        call.enqueue(new Callback<CardResponse>() {
            @Override
            public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                if(response.isSuccessful()){
                    Log.d("RETROFIT", "Card Criado com sucesso! " + response.body().getData());

                    List<CardModel> cards = response.body().getData();
                    if (cards != null && !cards.isEmpty()) {
                        CardModel cardCriado = cards.get(0);

                        Intent intent = new Intent(activity, CardEdit.class);
                        intent.putExtra("idCard", cardCriado.getIdCard());
                        intent.putExtra("titulo", cardCriado.getTitulo());
                        intent.putExtra("descricao", cardCriado.getDescricao());
                        intent.putExtra("idUsuario", idUsu);
                        intent.putExtra("data", cardCriado.getData());
                        activity.startActivity(intent);
                        activity.finish();
                    }

                }
            }

            @Override
            public void onFailure(Call<CardResponse> call, Throwable t) {
                Log.e("RETROFIT", "Erro: " + t.getMessage());
            }
        });
    }


    public static void logoff(Context context){
        sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
