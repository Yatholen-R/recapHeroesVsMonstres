package com.java2021.Entites.Race;

import com.java2021.Entites.Monstre;

public class dragonnet extends Monstre {

    public dragonnet() throws Exception {

    }

    @Override
    public int getForce() {
        return super.getEndu()+1;
    } //modificateur racial

    public String toString() {
        return super.toString() + " Race: Dragonnet }" ;
    }
}
