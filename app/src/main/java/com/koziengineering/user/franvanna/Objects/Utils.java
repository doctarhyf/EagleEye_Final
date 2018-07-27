package com.koziengineering.user.franvanna.Objects;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public class Utils {
    public static String Ellipsize(String str, int size) {

        String ret = str ;
        if(str.length() > size + 4){
            ret = str.substring(0, size) + "...";
        }

        return ret ;
    }

    public static JSONObject BundleToJSONObject(Bundle bundle) throws JSONException {

        JSONObject jsonObject = new JSONObject();

        //Set<String> keys = bundle.keySet();

        for (String key: bundle.keySet()) {

            String val = bundle.getString(key);
            jsonObject.put(key, val);


        }

        return jsonObject;
    }

    public static Bundle JSONObjectToBundle(JSONObject smsObject) throws JSONException {

        Bundle bundle = new Bundle();


        Iterator<?> keys = smsObject.keys();

        while( keys.hasNext() ) {
            String key = (String)keys.next();
            String val = (String) smsObject.get(key);
            bundle.putString(key, val);

        }

        return bundle;


    }

    public static String FormatDate(String s){

        String date = "date";
        long timestamp = Long.parseLong(s) * 1000L;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date netDate = new Date(timestamp);

            date = sdf.format(netDate);
        }

        return date;

    }

    public static String fromattedDateFromMillis(long millis) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(millis);
        String date = android.text.format.DateFormat.format("dd-MM-yyyy hh:mm a", cal).toString();
        return date;
    }
}
