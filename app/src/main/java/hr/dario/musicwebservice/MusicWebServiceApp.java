package hr.dario.musicwebservice;

import android.app.Application;
import android.arch.persistence.room.Room;

import hr.dario.musicwebservice.db.RoomDb;

public class MusicWebServiceApp extends Application {

    public static RoomDb database;

    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(getApplicationContext(), RoomDb.class, "mwsDb").allowMainThreadQueries().build();
    }
}
