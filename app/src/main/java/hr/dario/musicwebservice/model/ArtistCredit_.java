
package hr.dario.musicwebservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArtistCredit_  {

    @SerializedName("artist")
    @Expose
    private Artist_ artist;

    public Artist_ getArtist() {
        return artist;
    }

    public void setArtist(Artist_ artist) {
        this.artist = artist;
    }

}
