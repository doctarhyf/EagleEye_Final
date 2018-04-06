package com.example.user.franvanna;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityFAQ extends AppCompatActivity implements AdapterFAQQuestion.Listener {

    private static final String TAG = "CENI";
    private static final String KEY_FAQ_Q = "question";
    private static final String KEY_FAQ_R = "reponse";
    ListView lvFAQQuestions;
    AdapterFAQQuestion adapterFAQQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        getSupportActionBar().setTitle(R.string.vt_faq);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        lvFAQQuestions = findViewById(R.id.lvFAQQuestions);

        ArrayList<ModelFAQQuestion> questions = getFAQData();



        adapterFAQQuestion = new AdapterFAQQuestion(this, questions, this);

        lvFAQQuestions.setAdapter(adapterFAQQuestion);//new ArrayAdapter<>(this,R.layout.layout_faq_question, R.id.tvListFAQQuestions,questions));

    }

    private ArrayList<ModelFAQQuestion> getFAQData() {

        ArrayList<ModelFAQQuestion> questions = new ArrayList<>();

        for (int i = 0; i < 20; i++){

            questions.add(new ModelFAQQuestion(i, "Question " + i, "Reponse " + i));

        }


        return questions;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onQuestionClicked(ModelFAQQuestion question) {
        Log.e(TAG, "onQuestionClicked: listener");
        Intent intent = new Intent(this, ActivityFAQQA.class);
        Bundle data = new Bundle();
        data.putString(KEY_FAQ_Q, question.getQuestion());
        data.putString(KEY_FAQ_R, question.getReponse());
        startActivity(intent);
    }
}
