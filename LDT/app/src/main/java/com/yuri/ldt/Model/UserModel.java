package com.yuri.ldt.Model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("idUsu")
    private String id;

    @SerializedName("email")
    private String email;

    @SerializedName("nome")
    private String name;

    @SerializedName("senha")
    private String password;

    public void verifCredentials(String email, String password, String password2){
        if(password.equals(password2)){

        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
