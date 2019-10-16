package com.example.waagrechterwurf;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WertDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert (Wert wert);

    @Query("SELECT * from Wert")
    public List<Wert>getAll();

    @Delete
    public void delete (Wert wert);
}
