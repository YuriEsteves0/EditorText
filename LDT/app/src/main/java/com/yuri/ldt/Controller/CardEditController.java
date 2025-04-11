package com.yuri.ldt.Controller;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.yuri.ldt.Controller.Helpers.ActivityHelper;
import com.yuri.ldt.Controller.Helpers.SoftInputAssist;
import com.yuri.ldt.Controller.Interfaces.WebAPIService;
import com.yuri.ldt.Model.Server.User.CardResponse;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CardEditController {
    private static Handler autosaveHandler;
    private static Runnable autosaveRunnable;
    private static String ultimoTextoSalvo = "";

    public CardEditController(Activity activity, EditText editTextNote, String idCard, ImageView saving){
        ActivityHelper activityHelper = new ActivityHelper(activity);
        iniciarAutoSalvar(editTextNote, idCard, saving);
    }

    public static void iniciarAutoSalvar(EditText editTextNote, String idCard, ImageView saving) {
        autosaveHandler = new Handler(Looper.getMainLooper());
        autosaveRunnable = new Runnable() {
            @Override
            public void run() {
                String textoAtual = editTextNote.getText().toString();

                if (!textoAtual.equals(ultimoTextoSalvo)) {
                    ultimoTextoSalvo = textoAtual;
                    saveCard(idCard, textoAtual, saving);
                }

                autosaveHandler.postDelayed(this, 15000);
            }
        };
        autosaveHandler.postDelayed(autosaveRunnable, 15000);
    }

    public static void pararAutoSalvar() {
        if (autosaveHandler != null) {
            autosaveHandler.removeCallbacks(autosaveRunnable);
        }
    }

    public static void saveCard(String idCard, String textoDigitado, ImageView saving){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.yuriesteves.x-br.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebAPIService apiService = retrofit.create(WebAPIService.class);

        Call<CardResponse> call = apiService.updateCard(idCard, textoDigitado);

        call.enqueue(new Callback<CardResponse>() {
            @Override
            public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                if(response.isSuccessful()){
                    Date horaAtual = new Date();
                    String horaFormatada = horaAtual.toString();

                    Log.d("TAG", "CARD ATUALIZADO COM SUCESSO " + response.body().getMessage().toString() + "HORARIO ATUAL SAVE " + horaFormatada);

                    saving.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            saving.setVisibility(View.GONE);
                        }
                    }, 1500);
                }
            }

            @Override
            public void onFailure(Call<CardResponse> call, Throwable t) {
                Log.d("TAG", "ERRO AO ATUALIZAR CARD " + t.getMessage().toString());
            }
        });
    }
}
