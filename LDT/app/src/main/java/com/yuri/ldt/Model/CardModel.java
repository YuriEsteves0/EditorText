package com.yuri.ldt.Model;

import com.google.gson.annotations.SerializedName;

public class CardModel {
    @SerializedName("idUsu")
    private String idUsuario;

    @SerializedName("nomeCard")
    private String titulo;

    @SerializedName("textoCard")
    private String descricao;

    @SerializedName("dataCriacao")
    private String data;

    @SerializedName("idCard")
    private String idCard;

    public CardModel() {}

    public CardModel(String idCard, String titulo, String descricao, String data, String idUsuario) {
        this.idCard = idCard;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.idUsuario = idUsuario;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
