package com.example.waagrechterwurf;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Wert.class}, version = 1, exportSchema = false)
public abstract class WertRoomDatabase extends RoomDatabase {

    public abstract WertDao wertDao();
    private static WertRoomDatabase INSTANCE;

    static WertRoomDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                    WertRoomDatabase.class,"wert_database").build();
        }
        return INSTANCE;
    }
}
