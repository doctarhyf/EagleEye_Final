package com.koziengineering.user.franvanna;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.koziengineering.docta.pcone.R;
import com.koziengineering.user.franvanna.Utils.Utils;

public class ActivityListElectorral extends AppCompatActivity  {

    private static final String TAG = "CENI";
    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_electorral);

        alertDialog = Utils.getNoConnDialog(this);

        getSupportActionBar().setTitle("Candidats");
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

        }else{

            Utils.showDialogWithMessage(this, true, getResources().getString(R.string.strOptTitleNotAvail),
                    getResources().getString(R.string.strOptMsgNotAvail));

        }
    }
}
