
package hr.dario.musicwebservice.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReleaseGroup {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type-id")
    @Expose
    private String typeId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("primary-type")
    @Expose
    private String primaryType;
    @SerializedName("secondary-types")
    @Expose
    private List<String> secondaryTypes = null;
    @SerializedName("disambiguation")
    @Expose
    private String disambiguation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    public List<String> getSecondaryTypes() {
        return secondaryTypes;
    }

    public void setSecondaryTypes(List<String> secondaryTypes) {
        this.secondaryTypes = secondaryTypes;
    }

    public String getDisambiguation() {
        return disambiguation;
    }

    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

}
