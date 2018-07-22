package com.koziengineering.user.franvanna.Objects;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Locale;

public class SMS implements Comparable  {

    public static final String KEY_ID = "_id";
    public static final String KEY_THREAD_ID = "thread_id";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_PERSON = "person";
    public static final String KEY_DATE = "date";
    public static final String KEY_DATE_SENT = "date_sent";
    public static final String KEY_PROTOCOL = "protocol";
    public static final String KEY_READ = "read";
    public static final String KEY_STATUS = "status";
    public static final String KEY_TYPE = "type";
    public static final String KEY_REPLY_PATH_PRESENT = "reply_path_present";
    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_BODY = "body";
    public static final String KEY_SERVICE_CENTER = "service_center";
    public static final String KEY_LOCKED = "locked";
    public static final String KEY_SUB_ID = "sub_id";
    public static final String KEY_PHONE_ID = "phone_id";
    public static final String KEY_ERROR_CODE = "error_code";
    public static final String KEY_CREATOR = "creator";
    public static final String KEY_SEEN = "seen";
    public static final String KEY_PRIORITY = "priority";
    public static final String KEY_M_SIZE = "m_size";
    public static final String KEY_OPPO_DRAFTS = "oppo_drafts";
    public static final String KEY_OPPO_MASS = "oppo_mass";
    public static final String KEY_OPPO_TIMER = "oppo_timer";
    public static final String KEY_OPPO_GROUPADDRESS = "oppo_groupaddress";
    public static final String KEY_OPPO_COLLECTED = "oppo_collected";
    public static final String KEY_OPPO_SUB_DATE = "oppo_sub_date";
    public static final String KEY_OPPO_SERVICE_MESSAGE_SMS_TYPE = "oppo_service_message_sms_type";
    public static final String KEY_BUBBLE = "bubble";
    public static final String KEY_DELETED = "deleted";
    public static final String KEY_SYNC_STATE = "sync_state";
    public static final String KEY_SYNC_ID = "sync_id";
    public static final String KEY_OPPO_MESSAGE_URL = "oppo_message_url";
    public static final String KEY_OPPO_SMS_TYPE = "oppo_sms_type";
    public static final String KEY_BLOCK_TYPE = "block_type";
    public static final String TAG = "XXX";
    public static final String KEY_SMS_LIST = "smsList";
    public static final int MESSAGE_PREVIEW_LIMIT = 50;
    public static final String KEY_BOX = "box";

    private Bundle data;
    public void setBox(String box){
        data.putString(KEY_BOX , box);
    }


    public SMS(){

    }

    public Bundle getData() {
        return data;
    }

    public String mobile(){
        return data.getString(SMS.KEY_ADDRESS);
    }

    public SMS(Bundle newData){
        this.data = newData;
    }

    @Override
    public String toString() {
        String str = "\n----SMS----\n";
        str = str.concat("Box : " + data.getString(KEY_BOX) + "\n");
        str = str.concat("Numero : " + data.getString(KEY_ADDRESS) + "\n");
        str = str.concat("Message : " + data.getString(KEY_BODY) + "\n");
        //str = str.concat("Data : " + data.getString(KEY_DATE) + "\n");
        str = str.concat("Date : " + formattedDate() + "\n");
        str = str.concat("----------\n");

       // Log.e(TAG, str );
        return str;
    }



    public String formattedDate(){

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(data.getString(KEY_DATE)));
        String date = android.text.format.DateFormat.format("dd-MM-yyyy hh:mm a", cal).toString();
        return date;
    }

    public JSONObject toJSONObject() throws JSONException {

        return Utils.BundleToJSONObject(data);
    }

    public Bundle toBundle(){
        return data;
    }

    public String getProperty(String KEY){
        return data.getString(KEY);
    }

    public void setProperty(String KEY, String value){
        data.putString(KEY, value);
    }


    public static SMS FromJSONObject(JSONObject smsObject) throws JSONException {
        return new SMS(Utils.JSONObjectToBundle(smsObject));
    }

    public String previewMessage() {

        return Utils.Ellipsize(data.getString(KEY_BODY),SMS.MESSAGE_PREVIEW_LIMIT);
    }

    public String box() {

        return data.getString(KEY_BOX);
    }

    public long dateLong(){
        return Long.parseLong(data.getString(KEY_DATE));
    }


    @Override
    public int compareTo(@NonNull Object o) {
        //long date =
        SMS smsCompatre = (SMS) o;
        if(dateLong() > smsCompatre.dateLong()){
            return 1;
        }else if(dateLong() < smsCompatre.dateLong()){
            return -1;
        }else{
            return 0;
        }
    }

    public boolean isInbox() {
        return box() == SMSReader.SMS_BOX_INBOX;
    }


    public String message() {
        return data.getString(KEY_BODY);
    }

    public void setMessage(String msg) {
        data.putString(KEY_BODY,msg);
    }
}
