package com.julianescalante.clinic.model;

public class Picture {

    private  String picture;
    private String centerName;
    private String address;
    private String info;
    private Double latitud;
    private Double longitud;

    // Constructor
    public Picture(String picture, String centerName, String address,Double latitud, Double longitud) {
        this.picture = picture;
        this.centerName = centerName;
        this.address = address;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getLatitud() { return latitud; }

    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }

    public void setLongitud(Double longitud) { this.longitud = longitud; }
}
