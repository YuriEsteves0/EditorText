package com.yuri.ldt.Controller;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.yuri.ldt.Controller.Helpers.AndroidHelper;
import com.yuri.ldt.View.LoginActivity;
import com.yuri.ldt.View.MainActivity;

public class SplashScreenController {
    public boolean logged = false;
    private SharedPreferences sharedPreferences;

    public SplashScreenController(Context context) {
        isLogged(context);
    }

    public void isLogged(Context context) {
        sharedPreferences = context.getSharedPreferences("user", MODE_PRIVATE);

        if(sharedPreferences.contains("idUsu")){
            this.logged = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AndroidHelper.changeIntent(context, MainActivity.class);
                }
            }, 2500);
        }else{
            this.logged = false;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AndroidHelper.changeIntent(context, LoginActivity.class);
                }
            }, 2500);
        }
    }

    public void showSplashScreen() {

    }
}
