package hr.dario.musicwebservice.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import hr.dario.musicwebservice.db.model.DbRecordings;

@Dao
public interface RecTable {

    @Query("SELECT * FROM DbRecordings ORDER BY id")
    List<DbRecordings> selectAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DbRecordings recordings);

    @Delete
    void delete(DbRecordings recordings);


}
