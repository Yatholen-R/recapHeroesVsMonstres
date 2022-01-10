package com.java2021.Entites.Race;

import com.java2021.Entites.Monstre;

public class Oque extends Monstre {

    public Oque() throws Exception {
    }

    @Override
    public int getForce() {
        return super.getForce()+1;
    } //modificateur racial

    public String toString() {
        return super.toString() + " Race: Orque }" ;
    }
}
