package com.yuri.ldt.Controller;

import com.yuri.ldt.Model.CardModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityController {
    private List<CardModel> cardModelList = new ArrayList<>();
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
}
