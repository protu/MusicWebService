package hr.dario.musicwebservice.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DbRecording {

//    public DbRecording() {}


    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "artist")
    private String artistCredit;

    @ColumnInfo(name = "release_title")
    private String release;

     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtistCredit() {
        return artistCredit;
    }

    public void setArtistCredit(String artistCredit) {
        this.artistCredit = artistCredit;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

}
