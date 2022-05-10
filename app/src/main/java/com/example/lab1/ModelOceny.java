package com.example.lab1;

import java.io.Serializable;

/**
 * Klasa pomagająca w przetrzymywaniu informacji o ocenach za każdy przedmiot
 */

public class ModelOceny implements Serializable {
    private String nazwa;
    private final int[] oceny = {2,3,4,5};
    private int ocena;
    
    public ModelOceny(String nazwa){
        this.nazwa = nazwa;
    }


    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int[] getOceny() {
        return oceny;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
