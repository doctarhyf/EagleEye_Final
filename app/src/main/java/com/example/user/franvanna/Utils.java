package com.example.user.franvanna;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class Utils {

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


}
