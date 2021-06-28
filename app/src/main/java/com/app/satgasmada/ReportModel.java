package com.app.satgasmada;

import com.google.firebase.Timestamp;

public class ReportModel {

    private String Title;
    private String Desc;
    private Timestamp Date;
    private String Sender;
    private int Img;
    private String SenderId;

    public ReportModel(String title, String desc, Timestamp date, String sender, int img, String senderId) {
        Title = title;
        Desc = desc;
        Date = date;
        Sender = sender;
        Img = img;
        SenderId = senderId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp date) {
        Date = date;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public  int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public String getSenderId() {
        return SenderId;
    }

    public void setSenderId(String senderId) {
        SenderId = senderId;
    }
}
