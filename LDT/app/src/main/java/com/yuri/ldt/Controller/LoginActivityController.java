package com.yuri.ldt.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.material.textfield.TextInputEditText;
import com.yuri.ldt.Controller.Helpers.AndroidHelper;
import com.yuri.ldt.Model.Server.User.UserResponse;
import com.yuri.ldt.Controller.Interfaces.WebAPIService;
import com.yuri.ldt.Model.UserModel;
import com.yuri.ldt.View.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivityController {
    public LoginActivityController(){}

    public void verifDados(Context context, TextInputEditText senhaInput2, TextInputEditText senhaInput, TextInputEditText emailInput){
        Log.d("TAG", "Senha2: " + senhaInput2.getText().toString() + " Senha: " + senhaInput.getText().toString() + " Email: " + emailInput.getText().toString());
        String senha = senhaInput.getText().toString();
        String senha2 = senhaInput2.getText().toString();
        String email = emailInput.getText().toString();

        if(senha.equals(senha2) && !email.isEmpty()){
            verificarEmail(context, email, senha);
        }
    }

    public void verificarEmail(Context context, String email, String senha){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.yuriesteves.x-br.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebAPIService apiService = retrofit.create(WebAPIService.class);

        Call<UserResponse> call = apiService.getUserData("NA", email, senha);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    UserResponse userResponse = response.body();

                    UserModel user = userResponse.getData();

                    SharedPreferences.Editor editor = context.getSharedPreferences("user", Context.MODE_PRIVATE).edit();
                    editor.putString("idUsu", user.getId());
                    editor.apply();

                    AndroidHelper.changeIntent(context, MainActivity.class);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

}
