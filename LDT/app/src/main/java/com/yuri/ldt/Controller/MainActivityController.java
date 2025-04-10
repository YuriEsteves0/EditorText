package com.yuri.ldt.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.yuri.ldt.Controller.Adapters.CardAdapter;
import com.yuri.ldt.Controller.Helpers.ActivityHelper;
import com.yuri.ldt.Controller.Interfaces.WebAPIService;
import com.yuri.ldt.Model.CardModel;
import com.yuri.ldt.Model.Server.User.CardResponse;

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

        Call<CardResponse> call = apiService.insertCard(nomeCard, idUsu, "teste");

        call.enqueue(new Callback<CardResponse>() {
            @Override
            public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                if(response.isSuccessful()){
                    Log.d("RETROFIT", "Card Criado com sucesso! " + response.body().getData());

                    for (int i = 0; i < cardModelList.size(); i++) {
                        Log.d("RETROFIT", "Card: " + cardModelList.get(i).getTitulo());
                    }

                    verificarSeJaFoiAdicionado(nomeCard, activity, cardModelList, adapter, 5);
                }
            }

            @Override
            public void onFailure(Call<CardResponse> call, Throwable t) {
                Log.e("RETROFIT", "Erro: " + t.getMessage());
            }
        });
    }

    public static void verificarSeJaFoiAdicionado(String nomeCard, Activity activity, List<CardModel> cardModelList, CardAdapter adapter, int tentativasRestantes ){
        if(tentativasRestantes <= 0) return;

        new Handler(Looper.getMainLooper()).postDelayed(() ->{
            receberCards(activity, cardModelList, adapter);

            boolean jaFoiAdicionado = false;

            for (CardModel card : cardModelList){
                if(card.getTitulo().equalsIgnoreCase(nomeCard)){
                    jaFoiAdicionado = true;
                    break;
                }
            }

            if(!jaFoiAdicionado){
                verificarSeJaFoiAdicionado(nomeCard, activity, cardModelList, adapter, tentativasRestantes - 1);
            }
        }, 1000);
    }

    public static void logoff(Context context){
        sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
