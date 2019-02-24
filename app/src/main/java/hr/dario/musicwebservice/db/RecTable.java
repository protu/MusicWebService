package hr.dario.musicwebservice.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import hr.dario.musicwebservice.db.model.DbRecording;

@Dao
public interface RecTable {

    @Query("SELECT * FROM DbRecording ORDER BY id")
    List<DbRecording> selectAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DbRecording recordings);

    @Delete
    void delete(DbRecording recordings);


}
