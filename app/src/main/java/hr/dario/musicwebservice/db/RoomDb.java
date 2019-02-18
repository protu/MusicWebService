package hr.dario.musicwebservice.db;

import android.arch.persistence.room.Database;

import hr.dario.musicwebservice.db.model.DbRecordings;

@Database(entities = {DbRecordings.class}, version = 1, exportSchema = false)
public abstract class RoomDb {
    public abstract RecTable recTable();
}
