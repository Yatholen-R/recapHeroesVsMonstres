package com.java2021.Entites.Race;

import com.java2021.Entites.Heroes;

public class Humain extends Heroes {

    public Humain() throws Exception {
    }

    @Override
    public int getForce() {
        return super.getForce()+1;
    } //modificateur racial

    @Override
    public int getEndu() {
        return super.getEndu()+1;
    } //modificateur racial

    @Override
    public String toString() {
        return super.toString() + " Race: Humain }" ;
    }
}
