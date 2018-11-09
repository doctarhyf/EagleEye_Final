package com.koziengineering.docta.pcone.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

import com.koziengineering.docta.pcone.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Utils {

    private static final String NO_VALUE = "NO_VALUE";
    public static final String CENI_PREF_NAME = "CENI_PREF";
    public static final String VOTE_KEY_CAND_PREZ = "candPrez";
    private static final int NO_CANDIDATE = 0xbeef;
    public static final String VOTE_KEY_CAND_DEP_NAT = "candDepNat";
    public static final String VOTE_KEY_CAND_DEP_PROV = "candDepProv";
    public static final int REQ_CODE = 1001;
    public static final String PLAYSTORE_URL = "http://jtminvestment.com/ceni_beta.apk";
    private static final String TAG = "UTILS";
    public static final String KEY_SCREEN_DENSITY = "screenDensity";
    public static final String KEY_NEWS_ID = "newsId";
    public static final String KEY_NEWS_URL = "newsUrl";
    public static final String NO_PROMP_TEXT_DATA = "No promp data";
    private static final String NO_DATA = "no_data";

    public static  boolean isOnline(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }

    public static String UCFirst(String string){


        return string.substring(0,1).toUpperCase()+string.substring(1);

    }

    public static String getDateDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);


        return " J - " + elapsedDays + "    " + Utils.addLeadingZero(elapsedHours) + ":" + Utils.addLeadingZero(elapsedMinutes) + ":" + Utils.addLeadingZero(elapsedSeconds) + "\"";

    }

    private static String addLeadingZero(long num) {

        String lz = "";

        if(num < 10){
            lz = lz.concat("0" + num);
        }else{
            lz = "" + num;
        }

        return lz;
    }


    // TODO: 07/04/2018 Utils.getResIdByName("pt_kozi", R.drawable.class);

    public static int getResIdByName(String resName, Class<?> c){
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        }catch (Exception e){
            throw new RuntimeException("No resource ID found for Da REZ -> "
            + resName + " " + c, e);
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

    public static void saveIntValue(Context context, String key, int val){
        SharedPreferences preference = context.getSharedPreferences(CENI_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();

        editor.putInt(key, val);
        editor.commit();
    }



    public static void setPrompTextListData(Context context, String key, String val){

        SharedPreferences preference = context.getSharedPreferences(CENI_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();

        editor.putString(key, val);
        editor.commit();

    }

    public static String getPrompTextListData(Context context, String key, String val){

        SharedPreferences preference = context.getSharedPreferences(CENI_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();



        return preference.getString(key, NO_PROMP_TEXT_DATA);

    }

    public static void putStringData(Context context, String key, String data) {

        SharedPreferences preference = context.getSharedPreferences(CENI_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();

        editor.putString(key, data);
        editor.commit();

    }

    public static String getStringData(Context context, String key, String data) {
        SharedPreferences preference = context.getSharedPreferences(CENI_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();



        return preference.getString(key, NO_DATA);
    }

    public static int getCandFromPref(Context context, String key ){

        SharedPreferences preference = context.getSharedPreferences(CENI_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();

        return preference.getInt(key, -1);

    }

    public static int getIntValue(Context context, String key){
        SharedPreferences preference = context.getSharedPreferences(CENI_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();

        return preference.getInt(key, NO_CANDIDATE);
    }

    public static boolean clearCandidatesData(Context context){


        SharedPreferences preference = context.getSharedPreferences(CENI_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();

        editor.clear();
        //editor.apply();

        return  preference.getAll().size() == 0;
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static  void shareImage(Context context, Bitmap bitmap,String text) {
        //bitmap is ur image and text is which is written in edtitext
        //you will get the image from the path
        String pathofBmp =
                MediaStore.Images.Media.insertImage(context.getContentResolver(),
                        bitmap, "title", null);
        Uri uri = Uri.parse(pathofBmp);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Star App");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(shareIntent, context.getResources().getString(R.string.strShareResultSim)));

    }

    public static Bitmap getBitmapFromView(View view) {

        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
//Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
//Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
//has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
//does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
// draw the view on the canvas
        view.draw(canvas);
//return the bitmap
        return returnedBitmap;
    }

    public static void replaceFragmentWithAnimation(AppCompatActivity activity,int idFragCont, android.support.v4.app.Fragment fragment, String tag){
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.exit_to_right, R.anim.exit_to_left);
        transaction.replace(idFragCont, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public static void toggleTorchMode(Context context, CameraManager cameraManager, boolean torchModeOn) {


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                String cameraId = cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(cameraId, torchModeOn);

            } catch (CameraAccessException e) {

                Log.e(TAG, "toggleTorchMode: ");
            }
        }else{

        }


    }

    /*public static Point getScreenSize(AppCompatActivity activity){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        return new Point(width, height);

    }*/

    public static int getScreenDensity(AppCompatActivity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.densityDpi;

        /*{
            case DisplayMetrics.DENSITY_LOW:
                break;

            case DisplayMetrics.DENSITY_MEDIUM:
                break;

            case DisplayMetrics.DENSITY_HIGH:
                break;
        }*/
    }

    public static Point getScreenSize(AppCompatActivity activity){

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        try {
            display.getRealSize(size);
        } catch (NoSuchMethodError err) {
            display.getSize(size);
        }
        int width = size.x;
        int height = size.y;

        return size;
    }

    public static AlertDialog getNoConnDialog(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.strNoConn))
                .setMessage(context.getResources().getString(R.string.strNoConnMsg))
                .setNegativeButton("OK", null);

        return builder.create();
    }


    public static String readTextFromResource(Context context, int resId) {



            InputStream raw = context.getResources().openRawResource(resId);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            int i;
            try{
                i = raw.read();
                while(i != -1)
                {
                    stream.write(i);
                    i = raw.read();
                }

                raw.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            return stream.toString();


    }

    public static void showDialogWithMessage(Context context, boolean show, String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });



        AlertDialog alertDialog = builder.create();

        if(show) alertDialog.show();


    }


}
