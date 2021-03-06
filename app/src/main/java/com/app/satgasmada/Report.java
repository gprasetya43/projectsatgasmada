package com.app.satgasmada;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

public class Report {
    DocumentReference id;
    Timestamp createdAt;
    String description;
    ArrayList<String> images;
    String latitude;
    String longitude;
    String title;
    String senderId;

    public Report() {
    }

    public Report(DocumentReference id, Timestamp createdAt, String description, ArrayList<String> images, String latitude, String longitude, String title, String senderId) {
        this.id = id;
        this.createdAt = createdAt;
        this.description = description;
        this.images = images;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.senderId = senderId;
    }

    public DocumentReference getId() {
        return id;
    }

    public void setId(DocumentReference id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSenderId() {
        return senderId;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", images=" + images +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", title='" + title + '\'' +
                ", senderId='" + senderId + '\'' +
                '}';
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

}
