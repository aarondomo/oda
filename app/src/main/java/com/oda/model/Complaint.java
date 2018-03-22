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
    private String date;
    private String status;

    public void setResponsibleLastName(String responsibleLastName) {
        this.responsibleLastName = responsibleLastName;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    private String complaintStatusDescription;

    private String organizationInCharge;
    private String responsibleName;
    private String responsibleLastName;
    private String lastUpdate;
    public Complaint() {
    }


    public Complaint(String informantName, String informantLastName,
                     String denouncedName, String denouncedLAstName,
                     String situation, String address, String latitude,
                     String longitude, String multimedia,
                     String date, String status) {
        this.informantName = informantName;
        this.informantLastName = informantLastName;
        this.denouncedName = denouncedName;
        this.denouncedLAstName = denouncedLAstName;
        this.situation = situation;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.multimedia = multimedia;
        this.date = date;
        this.status = status;
        this.complaintStatusDescription = "";
        this.organizationInCharge = "";
        this.responsibleName = "";
        this.responsibleLastName = "";
        this.lastUpdate = "";
    }

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

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComplaintStatusDescription() {
        return complaintStatusDescription;
    }

    public void setComplaintStatusDescription(String complaintStatusDescription) {
        this.complaintStatusDescription = complaintStatusDescription;
    }

    public String getOrganizationInCharge() {
        return organizationInCharge;
    }

    public void setOrganizationInCharge(String organizationInCharge) {
        this.organizationInCharge = organizationInCharge;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getResponsibleLastName() {
        return responsibleLastName;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "informantName='" + informantName + '\'' +
                ", informantLastName='" + informantLastName + '\'' +
                ", denouncedName='" + denouncedName + '\'' +
                ", denouncedLAstName='" + denouncedLAstName + '\'' +
                ", situation='" + situation + '\'' +
                ", address='" + address + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", multimedia='" + multimedia + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", complaintStatusDescription='" + complaintStatusDescription + '\'' +
                ", organizationInCharge='" + organizationInCharge + '\'' +
                ", responsibleName='" + responsibleName + '\'' +
                ", responsibleLastName='" + responsibleLastName + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}
