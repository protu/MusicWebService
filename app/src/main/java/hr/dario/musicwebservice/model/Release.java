
package hr.dario.musicwebservice.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Release  {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("artist-credit")
    @Expose
    private List<ArtistCredit_> artistCredit = null;
    @SerializedName("release-group")
    @Expose
    private ReleaseGroup releaseGroup;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("release-events")
    @Expose
    private List<ReleaseEvent> releaseEvents = null;
    @SerializedName("track-count")
    @Expose
    private Integer trackCount;
    @SerializedName("media")
    @Expose
    private List<Medium> media = null;
    @SerializedName("disambiguation")
    @Expose
    private String disambiguation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ArtistCredit_> getArtistCredit() {
        return artistCredit;
    }

    public void setArtistCredit(List<ArtistCredit_> artistCredit) {
        this.artistCredit = artistCredit;
    }

    public ReleaseGroup getReleaseGroup() {
        return releaseGroup;
    }

    public void setReleaseGroup(ReleaseGroup releaseGroup) {
        this.releaseGroup = releaseGroup;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<ReleaseEvent> getReleaseEvents() {
        return releaseEvents;
    }

    public void setReleaseEvents(List<ReleaseEvent> releaseEvents) {
        this.releaseEvents = releaseEvents;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }

    public String getDisambiguation() {
        return disambiguation;
    }

    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

}
