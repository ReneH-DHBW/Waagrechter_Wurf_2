package com.example.waagrechterwurf;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity implements View.OnClickListener {

//edit Texte oben deklarieren und Buttons in onCreate Kennste Ja
    protected EditText eingegebene_hoehe = null;
    protected EditText eingegebene_beschleunigung = null;
    protected EditText comment = null;

    protected Button weite_wird_berechnet;
    protected Button zur_hilfe_seite;
    protected Button hilfe_v;
    protected Button hilfe_h;
    protected Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weite_wird_berechnet = findViewById(R.id.weite_wird_berechnet);
        weite_wird_berechnet.setOnClickListener(this);

        zur_hilfe_seite = findViewById(R.id.zur_hilfe_seite);
        zur_hilfe_seite.setOnClickListener(this);

        hilfe_v = findViewById(R.id.hilfe_button_v);
        hilfe_v.setOnClickListener(this);

        hilfe_h = findViewById(R.id.hilfe_button_h);
        hilfe_h.setOnClickListener(this);

        button = findViewById(R.id.zurTabelle);
        button.setOnClickListener(this);

        eingegebene_hoehe = findViewById(R.id.eingegebene_hoehe);
        eingegebene_hoehe.addTextChangedListener(berechnenWatcher);
        eingegebene_beschleunigung = findViewById(R.id.eingegebene_beschleunigung);
        eingegebene_beschleunigung.addTextChangedListener(berechnenWatcher);
        comment = findViewById(R.id.kommentar);
    }

//Für berechnen klick. Idee dahinter, je nachdem auf welchen Button ich drücke schaut der switch welchen Case er ausführen muss.
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
// Zur Ergebnis-Seite
            case R.id.weite_wird_berechnet:
                Intent intent = new Intent(this, Activity2_Anzeige.class);
// gebe die Hoehe weiter
                Double wertHoehe = Double.parseDouble(eingegebene_hoehe.getText().toString());
                intent.putExtra("wert_hoehe", wertHoehe);
// gebe die Geschwindigkeit weiter
                Double wertV = Double.parseDouble(eingegebene_beschleunigung.getText().toString());
                intent.putExtra("wert_v", wertV);
// Kommentar
                String wertComment = comment.getText().toString();
                intent.putExtra("wert_k", wertComment);
// Zeit
                String date = getCurrentTimeStamp();
                intent.putExtra("zeit", date);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
// Zur Hilfe Seite
            case R.id.zur_hilfe_seite:
                Intent intentHilfe = new Intent(this, Activity3_Hilfe.class);
                startActivity(intentHilfe);
                break;
// Zur Tabelle
            case R.id.zurTabelle:
                Intent intentTabelle2 = new Intent(this, Activity4_Tabelle.class);
                startActivity(intentTabelle2);
                break;
// Toast erstellen für Geschwindigkeit (Hilfe)
            case R.id.hilfe_button_v:
                eventHandlerFuerButtonV();
                break;
// Toast erstellen für Geschwindigkeit (Hilfe)
            case R.id.hilfe_button_h:
                eventHandlerFuerButtonH();
                break;
        }

    }
//Der TextWatcher verhindet, dass ohne die Eingabe von Werten der Berechnen Button gedrückt werden kann
    private TextWatcher berechnenWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String hoeheString = eingegebene_hoehe.getText().toString().trim();
            String beschlString = eingegebene_beschleunigung.getText().toString().trim();
            weite_wird_berechnet.setEnabled(!hoeheString.isEmpty()&&!beschlString.isEmpty());
        }
        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

// Für den Hilfe-Button Höhe
    protected void eventHandlerFuerButtonV(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(R.string.hilfe_v);
        dialogBuilder.setPositiveButton("Okay", null);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

// Für den Hilfe-Button Beschleunigung
    protected void eventHandlerFuerButtonH(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(R.string.hilfe_h);
        dialogBuilder.setPositiveButton("Okay", null);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
// Aktuelle Zeit finden
    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
            String aktuelleZeit = dateFormat.format(new Date());

            return aktuelleZeit;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
