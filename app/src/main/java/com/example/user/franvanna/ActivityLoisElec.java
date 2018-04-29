package com.example.user.franvanna;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.user.franvanna.Utils.Utils;

public class ActivityLoisElec extends AppCompatActivity {

    WebView wbLoisElec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lois_elec);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Loi Electorale 2011");


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wbLoisElec= findViewById(R.id.wvLoisElec);

        wbLoisElec.setInitialScale(1);
        wbLoisElec.getSettings().setJavaScriptEnabled(true);
        wbLoisElec.getSettings().setLoadWithOverviewMode(true);
        wbLoisElec.getSettings().setUseWideViewPort(true);
        wbLoisElec.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        wbLoisElec.setScrollbarFadingEnabled(false);
        wbLoisElec.loadDataWithBaseURL(null, Utils.readTextFromResource(this,R.raw.html_page_lois_elec), "text/html", "utf-8", null);


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

}
