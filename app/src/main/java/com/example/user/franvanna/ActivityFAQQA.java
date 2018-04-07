package com.example.user.franvanna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityFAQQA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqq);

        getSupportActionBar().setTitle(R.string.vt_faq);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tvQ = findViewById(R.id.tvFAQQAQuest);
        TextView tvR = findViewById(R.id.tvFAQQARep);

        Bundle data = getIntent().getExtras();

        tvQ.setText(data.getString(ActivityFAQ.KEY_FAQ_Q));
        tvR.setText(data.getString(ActivityFAQ.KEY_FAQ_R));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return true;
    }
}
