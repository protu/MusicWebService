
package hr.dario.musicwebservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Alias implements Serializable {

    @SerializedName("sort-name")
    @Expose
    private String sortName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("locale")
    @Expose
    private Object locale;
    @SerializedName("type")
    @Expose
    private Object type;
    @SerializedName("primary")
    @Expose
    private Object primary;
    @SerializedName("begin-date")
    @Expose
    private Object beginDate;
    @SerializedName("end-date")
    @Expose
    private Object endDate;

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getLocale() {
        return locale;
    }

    public void setLocale(Object locale) {
        this.locale = locale;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getPrimary() {
        return primary;
    }

    public void setPrimary(Object primary) {
        this.primary = primary;
    }

    public Object getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Object beginDate) {
        this.beginDate = beginDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

}
