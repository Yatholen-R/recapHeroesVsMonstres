package com.java2021.Entites;


import com.java2021.Entites.Race.Loup;
import com.java2021.Toolbox;

public class Monstre extends Entite {

    private int ID;

    public Monstre() throws Exception {
        this.ID = Toolbox.Rnd(1,10000);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
