package com.oda.model;

public class Complaint {

    private String informantName;
    private String informantLastName;
    private String denouncedName;
    private String denouncedLAstName;
    private String situation;
    private String address;
    private String latitude;
    private String longitude;
    private String multimedia;

    public String getInformantName() {
        return informantName;
    }

    public void setInformantName(String informantName) {
        this.informantName = informantName;
    }

    public String getInformantLastName() {
        return informantLastName;
    }

    public void setInformantLastName(String informantLastName) {
        this.informantLastName = informantLastName;
    }

    public String getDenouncedName() {
        return denouncedName;
    }

    public void setDenouncedName(String denouncedName) {
        this.denouncedName = denouncedName;
    }

    public String getDenouncedLAstName() {
        return denouncedLAstName;
    }

    public void setDenouncedLAstName(String denouncedLAstName) {
        this.denouncedLAstName = denouncedLAstName;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }
}
