package com.example.haritaolay.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Il implements Serializable {

    private String il;
    private ArrayList<String> ilceler;

    public Il() {

    }

    public Il(String il) {
        this.il = il;
    }

    public Il(String il, ArrayList<String> ilceler) {

        this.il = il;
        this.ilceler=ilceler;
    }


    public void adder(String ilce){
        this.ilceler.add(ilce);
    }
    public ArrayList<String> getIlceler() {
        return ilceler;
    }


    public String getIlAdi() {
        return this.il;
    }

    public void setIlAdi(String ilAdi) {
        this.il = ilAdi;
    }
}
