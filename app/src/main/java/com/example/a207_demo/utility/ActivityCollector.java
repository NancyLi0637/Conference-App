package com.example.a207_demo.utility;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * ActivityCollector collects all the activities together
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    /**
     * addActivity to the collector
     * @param activity activity to add
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * remove activity from the collector
     * @param activity activity to remove
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * ActivityCollector finishes all the unfinished activities
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }
}
