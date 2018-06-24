package com.koziengineering.user.franvanna;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.koziengineering.user.franvanna.Utils.Utils;

public class ActivityAbout extends AppCompatActivity {

    private static final String TAG = "EE";
    private static final int MAX_CLICK_NUM = 4;

    ImageView ivCeni;
    private int ivCeniClickCound = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.title_about));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivCeni = findViewById(R.id.iv_ceni);




    }

    @Override
    protected void onResume() {
        super.onResume();

        ivCeniClickCound = 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

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

    private AlertDialog alertDialog = null;

    public void onIvCeniClicked(View view) {

        Log.e(TAG, "onIvCeniClicked: IV CENI CLICK COUNT -> " + ivCeniClickCound );

        ivCeniClickCound ++;

        if(ivCeniClickCound == MAX_CLICK_NUM){

            //Toast.makeText(this, getResources().getString(R.string.txt_more_clicks_for_thanks), Toast.LENGTH_SHORT).show();

            View dialogView = getLayoutInflater().inflate(R.layout.layout_dialog_thanks, null);
            WebView wvThanks = dialogView.findViewById(R.id.wvThanks);

            wvThanks.setInitialScale(1);
            wvThanks.getSettings().setJavaScriptEnabled(true);
            wvThanks.getSettings().setLoadWithOverviewMode(true);
            wvThanks.getSettings().setUseWideViewPort(true);
            wvThanks.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            wvThanks.setScrollbarFadingEnabled(false);
            wvThanks.loadDataWithBaseURL(null, Utils.readTextFromResource(this,R.raw.html_page_thanks), "text/html", "utf-8", null);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialogView);
            builder.setPositiveButton(getResources().getString(R.string.txtOk), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });



            alertDialog = builder.show();
            ivCeniClickCound = 0;

        }
    }
}
