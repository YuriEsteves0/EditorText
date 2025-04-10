package com.yuri.ldt.Model.Server.User;

import com.google.gson.annotations.SerializedName;
import com.yuri.ldt.Model.CardModel;

import java.util.List;

public class CardResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("dados")
    private List<CardModel> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<CardModel> getData() {
        return data;
    }
}
