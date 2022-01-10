package com.java2021.Entites;

import com.java2021.Entites.Race.Loup;
import com.java2021.Entites.Race.Oque;
import com.java2021.Entites.Race.dragonnet;
import com.java2021.Toolbox;

import java.util.HashMap;

public class Inventaire {

    private int or;
    private int cuir;
    HashMap<String,Integer> autre = new HashMap();

    public Inventaire(Entite entite) {
        if(entite instanceof Heroes) {
            this.cuir = 0;
            this.or = 0;
        }else if (entite instanceof Loup) {
            this.cuir = Toolbox.Rnd(1, 5);
        } else if(entite instanceof Oque) {
            this.or = Toolbox.Rnd(10, 50);
        }else if(entite instanceof dragonnet) {
            this.cuir = (Toolbox.Rnd(1,3));
            this.or = (Toolbox.Rnd(5,30));
        } else {
            this.cuir = 0;
            this.or = 0;
        }
    } // construction de l'inventaire avec gestion racial

    public int getOr() {
        return or;
    }

    public int getCuir() {
        return cuir;
    }

    public HashMap<String, Integer> getAutre() {
        return autre;
    }


    public void loot(Entite entite){
        Inventaire temp = entite.getSac();
        System.out.println(temp.getOr() +" pièces d'or et " + temp.getCuir() +" cuir looté");
        this.or += temp.getOr();
        this.cuir += temp.getCuir();
    } // fonction de loot avec affichage

    @Override
    public String toString() {
        return "Inventaire{" +
                "or=" + or +
                ", cuir=" + cuir +
                '}';
    }
}
