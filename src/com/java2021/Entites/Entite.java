package com.java2021.Entites;

import com.java2021.Toolbox;

public abstract class Entite {

    private String nom;
    private int endu;
    private int force;
    private int pv;
    private int posX;
    private int posY;
    private Inventaire sac;
    private boolean enVie = true;

    public Entite() throws Exception {
        if(this instanceof Heroes) this.nom = Toolbox.ScanString("entrer un nom pour votre héros");
        if(this instanceof Monstre) this.nom = genNom(); // génère des noms random
        this.nom = this.nom.substring(0, 1).toUpperCase() + this.nom.substring(1).toLowerCase();
        this.endu = StatGen(4, 3);
        this.force = StatGen(4, 3);
        this.pv = getEndu() + getModificateur(getEndu());
        this.sac = new Inventaire(this);
    } //fonction de création des entites

    public String getNom() {
        return nom;
    }

    public int getForce() {
        return force;
    }

    public int getEndu() {
        return endu;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public Inventaire getSac() {
        return sac;
    }

    public void ListeSac() {
        System.out.println(this.sac.toString());
    }

    public boolean isEnVie() { //vérifie l'état d'un entité
        return enVie;
    }

    private int StatGen(int nbLance, int nbLanceGarde){
        int temp =0;
        int[] stats = new int[nbLance];

        for(int i=0; i< stats.length; i++){
            stats[i] = Toolbox.Rnd(1,6);
        }

        Toolbox.sortingTab(stats,false);

        for(int i=0; i< nbLanceGarde; i++){
            temp += stats[i];
        }

        return temp;
    } // génère les stats selon les lancé de dés

    private int getModificateur(int carac){

        if (carac <= 5) {
            return -1;
        } else if (carac <= 10) {
            return 0;
        } else if (carac <= 15) {
            return 1;
        } else {
            return 2;
        }
    } // revois le modificateur de stat

    public void Combat(Entite cible) throws InterruptedException {
        System.out.println(this.toString());
        System.out.println(cible.toString());
        Thread.sleep(500);
        while(this.enVie && cible.enVie){
            Thread.sleep(250);
            this.Attaque(cible);
            Thread.sleep(250);
            if(cible.enVie){
                cible.Attaque(this);
            }
        }

        if(this.isEnVie()){
            System.out.println(this.sac.toString());
        }
        Thread.sleep(1000);
    } //routine de combat

    private void Attaque(Entite cible) {
        int degats = Toolbox.Rnd(1, 4) +getModificateur(this.getForce()) ;
        cible.setPv(cible.getPv() - degats);
        System.out.println(this.nom+ " inflige " + degats + " à " +cible.getNom() +", " + (cible.getPv()<0 ? 0: cible.getPv() )+ " restant");

        if (cible.getPv() <= 0) {
            this.setLoot(cible);
            cible.Mort();
            if(this instanceof Heroes) FinCombat();
        }

    } // action d'attaquer une autre entité

    private void setLoot(Entite cible){
        this.sac.loot(cible);
    } // appel la fonction de loot

    private void FinCombat(){
        int temp = this.getEndu();

        if (temp <= 5) {
            temp -= 1;
        } else if (temp <= 10 && temp > 5) {

        } else if (temp <= 15 && temp > 10) {
            temp += 1;
        } else {
            temp += 2;
        }
        this.setPv(temp);
        System.out.println(this.nom + " a regen");
    } // regen le héros en fin de combats

    private void Mort(){
        this.enVie = false;
        System.out.println(this.getNom() +" est mort");
    } // change le statut de l'entité à mort

    private String genNom(){
        String temp = "";
        int longNom = Toolbox.Rnd(4,10);
        String refConsonne = "zrtupqsdfghjklmwxcvbn";
        String refVoyelle = "aeiouy";

        for(int i=0; i <longNom;i++){
            switch (Toolbox.Rnd(1,3)){
                case 1:
                    temp += refVoyelle.charAt(Toolbox.Rnd(0,5));
                    break;
                default:
                    temp += refConsonne.charAt(Toolbox.Rnd(0,20));
            }

        }
        return temp;
    } // génération de nom pour les monstres

    @Override
    public String toString() {
        return "Entite{" +
                "nom='" + nom + '\'' +
                ", endu=" + endu +
                ", force=" + force +
                ", pv=" + pv;
    }
}
