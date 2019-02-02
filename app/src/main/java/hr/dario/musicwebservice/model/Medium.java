
package hr.dario.musicwebservice.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medium {

    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("track")
    @Expose
    private List<Track> track = null;
    @SerializedName("track-count")
    @Expose
    private Integer trackCount;
    @SerializedName("track-offset")
    @Expose
    private Integer trackOffset;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<Track> getTrack() {
        return track;
    }

    public void setTrack(List<Track> track) {
        this.track = track;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public Integer getTrackOffset() {
        return trackOffset;
    }

    public void setTrackOffset(Integer trackOffset) {
        this.trackOffset = trackOffset;
    }

}
