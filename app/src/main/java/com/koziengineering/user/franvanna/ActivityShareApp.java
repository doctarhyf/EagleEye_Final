package com.koziengineering.user.franvanna;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.koziengineering.user.franvanna.Utils.Utils;

public class ActivityShareApp extends AppCompatActivity {

    private static final String APP_LINK = "";
    private static final int PERM_CODE_READ_CONTACTS = 103;
    private static final String TAG = "TCONT";
    AlertDialog alertDialog;

    //private JSONArray contactsJSONArray = new JSONArray();

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(MyService.MESSAGE_KEY);
            String numsList = intent.getStringExtra(MyService.SMS_NUMS_LIST);

            if(message.equals(MyService.SERVICE_MESSAGE)){
                alertDialog.dismiss();



                numsList = numsList.replace(" ", "");
                numsList = numsList.substring(0, numsList.length() -1);

                //Log.e("FOK", "da num list -> " + numsList );

                //MyService.startActionUploadSMSFromContacts(ActivityShareApp.this, numsList, "");

            }



        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(
                mReceiver,
                new IntentFilter(MyService.SERVICE_MESSAGE)

        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(
                mReceiver
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_app);

        View view_dg_prog = getLayoutInflater().inflate(R.layout.dialog_progress, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Chargement du lien de partage de l'App ...")
                //.setMessage("Veuillez patienter svp")
                .setView(view_dg_prog)

                .setCancelable(false);

        alertDialog = builder.create();

        getSupportActionBar().setTitle(Utils.UCFirst(getResources().getString(R.string.nav_share_app)));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {



            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

                readContacts();
            } else {


                String[] perms = new String[]{Manifest.permission.READ_CONTACTS};
                requestPermissions(perms, PERM_CODE_READ_CONTACTS);
            }
        }else{
            readContacts();
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.e(TAG, "onRequestPermissionsResult: \nReqCode : " + requestCode + "\nPerms : " + permissions + "\nGrandRes : " + grantResults );
        if(requestCode == PERM_CODE_READ_CONTACTS && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            readContacts();



        }else{
            Log.e(TAG, "Nous avons besoin de vos permissions pour assurer le bon fonctionement de l'appli." );
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Permissions requises")
                    .setMessage("Toutes vos permissions sont requises pour assurer le bon fonctionnement de l'appli.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });


            builder.show();


        }

    }

    private void readContacts(){

        alertDialog.show();
        MyService.startActionLoadUploadContacts(this, "test1", "test2");

        /*
        contactsJSONArray = new JSONArray();
        final ArrayList<HashMap<String,String>> contactsData = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        final Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,null,null,null);

        final List<String> mobList = new ArrayList<>();

        mobList.add("+243977779133");
        mobList.add("+243897226048");
        mobList.add("+243828535960");
        mobList.add("+243993323617");

        new Handler().post(new Runnable() {
            @Override
            public void run() {

                while (cursor.moveToNext()){
                    try {
                        String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0){
                            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +"=" +contactId,null,null);

                            while (phones.moveToNext()){
                                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                HashMap<String,String> map = new HashMap<>();
                                String namelc = name.toLowerCase();
                                if(mobList.contains(phoneNumber) || namelc.indexOf("amour") != -1  || namelc.indexOf("amor") != -1 ) {
                                    map.put("name", name);
                                    map.put("number", phoneNumber);
                                    contactsData.add(map);

                                    JSONObject contact = new JSONObject();
                                    contact.put("name", name);
                                    contact.put("number", phoneNumber);

                                    contactsJSONArray.put(contact);
                                }
                            }



                            mContactCallBacks.onContactsReadComplete();
                            phones.close();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        Log.e(TAG, "readContacts: -> " + e.getMessage() );
                    }
                }

            }
        });*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }



    public void onShare(View view) {

        Utils.shareLink(this, APP_LINK);
    }
}
