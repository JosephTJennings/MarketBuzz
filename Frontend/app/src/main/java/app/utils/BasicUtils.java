package app.utils;

import android.app.Activity;

import java.util.ArrayList;

public class BasicUtils {

    public static boolean isValidField(ArrayList<String> field) {
        for (String str: field) {
            if (str == null || str.length() == 0) {
                return true;
            }
        }
        return false;
    }
}
