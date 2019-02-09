
package hr.dario.musicwebservice.model;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import hr.dario.musicwebservice.R;

public class Record {

    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("recordings")
    @Expose
    private List<Recording> recordings = null;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public List<Recording> getRecordings() {
        return recordings;
    }

    public void setRecordings(List<Recording> recordings) {
        this.recordings = recordings;
    }

    public void removeRecording(int recPosition) {
        recordings.remove(recPosition);
    }

    @Override
    public String toString() {
        StringBuilder strRecordList = new StringBuilder();
        for (Recording recording : recordings) {
            strRecordList.append(recording.getTitle() + " - ");
            List<ArtistCredit> artists = recording.getArtistCredit();
            strRecordList.append(artists.get(0).getArtist().getName());
            strRecordList.append(System.getProperty("line.separator"));
            List<Release> releases = recording.getReleases();
            for (Release release : releases) {
                String releaseTitle = release.getTitle();
                if (releaseTitle != null && !releaseTitle.isEmpty())
                    strRecordList.append(release.getTitle());
                String releaseDate = release.getDate();
                if (releaseDate != null && !releaseDate.isEmpty())
                    strRecordList.append(" (" + release.getDate() + ")");
                strRecordList.append(System.getProperty("line.separator"));
            }
            strRecordList.append(System.getProperty("line.separator"));
            strRecordList.append(System.getProperty("line.separator"));
        }
        return strRecordList.toString();
    }
}
