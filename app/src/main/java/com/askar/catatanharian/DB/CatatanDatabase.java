package com.askar.catatanharian.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.askar.catatanharian.DB.DAO.CatatanDao;
import com.askar.catatanharian.DB.Entity.Catatan;

@Database(entities = {Catatan.class}, version = 1)
public abstract class CatatanDatabase extends RoomDatabase {

    public abstract CatatanDao catatanDao();

    public final static String DATABASE_NAME = "db_catatan_harian";

}
