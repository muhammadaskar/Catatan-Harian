package com.askar.catatanharian.DB.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.askar.catatanharian.DB.Entity.Catatan;

import java.util.List;

@Dao
public interface CatatanDao {
    @Query("Select * From Catatan")
    List<Catatan> getAll();

    @Query("Select * From Catatan Where id = :id Limit 1")
    Catatan selectDetailCatatan(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAll(Catatan catatan);

    @Delete
    int delete(Catatan catatan);

    @Update
    int update(Catatan catatan);
}
