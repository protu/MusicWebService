
package hr.dario.musicwebservice.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Recording implements  Serializable{

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

    public String getStringReleases() {
        List<Release> releases = getReleases();
        StringBuilder strReleases = new StringBuilder();
        for (Release release : releases) {
            String releaseTitle = release.getTitle();
            if (releaseTitle != null && !releaseTitle.isEmpty())
                strReleases.append(release.getTitle());
            String releaseDate = release.getDate();
            if (releaseDate != null && !releaseDate.isEmpty()) {
                strReleases.append(" (" + release.getDate() + ")");
                strReleases.append(System.getProperty("line.separator"));
            }
        }
        return strReleases.toString();
    }

    public String getStringArtistCredits() {
        List<ArtistCredit> artistList = getArtistCredit();
        StringBuilder sbArtists = new StringBuilder();
        for (ArtistCredit artistCredit : artistList) {
            String artistName = artistCredit.getArtist().getName();
            if (artistName != null && !artistName.isEmpty()) {
                sbArtists.append(artistName);
                sbArtists.append(System.getProperty("line.separator"));
            }
        }
        return sbArtists.toString();
    }

    @Override
    public String toString() {
        StringBuilder strRecording = new StringBuilder();
        strRecording.append(getTitle());
        strRecording.append(System.getProperty("line.separator"));
        strRecording.append(getStringArtistCredits().trim());
        strRecording.append(System.getProperty("line.separator"));
        strRecording.append(getStringReleases().trim());
        return strRecording.toString();
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(id);
//        dest.writeInt(score);
//        dest.writeString(title);
//        dest.writeInt(length);
//        dest.writeString(disambiguation);
//        dest.writeList(artistCredit);
//        dest.writeList(releases);
//        dest.writeList(isrcs);
//        dest.writeList(tags);
//    }
//
//    public static final Parcelable.Creator<Recording> CREATOR = new Parcelable.Creator<Recording>() {
//        @Override
//        public Recording createFromParcel(Parcel source) {
//            return new Recording();
//        }
//
//        @Override
//        public Recording[] newArray(int size) {
//            return new Recording[0];
//        }
//
//    };
}
