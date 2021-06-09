package com.app.satgasmada;

public class NotifModel {

    private String TitleNotif;
    private String DescNotif;
    private String DateNotif;
    private int imgNotif;

    public NotifModel(String titleNotif, String descNotif, String dateNotif, int imgNotif) {
        TitleNotif = titleNotif;
        DescNotif = descNotif;
        DateNotif = dateNotif;
        this.imgNotif = imgNotif;
    }

    public String getTitleNotif() {
        return TitleNotif;
    }

    public void setTitleNotif(String titleNotif) {
        TitleNotif = titleNotif;
    }

    public String getDescNotif() {
        return DescNotif;
    }

    public void setDescNotif(String descNotif) {
        DescNotif = descNotif;
    }

    public String getDateNotif() {
        return DateNotif;
    }

    public void setDateNotif(String dateNotif) {
        DateNotif = dateNotif;
    }

    public int getImgNotif() {
        return imgNotif;
    }

    public void setImgNotif(int imgNotif) {
        this.imgNotif = imgNotif;
    }
}
