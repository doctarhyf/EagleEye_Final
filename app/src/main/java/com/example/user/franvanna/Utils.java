package com.example.user.franvanna;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class Utils {

    private static final String NO_VALUE = "NO_VALUE";
    public static final String CENI_PREF_NAME = "CENI_PREF";
    public static final String VOTE_KEY_CAND_PREZ = "candPrez";
    private static final int NO_CANDIDATE = 0xbeef;
    public static final String VOTE_KEY_CAND_DEP_NAT = "candDepNat";
    public static final String VOTE_KEY_CAND_DEP_PROV = "candDepProv";

    public static String UCFirst(String string){


        return string.substring(0,1).toUpperCase()+string.substring(1);

    }

    // TODO: 07/04/2018 Utils.getResIdByName("pt_kozi", R.drawable.class);

    public static int getResIdByName(String resName, Class<?> c){
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        }catch (Exception e){
            throw new RuntimeException("No resource ID found for : "
            + resName + " / " + c, e);
        }
    }

    public static int GRIDBN(String resName, Class<?> c){
        return getResIdByName(resName, c);
    }

    public static int RINT(int MAX_EXCLUSIF, int MIN_INCLUSIF){
        Random r = new Random();
        return r.nextInt(MAX_EXCLUSIF - MIN_INCLUSIF) + MIN_INCLUSIF;
    }

    public static ArrayList<View> getViewsByTag(ViewGroup root,String tag ){
        ArrayList<View> views = new ArrayList<>();
        final int childCount = root.getChildCount();

        for(int i = 0; i < childCount; i++){
            final View child = root.getChildAt(i);

            if(child instanceof  ViewGroup){
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }

            final Object tagObj = child.getTag();
            if(tagObj != null && tagObj.equals(tag)){
                views.add(child);
            }
        }

        return views;
    }

    public static void saveCandToPref(Context context, String key, int val){

        SharedPreferences preference = context.getSharedPreferences(CENI_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();

        editor.putInt(key, val);
        editor.commit();

    }

    public static int getCandFromPref(Context context, String key ){

        SharedPreferences preference = context.getSharedPreferences(CENI_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();

        return preference.getInt(key, NO_CANDIDATE);

    }

    public static boolean clearCandidatesData(Context context){


        SharedPreferences preference = context.getSharedPreferences(CENI_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();

        editor.clear();

        return  preference.getAll().size() == 0;
    }
}
