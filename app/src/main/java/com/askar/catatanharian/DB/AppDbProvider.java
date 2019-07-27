package com.askar.catatanharian.DB;

import android.content.Context;

import androidx.room.Room;

public class AppDbProvider {
    private static CatatanDatabase instance;
    private static CatatanDatabase asynchronusInstance;

    public static CatatanDatabase getInstance(Context context){
        if (AppDbProvider.instance == null){
            AppDbProvider.instance = Room.databaseBuilder(
                    context, CatatanDatabase.class, instance.DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return AppDbProvider.instance;
    }

    public static CatatanDatabase getAsynchronusInstance(Context context){
        if (asynchronusInstance == null){
            asynchronusInstance = Room.databaseBuilder(
                    context, CatatanDatabase.class, instance.DATABASE_NAME).build();
        }
        return asynchronusInstance;
    }
}
