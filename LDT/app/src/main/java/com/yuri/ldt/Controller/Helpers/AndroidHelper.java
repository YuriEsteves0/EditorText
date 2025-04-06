package com.yuri.ldt.Controller.Helpers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AndroidHelper {
    public static void changeIntent(Context context, Class<?> cls){
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void logs(String Message){
        Log.d("PAGINA", Message);
    }
}
