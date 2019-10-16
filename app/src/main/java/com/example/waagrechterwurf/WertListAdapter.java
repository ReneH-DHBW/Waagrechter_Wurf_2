package com.example.waagrechterwurf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class WertListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Wert> werts = Collections.emptyList();

    class WertViewHolder extends RecyclerView.ViewHolder{
        public WertViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    WertListAdapter(){

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
    }

    @Override
    public int getItemCount(){return werts.size();}

    public void setWerts(List<Wert> werts){
        this.werts = werts;
        notifyDataSetChanged();
    }

}
