package com.example.user.franvanna;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.franvanna.Adapters.AdapterListVotesTypes;
import com.example.user.franvanna.Objects.VoteType;
import com.example.user.franvanna.Utils.Utils;

import java.util.ArrayList;

public class ActivityListElectorral extends AppCompatActivity  {

    private static final String TAG = "CENI";
    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_electorral);

        alertDialog = Utils.getNoConnDialog(this);

        getSupportActionBar().setTitle("Liste electorale");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (!Utils.isOnline(this)){


            if(alertDialog != null) {
                alertDialog.show();
            }

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId() == android.R.id.home){
            finish();
        }




        return super.onOptionsItemSelected(item);
    }

    public void onCheckListElec(View view) {

        Log.e(TAG, "onCheckListElec: " );

        if (!Utils.isOnline(this)){


            if(alertDialog != null) {
                alertDialog.show();
            }

        }
    }
}
