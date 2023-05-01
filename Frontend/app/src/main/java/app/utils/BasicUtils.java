package app.utils;

import android.app.Activity;

import java.util.ArrayList;

/**
 * This class is for basic utility for any activity.
 */
public class BasicUtils {
    /**
     * This method checks if the user has valid fields before attempting a login.
     * @param field An arrayList of all fields.
     * @return true if no empty fields, else false
     */
    public static boolean isValidField(ArrayList<String> field) {
        for (String str: field) {
            if (str == null || str.length() == 0) {
                return true;
            }
        }
        return false;
    }
}
