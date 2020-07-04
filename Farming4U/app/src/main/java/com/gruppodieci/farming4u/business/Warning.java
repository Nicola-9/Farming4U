package com.gruppodieci.farming4u.business;

public class Warning {
    public Warning(){
    }

    public Warning(String warning, boolean isSerious, String data){
        this.warning=warning;
        this.isSerious=isSerious;
        this.data=data;


    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSerious(boolean serious) {
        isSerious = serious;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getWarning() {
        return warning;
    }

    public String getData() {
        return data;
    }

    public boolean isSerious() {
        return isSerious;
    }

    private String data;
    private String warning;
    private boolean isSerious;
}
