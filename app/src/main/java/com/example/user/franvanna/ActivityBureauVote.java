package com.example.user.franvanna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityBureauVote extends AppCompatActivity {


    EditText etNumeroElec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bureau_vote);

        etNumeroElec = findViewById(R.id.etNumeroElec);

        getSupportActionBar().setTitle(getResources().getString(R.string.strBureauVote));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void onShowBureauVote(View view) {

        // TODO: 08/04/2018 CONNECT CENI API
        String numElec = etNumeroElec.getText().toString();
        Toast.makeText(this, "Numero : " + numElec, Toast.LENGTH_LONG).show();
    }
}
