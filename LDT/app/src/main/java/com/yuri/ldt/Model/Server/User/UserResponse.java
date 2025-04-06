package com.yuri.ldt.Model.Server.User;

import com.google.gson.annotations.SerializedName;
import com.yuri.ldt.Model.UserModel;

public class UserResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("dados")
    private UserModel data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public UserModel getData() {
        return data;
    }
}
