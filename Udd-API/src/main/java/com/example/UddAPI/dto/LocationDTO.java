package com.example.UddAPI.dto;

public class LocationDTO {
    private String placeId;
    private String licence;
    private String osmType;
    private String osmId;
    private String[] boundingbox;
    private String lat;
    private String lon;
    private String displayName;
    private String classType;
    private String type;
    private double importance;
    public String getPlaceId() {
        return placeId;
    }
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
    public String getLicence() {
        return licence;
    }
    public void setLicence(String licence) {
        this.licence = licence;
    }
    public String getOsmType() {
        return osmType;
    }
    public void setOsmType(String osmType) {
        this.osmType = osmType;
    }
    public String getOsmId() {
        return osmId;
    }
    public void setOsmId(String osmId) {
        this.osmId = osmId;
    }
    public String[] getBoundingbox() {
        return boundingbox;
    }
    public void setBoundingbox(String[] boundingbox) {
        this.boundingbox = boundingbox;
    }
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLon() {
        return lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getClassType() {
        return classType;
    }
    public void setClassType(String classType) {
        this.classType = classType;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getImportance() {
        return importance;
    }
    public void setImportance(double importance) {
        this.importance = importance;
    }
}