package com.gruppodieci.farming4u.business;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Warning implements Serializable {
    public Warning(){
    }

    public Warning(String warning, boolean isSerious, String data, String type){
        this.warning=warning;
        this.isSerious=isSerious;
        this.data=data;
        this.type=type;
        this.tagClicked = false;
        this.productQuantity = 0;
        this.days = 0;
    }

    public Warning(String warning, boolean isSerious, String data){
        this.warning=warning;
        this.isSerious=isSerious;
        this.data=data;
        this.tagClicked = false;
        this.productQuantity = 0;
        this.days = 0;
    }

    public Warning(String warning, boolean isSerious){
        this.warning=warning;
        this.isSerious=isSerious;
        LocalDateTime data=LocalDateTime.now();
        String dataStr=""+data.getDayOfMonth()+" "+MonthConverter.getMonth(data.getMonthValue())+" "+data.getYear()+" "+data.getHour()+":"+data.getMinute();
        this.data=dataStr;
        this.tagClicked = false;
        this.productQuantity = 0;
        this.days = 0;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public boolean isSerious() {
        return isSerious;
    }

    public void setSerious(boolean serious) {
        isSerious = serious;
    }

    public int getSizeOfWarning() {
        return sizeOfWarning;
    }

    public void setSizeOfWarning(int sizeOfWarning) {
        this.sizeOfWarning = sizeOfWarning;
    }

    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }

    public boolean getTagClicked() { return this.tagClicked; }

    public void setTagClicked(boolean tagClicked) { this.tagClicked = tagClicked; }

    public int getProductQuantity() {
        return this.productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getDays() {
        return this.days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Warning{" +
                "type='" + type + '\'' +
                ", sizeOfWarning=" + sizeOfWarning +
                ", yPosition=" + yPosition +
                ", xPosition=" + xPosition +
                ", data='" + data + '\'' +
                ", warning='" + warning + '\'' +
                ", isSerious=" + isSerious +
                ", tagClicked=" + tagClicked +
                ", productQuantity=" + productQuantity +
                ", days=" + days +
                '}';
    }

    private String type;
    private int sizeOfWarning;
    private int yPosition;
    private int xPosition;
    private String data;
    private String warning;
    private boolean isSerious;
    private boolean tagClicked;
    private int productQuantity;
    private int days;

    public static final String CONCIMAZIONE = "Carenza di concime";
    public static final String PESTICIDI = "Carenza di pesticidi";
    public static final String ERBA = "Erba alta";
    public static final String IRRIGAZIONE = "Irrigazione necessaria";
}
