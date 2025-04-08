package com.yuri.ldt.Controller.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ActivityHelper {
    private static List<Activity> activities= new ArrayList<>();

    public ActivityHelper(Activity activity){
        startActivities(activity);
    }

    public static void startActivities(Activity activity){
        addActivity(activity);
        countActivities();
    }

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void finishActivity(Activity activity) {
        activity.finish();
    }

    public static void countActivities() {
        AndroidHelper.logs("-----------------------");
        for (Activity a : activities) {
            AndroidHelper.logs(a.getClass().getSimpleName());
        }
        AndroidHelper.logs("-----------------------");
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            activity.finish();
        }
        activities.clear();
    }
}
