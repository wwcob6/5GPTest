package com.punuo.sys.sdk.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author chzjy
 * Date 2016/12/19.
 */

public class ActivityCollector {
    private static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        if (!activities.contains(activity)) {
            activities.add(activity);
        }
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void finishToFirstView() {
        for (int i = 1; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
    public static void finishToMain(){
        for (int i = 2; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
