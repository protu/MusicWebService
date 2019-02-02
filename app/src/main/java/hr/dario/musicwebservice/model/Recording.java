
package hr.dario.musicwebservice.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recording {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("disambiguation")
    @Expose
    private String disambiguation;
    @SerializedName("video")
    @Expose
    private Object video;
    @SerializedName("artist-credit")
    @Expose
    private List<ArtistCredit> artistCredit = null;
    @SerializedName("releases")
    @Expose
    private List<Release> releases = null;
    @SerializedName("isrcs")
    @Expose
    private List<String> isrcs = null;
    @SerializedName("tags")
    @Expose
    private List<Tag> tags = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getDisambiguation() {
        return disambiguation;
    }

    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    public Object getVideo() {
        return video;
    }

    public void setVideo(Object video) {
        this.video = video;
    }

    public List<ArtistCredit> getArtistCredit() {
        return artistCredit;
    }

    public void setArtistCredit(List<ArtistCredit> artistCredit) {
        this.artistCredit = artistCredit;
    }

    public List<Release> getReleases() {
        return releases;
    }

    public void setReleases(List<Release> releases) {
        this.releases = releases;
    }

    public List<String> getIsrcs() {
        return isrcs;
    }

    public void setIsrcs(List<String> isrcs) {
        this.isrcs = isrcs;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
