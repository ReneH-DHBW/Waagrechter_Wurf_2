package com.example.waagrechterwurf;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class WertListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Wert> werts = Collections.emptyList();
    private final WertDao dao;
// Konstruktor für Delete
    public WertListAdapter(WertDao dao){
        this.dao = dao;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wert_list_item, parent,false);
                return new WertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        TextView wordView = holder.itemView.findViewById(R.id.list_item_wert);
        wordView.setText(werts.get(position).getWert());
// Für Delete
        wordView.setOnClickListener((view) -> {
            new DeleteWordTask(dao, this).execute(werts.get(position));
        });
    }

    @Override
    public int getItemCount(){return werts.size();}

    public void setWerts(List<Wert> werts){
        this.werts = werts;
        notifyDataSetChanged();
    }
//Für Delete
    static class DeleteWordTask extends AsyncTask<Wert, Void, List<Wert>> {

        private final WertDao dao;
        private final WertListAdapter adapter;

        public DeleteWordTask(WertDao dao, WertListAdapter adapter){
            this.dao = dao;
            this.adapter = adapter;
        }

        @Override
        protected List<Wert> doInBackground(Wert... werts) {
            dao.delete(werts[0]);
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Wert> werts){
            super.onPostExecute(werts);
            adapter.setWerts(werts);
        }
    }

    public class WertViewHolder extends RecyclerView.ViewHolder{

        public WertViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

}
