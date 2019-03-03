
package hr.dario.musicwebservice.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    public void addRecordings(List<Recording> recordings) {
        this.recordings.addAll(recordings);
    }

    public Recording getRecording(int recPosition) {
        return recordings.get(recPosition);
    }

    public void clearData() {
        recordings.clear();
        setCount(0);
        setOffset(0);
    }

    @Override
    public String toString() {
        StringBuilder strRecordList = new StringBuilder();
        for (Recording recording : recordings) {
            strRecordList.append(recording.toString());
            strRecordList.append(System.getProperty("line.separator"));
            strRecordList.append(System.getProperty("line.separator"));
        }
        return strRecordList.toString();
    }
}
