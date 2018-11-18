package com.koziengineering.user.franvanna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.koziengineering.docta.pcone.R;

public class ActivityBigImageView extends AppCompatActivity {

    private static final String TAG = "CENI2018";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolable_view);


        String windowTitle = "CENI 2018";

        String curWindowTitle = getIntent().getStringExtra(DataDisplay.CUR_WINDOW_TITLE);

        if(curWindowTitle != null){
            windowTitle = curWindowTitle;
        }

        Log.e(TAG, "onCreate: curwititle : " + curWindowTitle );

        getSupportActionBar().setTitle(curWindowTitle);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;



        if(item.getItemId() == android.R.id.home){
            finish();
        }



        return super.onOptionsItemSelected(item);
    }
}
