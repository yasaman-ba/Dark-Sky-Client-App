
package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currently {

    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("nearestStormDistance")
    @Expose

    private Integer precipProbability;
    @SerializedName("temperature")
    @Expose

    private Double temperature;
    @SerializedName("apparentTemperature")
    @Expose

    private Double ozone;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    //

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    //

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) { this.icon = icon; }

    //

    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

}
