package com.example.waagrechterwurf;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Wert {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String wert;

    public Wert (String wert){
        this.wert = wert;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getWert(){
        return wert;
    }
}
