package com.app.satgasmada;

public class ReportModel {

    private String Title;
    private String Desc;
    private String Date;
    private String Sender;
    private int Img;

    public ReportModel(String title,String desc, String date, String sender, int img){
        Title = title;
        Desc = desc;
        Date = date;
        Sender = sender;
        Img = img;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
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
}
