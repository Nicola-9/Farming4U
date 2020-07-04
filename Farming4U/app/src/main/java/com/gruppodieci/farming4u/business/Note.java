package com.gruppodieci.farming4u.business;

import java.io.Serializable;
import java.time.LocalDate;

public class Note implements Serializable {
    public Note(){
    }

    public Note(String nota,String data){
        this.nota=nota;
        this.data=data;
        dataSveglia="";
    }

    public String getNota() {
        return nota;
    }

    public String getData() {
        return data;
    }

    public String getDataSveglia() {
        return dataSveglia;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDataSveglia(String dataSveglia) {
        this.dataSveglia = dataSveglia;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    private String dataSveglia;
    private String data;
    private String nota;
}
