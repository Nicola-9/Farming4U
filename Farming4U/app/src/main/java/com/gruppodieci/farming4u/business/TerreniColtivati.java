package com.gruppodieci.farming4u.business;

import java.io.Serializable;
import java.util.Objects;

public class TerreniColtivati implements Serializable {

    private float xPositionInizio;
    private float yPositionInizio;
    private float xPositionFine;
    private float yPositionFine;
    private int type_of_cultivation;
    private String periodo_di_coltivazione;
    private int quantita_da_coltivare;

    public TerreniColtivati(){
    }

    public TerreniColtivati(float x_inizio, float y_inizio, float x_fine, float y_fine){
        this.xPositionInizio = x_inizio;
        this.yPositionInizio = y_inizio;
        this.xPositionFine = x_fine;
        this.yPositionFine = y_fine;
    }

    public TerreniColtivati(float x_inizio, float y_inizio, float x_fine, float y_fine, int s){
        this.xPositionInizio = x_inizio;
        this.yPositionInizio = y_inizio;
        this.xPositionFine = x_fine;
        this.yPositionFine = y_fine;
        this.type_of_cultivation = s;
    }

    public TerreniColtivati(float x_inizio, float y_inizio, float x_fine, float y_fine, int s, String perColtivazione, int quantita){
        this.xPositionInizio = x_inizio;
        this.yPositionInizio = y_inizio;
        this.xPositionFine = x_fine;
        this.yPositionFine = y_fine;
        this.type_of_cultivation = s;
        this.periodo_di_coltivazione = perColtivazione;
        this.quantita_da_coltivare = quantita;
    }


    public Boolean confrontaTerreno(TerreniColtivati t1, TerreniColtivati t2){
        if(t1.getxPositionInizio() == t2.getxPositionInizio()){
            if(t1.getxPositionFine() == t2.getxPositionFine()){
                if(t1.getyPositionInizio() == t2.getyPositionInizio()){
                    if(t1.getyPositionFine() == t2.getyPositionFine()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public float getxPositionInizio() {
        return xPositionInizio;
    }

    public void setxPositionInizio(float xPositionInizio) {
        this.xPositionInizio = xPositionInizio;
    }

    public float getyPositionInizio() {
        return yPositionInizio;
    }

    public void setyPositionInizio(float yPositionInizio) {
        this.yPositionInizio = yPositionInizio;
    }

    public float getxPositionFine() {
        return xPositionFine;
    }

    public void setxPositionFine(float xPositionFine) {
        this.xPositionFine = xPositionFine;
    }

    public float getyPositionFine() {
        return yPositionFine;
    }

    public void setyPositionFine(float yPositionFine) {
        this.yPositionFine = yPositionFine;
    }

    public int getType_of_cultivation() {
        return type_of_cultivation;
    }

    public void setType_of_cultivation(int type_of_cultivation) {
        this.type_of_cultivation = type_of_cultivation;
    }

    @Override
    public String toString() {
        return "TerreniColtivati{" +
                "xPositionInizio=" + xPositionInizio +
                ", yPositionInizio=" + yPositionInizio +
                ", xPositionFine=" + xPositionFine +
                ", yPositionFine=" + yPositionFine +
                ", type_of_cultivation=" + type_of_cultivation +
                ", periodo_di_coltivazione='" + periodo_di_coltivazione + '\'' +
                ", quantita_da_coltivare=" + quantita_da_coltivare +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TerreniColtivati that = (TerreniColtivati) o;
        return Float.compare(that.xPositionInizio, xPositionInizio) == 0 &&
                Float.compare(that.yPositionInizio, yPositionInizio) == 0 &&
                Float.compare(that.xPositionFine, xPositionFine) == 0 &&
                Float.compare(that.yPositionFine, yPositionFine) == 0 &&
                type_of_cultivation == that.type_of_cultivation &&
                quantita_da_coltivare == that.quantita_da_coltivare &&
                Objects.equals(periodo_di_coltivazione, that.periodo_di_coltivazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPositionInizio, yPositionInizio, xPositionFine, yPositionFine, type_of_cultivation, periodo_di_coltivazione, quantita_da_coltivare);
    }
}