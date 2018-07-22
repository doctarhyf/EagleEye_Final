package com.koziengineering.user.franvanna.Objects;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SMSReader {


    private static final String TAG = "SRDR";
    public static String SMS_BOX_INBOX = "inbox";
    public static String SMS_BOX_SENT = "sent";
    private final Context context;

    public SMSReader(Context context) {

        this.context = context;
    }


    public interface SMSListener {
        void onSMSReadComplete(Map<String, List<SMS>> smsMap, String smBox);

        void onSMSInboxEmpty();
    }

    public void readSMS(final SMSListener mSMSListener, final String SMS_BOX) {

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                //Log.e(TAG, "run: "  );


                Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/" + SMS_BOX), null, null, null, null);


                Map<String, List<SMS>> smsMap = new HashMap<>();
                if (cursor.moveToFirst()) { // must check the result to prevent exception

                    int i = 0;
                    Bundle bundle;
                    do {

                        bundle = new Bundle();
                        List<String> keysList = new ArrayList<>();
                        keysList.add(SMS.KEY_ADDRESS);
                        keysList.add(SMS.KEY_BODY);
                        keysList.add(SMS.KEY_DATE);
                        keysList.add(SMS.KEY_BOX);

                        for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                            String key = cursor.getColumnName(idx);
                            String val = cursor.getString(idx);

                            //if (keysList.contains(key)) {

                                bundle.putString(key, val);
                            //}


                        }
                        bundle.putString(SMS.KEY_BOX, SMS_BOX);
                        SMS sms = new SMS(bundle);

                        if(SMS_BOX == SMSReader.SMS_BOX_SENT){
                            String msg = sms.message();
                            sms.setMessage("Me -> " + msg);
                        }


                        List<SMS> smsList = smsMap.get(sms.mobile());

                        if (smsList == null) {
                            Log.e(TAG, "run: isNull");
                            smsList = new ArrayList<>();
                            smsList.add(sms);
                            smsMap.put(sms.mobile(), smsList);

                        } else {
                            Log.e(TAG, "notNull");
                            smsList.add(sms);

                        }


                        //if(sms.mobile() == "+243976362208") {
                           // Log.e("FAK", sms.box());
                        //}

                        i++;
                    } while (cursor.moveToNext());

                    mSMSListener.onSMSReadComplete(smsMap, SMS_BOX);
                } else {
                    mSMSListener.onSMSInboxEmpty();
                }

            }



});

    }
}
