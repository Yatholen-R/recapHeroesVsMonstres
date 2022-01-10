package com.java2021;

import com.java2021.Entites.Heroes;
import com.java2021.Entites.Monstre;

public class Main {

    public static void main(String[] args) throws Exception {
        Plateau plateau;
        boolean encore = true;

        do{
            plateau = new Plateau();
            plateau.Jouer();
            encore = Toolbox.ScanBol("encore ?");
        }while(encore);

    }
}
