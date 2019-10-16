package com.example.waagrechterwurf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2_Anzeige extends Activity {
// TextViews draußen deklarieren
    protected TextView textViewHoehe = null;
    protected TextView textViewV = null;
    protected TextView textViewErgebnis = null;

    protected Button zur_hilfe_seite;
    protected Button speichern;
    protected Button weiter_zur_tabelle;

    private WertDao dao;

    double weite = 0;
    double gravitation = 9.81;
    double wertHoeheVA1;
    double wertVVA1;

    String wertHoeheVA1_String;
    String wertVVA1_String;
    String weiteString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2__anzeige);

        dao = WertRoomDatabase.getDatabase(this).wertDao();

        textViewHoehe = findViewById(R.id.hoehe_anzeigen);
        textViewV = findViewById(R.id.v_anzeigen);
        textViewErgebnis = findViewById(R.id.ergebnis_anzeigen);

        zur_hilfe_seite = findViewById(R.id.zur_hilfe_seite);
        zur_hilfe_seite.setOnClickListener(view ->{
            Intent intent = new Intent(this, Activity3_Hilfe.class);
            startActivity(intent);
                });
        speichern = findViewById(R.id.speichern);
        speichern.setOnClickListener((view)->{saveWertOnClick();
                });
        weiter_zur_tabelle = findViewById(R.id.weiter_zur_Tabelle);
        weiter_zur_tabelle.setOnClickListener((view)->{
            Intent intent = new Intent(this, Activity4_Tabelle.class);
            startActivity(intent);
        });

//Darstellung der Zahlen auf die TextViews aus dem erstellen Intent von der vorherigen Seite
        Intent intent = getIntent();
        wertHoeheVA1 = intent.getDoubleExtra("wert_hoehe", 0);
        wertHoeheVA1_String = String.valueOf(wertHoeheVA1);
        textViewHoehe.setText(wertHoeheVA1_String);

        wertVVA1 = intent.getDoubleExtra("wert_v", 0);
        wertVVA1_String = String.valueOf(wertVVA1);
        textViewV.setText(wertVVA1_String);
// Berechnung und Darstellung der Werte
        weite = Math.round((wertVVA1 * Math.sqrt((2 * wertHoeheVA1) / gravitation))*100.0) / 100.0;
        weiteString = String.valueOf(weite);
        textViewErgebnis.setText(weiteString);

    }

    class SpeichernTask extends AsyncTask<Wert, Void, Void>{
        @Override
        protected Void doInBackground(Wert... werts){
            dao.insert(werts[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
        }
    }
    private void saveWertOnClick(){
        EditText wert = findViewById(R.id.eingegebene_hoehe_test);
        if (!wert.getText().toString().isEmpty()){
            new SpeichernTask().execute(new Wert(wert.getText().toString()));
        }
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, wert.getText().toString(), Toast.LENGTH_LONG);
        toast.show();
    }
}
