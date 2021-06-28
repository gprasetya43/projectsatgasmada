package com.app.satgasmada;

import com.google.firebase.Timestamp;

public class Profile {
    String avatar;
    String email;
    String fcm_token;
    boolean isActive;
    String last_latitude;
    String last_location_datetime;
    Timestamp last_login;
    String last_longitude;
    String mobilephone;
    String name;
    String password;
    String role;

    public Profile() {
    }

    public Profile(String avatar, String email, String fcm_token, boolean isActive, String last_latitude, String last_location_datetime, Timestamp last_login, String last_longitude, String mobilephone, String name, String password, String role) {
        this.avatar = avatar;
        this.email = email;
        this.fcm_token = fcm_token;
        this.isActive = isActive;
        this.last_latitude = last_latitude;
        this.last_location_datetime = last_location_datetime;
        this.last_login = last_login;
        this.last_longitude = last_longitude;
        this.mobilephone = mobilephone;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getLast_latitude() {
        return last_latitude;
    }

    public void setLast_latitude(String last_latitude) {
        this.last_latitude = last_latitude;
    }

    public String getLast_location_datetime() {
        return last_location_datetime;
    }

    public void setLast_location_datetime(String last_location_datetime) {
        this.last_location_datetime = last_location_datetime;
    }

    public Timestamp getLast_login() {
        return last_login;
    }

    public void setLast_login(Timestamp last_login) {
        this.last_login = last_login;
    }

    public String getLast_longitude() {
        return last_longitude;
    }

    public void setLast_longitude(String last_longitude) {
        this.last_longitude = last_longitude;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", fcm_token='" + fcm_token + '\'' +
                ", isActive=" + isActive +
                ", last_latitude='" + last_latitude + '\'' +
                ", last_location_datetime='" + last_location_datetime + '\'' +
                ", last_login=" + last_login +
                ", last_longitude='" + last_longitude + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
