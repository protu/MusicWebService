
package hr.dario.musicwebservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    @Override
    public String toString() {
        StringBuilder strRecording = new StringBuilder();
        strRecording.append(getTitle() + " - ");
        List<ArtistCredit> artists = getArtistCredit();
        strRecording.append(artists.get(0).getArtist().getName());
        strRecording.append(System.getProperty("line.separator"));
        List<Release> releases = getReleases();
        for (Release release : releases) {
            String releaseTitle = release.getTitle();
            if (releaseTitle != null && !releaseTitle.isEmpty())
                strRecording.append(release.getTitle());
            String releaseDate = release.getDate();
            if (releaseDate != null && !releaseDate.isEmpty())
                strRecording.append(" (" + release.getDate() + ")");
            strRecording.append(System.getProperty("line.separator"));
        }
        return strRecording.toString();
    }
}
