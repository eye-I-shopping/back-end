package com.example.TT.item.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class itemDto4{
    private double xmin;
    private double ymin;
    private double xmax;
    private double ymax;
    private double confidence;
    @JsonProperty("class")
    private int itemClass;
    private String name;

    // getter and setter methods
    public double getXmin() {
        return xmin;
    }

    public void setXmin(double xmin) {
        this.xmin = xmin;
    }

    public double getYmin() {
        return ymin;
    }

    public void setYmin(double ymin) {
        this.ymin = ymin;
    }

    public double getXmax() {
        return xmax;
    }

    public void setXmax(double xmax) {
        this.xmax = xmax;
    }

    public double getYmax() {
        return ymax;
    }

    public void setYmax(double ymax) {
        this.ymax = ymax;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public int getItemClass() {
        return itemClass;
    }

    public void setItemClass(int itemClass) {
        this.itemClass = itemClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
