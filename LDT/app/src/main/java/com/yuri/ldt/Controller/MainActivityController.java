package com.yuri.ldt.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.yuri.ldt.Controller.Helpers.ActivityHelper;
import com.yuri.ldt.Model.CardModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityController {

    private List<CardModel> cardModelList = new ArrayList<>();
    private static SharedPreferences sharedPreferences;

    public MainActivityController(Activity activity){
        ActivityHelper.finishAll();
        ActivityHelper activityHelper = new ActivityHelper(activity);
    }

    public List<CardModel> criarCardsExemplares(){
        CardModel cardModel = new CardModel();
        cardModel.setIdCard("1");
        cardModel.setIdUsuario("1");
        cardModel.setTitulo("Notas Importantes");
        cardModel.setDescricao("Descrição da nota");
        cardModel.setData("26-11-2006");

        CardModel cardModel2 = new CardModel();
        cardModel2.setTitulo("Compras");
        cardModel2.setData("05-04-2025");
        cardModel2.setDescricao("Descrição da nota 2");
        cardModel2.setIdCard("2");
        cardModel2.setIdUsuario("1");

        cardModelList.add(cardModel);
        cardModelList.add(cardModel2);

        return cardModelList;
    }

    public static void logoff(Context context){
        sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
