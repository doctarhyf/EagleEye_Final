package com.example.user.franvanna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityContactUs extends AppCompatActivity {


    EditText etEmail, etName, etMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        getSupportActionBar().setTitle(getResources().getString(R.string.strContactUs));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etEmail = findViewById(R.id.etCUEmail);
        etName = findViewById(R.id.etCUName);
        etMessage = findViewById(R.id.etCUMessage);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return true;
    }

    public void sendMessage(View view) {

        if (textIsEmpty(etMessage.getText().toString()) ||
                textIsEmpty(etEmail.getText().toString()) ||
                        textIsEmpty(etMessage.getText().toString()))
                {

                    Toast.makeText(this, "Veuillez remplir tous les champs avant d'envoyer votre message", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Nom : " + etName.getText().toString() + "" +
                    "\nEmail : " + etEmail.getText().toString() + "" +
                    "\nMessage : " + etMessage.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean textIsEmpty(String string) {
        return string.equals("");
    }
}
