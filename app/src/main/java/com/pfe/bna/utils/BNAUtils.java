package com.pfe.bna.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import androidx.core.content.ContextCompat;

import com.android.volley.VolleyError;
import com.pfe.bna.R;
import com.pfe.bna.core.AlertBuilder;
import com.pfe.bna.core.AlertButton;
import com.pfe.bna.core.AlertCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BNAUtils {
    /**
     * This method is used to replace a specific pattern by a given value in the text
     * provided in parameter.
     * Example: All right reserved $date: the delimiter should be passed '$date' to be
     * replaced by the current year value.
     * @param text
     * @param delimiter
     * @param params
     * @return
     */
    public static String substitute(String text, String delimiter, String... params) {
        // local variable to hold the result string
        String str = text;
        /**
         * Loop to the parameters to be substituted.
         */
        for (int i = 0; i < params.length; i++) {
            /**
             * Replace strings one by one in the text
             */
            str = str.replace(delimiter, params[i]);
        }
        // return the result.
        return str;
    }

    /**
     * static method to change easily the server side base url.
     * @return
     */
    public static String getBaseUrl() {
        return "http://192.168.1.13:8081/bna";
    }

    /**
     * utility method used to format date object based on date format
     * string
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        // instantiate simple date format object.
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        // return formatted date.
        return dateFormat.format(date);
    }

    /**
     * Method used to parse date string
     * @param dateString
     * @return
     */
    public static Date parseDate(String dateString) {
        // instantiate simple date format object.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // local variable to hold dte object.
        Date date = null;
        try {
            // parse date string.
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * utility method to log error
     * @param error
     */
    public static void logError(Context context, VolleyError error) {
        System.out.println("error.networkResponse.statusCode = " + error.networkResponse.statusCode);
        if (context.getResources().getBoolean(R.bool.log_print_stack_trace)) {
            error.printStackTrace();
        }
        String alertTitle = "TODO";
        if (error.networkResponse.statusCode < 200) {

        } else if(error.networkResponse.statusCode < 300) {

        } else if(error.networkResponse.statusCode < 400) {

        }
        new AlertBuilder(context).setTitle(alertTitle)
                .setMessage("Implement custom error object")
                .rebuildButtons().setButton(AlertButton.OK)
                .getErrorAlert();
    }
}
