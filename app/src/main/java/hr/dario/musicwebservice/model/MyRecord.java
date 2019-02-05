package hr.dario.musicwebservice.model;

import java.util.UUID;

public class MyRecord {
    private UUID idRecord;
    private String title;
    private String artistCredit;
    private String recording;

    public MyRecord() {
    }

    public MyRecord(String title) {
        this.title = title;
    }

    public void generateId() {
        this.idRecord = UUID.randomUUID();
    }

    public UUID getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(UUID idRecord) {
        this.idRecord = idRecord;
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

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }
}
