package hr.dario.musicwebservice.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import hr.dario.musicwebservice.db.model.DbRecording;

@Database(entities = {DbRecording.class}, version = 1, exportSchema = false)
public abstract class RoomDb extends RoomDatabase {
    public abstract RecTable recTable();
}
