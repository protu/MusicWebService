
package hr.dario.musicwebservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReleaseEvent implements Serializable {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("area")
    @Expose
    private Area area;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

}
