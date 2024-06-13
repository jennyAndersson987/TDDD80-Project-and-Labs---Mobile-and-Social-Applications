package com.example.lab3;

public class Objects {

    public String title;
    public String epost;

    public String namn;
    public String svarade;

    public Objects(String title, String epost, String namn, String svarade) {
        this.title = title;
        this.epost = epost;
        this.namn = namn;
        this.svarade = svarade;
    }

    public String getTitle() {
        return title;
    }

    public String getEpost() {
        return epost;
    }

    public String getNamn() {
        return namn;
    }

    public String getSvarade() {
        return svarade;
    }
}