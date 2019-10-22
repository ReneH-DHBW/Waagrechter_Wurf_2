package com.example.waagrechterwurf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Activity4_Tabelle extends AppCompatActivity {

    private WertDao dao;
    private RecyclerView recyclerView;
    private WertListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity4__tabelle);

        dao = WertRoomDatabase.getDatabase(this).wertDao();

        recyclerView = findViewById(R.id.wert_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new WertListAdapter(dao);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onResume(){
        super.onResume();
        new LadeWertTask().execute();
    }

    //Die Liste laden
    class LadeWertTask extends AsyncTask<Void, Void, List<Wert>>{
        @Override
        protected List<Wert> doInBackground(Void... voids){
            return dao.getAll();
        }
        @Override
        protected void onPostExecute(List<Wert> werts){
            super.onPostExecute(werts);
            adapter.setWerts(werts);
        }
    }




}