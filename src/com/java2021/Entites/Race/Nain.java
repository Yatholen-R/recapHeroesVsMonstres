package com.java2021.Entites.Race;

import com.java2021.Entites.Heroes;

public class Nain extends Heroes {

    public Nain() throws Exception {
    }

    @Override
    public int getEndu() {
        return super.getEndu()+2;
    }

    @Override
    public String toString() {
        return super.toString() + " Race: Nain }";
    }
}
