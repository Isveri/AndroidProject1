package com.example.lab1;

import java.io.Serializable;

public class ModelOceny implements Serializable {
    private String nazwa;
    private final int[] oceny = {2,3,4,5};
    
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
}
