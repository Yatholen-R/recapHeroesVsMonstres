package com.java2021;

import com.java2021.Entites.Entite;
import com.java2021.Entites.Heroes;
import com.java2021.Entites.Monstre;
import com.java2021.Entites.Race.*;

import java.util.ArrayList;

public class Plateau {

    private int hauteur = 15;
    private int largeur = 15;
    private ArrayList<Entite> personnages;
    private Heroes heroes;

    public Plateau() throws Exception {
        personnages = new ArrayList<>(); //creation de liste des participants
        heroes = CreationHero(); // création du héros
        PositionDepart(heroes,0); // position de départ random pour le héros
        personnages.add(heroes); // rajout du héro à la liste des participants
        CreationEnnemis(personnages); // création automatique des ennemis
    }

    public void Jouer() throws Exception {
        Afficher();
        do{
            if(personnages.get(0).isEnVie()) System.out.println(personnages.get(0).toString());
            Mouvement(personnages.get(0));
            nbEnnemiEnVie();
        }while (personnages.get(0).isEnVie() && EnnemisEnVie());

        System.out.println("fin de la partie");
        if(EnnemisEnVie()){
            System.out.println("Perdu!");
        } else {
            System.out.println("Gagné");
        }
    } // Run ?

    private void Mouvement(Entite hero) throws Exception {
        boolean mPasValide = true;
        String mouvement;
        do{
            mouvement = Toolbox.ScanString("entrez le mouvement de votre héros (haut-bas-gauche-droite)");
            mouvement = mouvement.toLowerCase();
            switch (mouvement){
                case "z":
                case "haut":
                    if(hero.getPosY()>0){
                        hero.setPos(hero.getPosX(), hero.getPosY()-1);
                        mPasValide = false;
                    } else {
                        System.out.println("mouvement impossible");
                    }
                    break;
                case "s":
                case "bas":
                    if(hero.getPosY()<hauteur-1){
                        hero.setPos(hero.getPosX(), hero.getPosY()+1);
                        mPasValide = false;
                    }else {
                        System.out.println("mouvement impossible");
                    }
                    break;
                case "q":
                case "gauche":
                    if(hero.getPosX()>0){
                        hero.setPos(hero.getPosX()-1, hero.getPosY());
                        mPasValide = false;
                    }else {
                        System.out.println("mouvement impossible");
                    }
                    break;
                case "d":
                case "droite":
                    if(hero.getPosX()<largeur-1){
                        hero.setPos(hero.getPosX()+1, hero.getPosY());
                        mPasValide = false;
                    }else {
                        System.out.println("mouvement impossible");
                    }
                    break;
                default:
                    System.out.println("mouvement non reconnu");
            }
        }while(mPasValide);
        Afficher();
        ScanPresence(hero);
    } //fonction de déplacement

    private void ScanPresence(Entite hero) throws InterruptedException { // fonction pour détecter la présence d'ennemi à coté du joueur et lancer le combat si oui
        for (int i=1; i<personnages.size(); i++){
            if( DistanceScan(hero, personnages.get(i),1 )
                && personnages.get(i).isEnVie()) {
                hero.Combat(personnages.get(i)); // lance la routine de combat
                Afficher(); // mise à jour de l'affichage
            }
        }
    } // fonction de détecté la

    private void Afficher() {
        boolean caseVide;
        for(int i=0; i< hauteur; i++){
            for(int j=0; j< largeur; j++){
                caseVide=true;
                for(Entite e : personnages){
                    if(e.getPosX() == j && e.getPosY() == i && e instanceof Heroes){
                        if(e.isEnVie()){
                            System.out.print("| H |");
                        } else {
                            System.out.print("| # |");
                        }
                        caseVide = false;
                    }else if (e.getPosX() == j && e.getPosY() == i && e instanceof Monstre && e.isEnVie() && caseVide && DistanceScan(personnages.get(0), e,3)){ // gestion de la portée de la ligne de vue
                        System.out.print("| M |");
                        caseVide = false;
                    } else if (e.getPosX() == j && e.getPosY() == i && e instanceof Monstre && !e.isEnVie() && caseVide) {
                        System.out.print("| m |");
                        caseVide = false;
                    }
                }
                if(caseVide){
                    System.out.print("| _ |");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    } // fonction affichage

    private Heroes CreationHero() throws Exception {
        String race;
        boolean mauvaiseRace = true;
        do{
            race = Toolbox.ScanString("entrer la race de votre héros (Humain ou Nain)");
            race = race.toLowerCase();
            if(race.equals("humain")){
                 return new Humain();
            } else if(race.equals("nain")){
                return new Nain();
            } else {
                System.out.println("race non reconnue ou indisponible");
            }

        }while (mauvaiseRace);
        return new Humain();
    }// choix de race pour le héros
    private void CreationEnnemis(ArrayList Entite) throws Exception {
        int nbEnnemis = Toolbox.ScanInt("nombre d'ennemi ?"); // entrée du nombre + contôle du nombe
        if(nbEnnemis<5){
            nbEnnemis = 5;
        }else if(nbEnnemis>30){
            nbEnnemis=30;
        }
        for(int i=1; i<nbEnnemis+1;i++){ // random race de l'ennemi
            switch (Toolbox.Rnd(1,3)){
                case 1:
                    personnages.add(new Loup());
                    break;
                case 2:
                    personnages.add(new Oque());
                    break;
                default:
                    personnages.add(new dragonnet());
                    break;
            }
            PositionDepart(personnages.get(i),i); // fonction pour positionné l'ennemi
        }
    } // gestion création d'ennemi
    private void PositionDepart(Entite monstre, int k){
        Boolean unique;
        do{
            monstre.setPos(Toolbox.Rnd(0,hauteur-1),Toolbox.Rnd(0,largeur-1));
            unique = true;
            for (int i=0; i< personnages.size();i++){
                if(i!=k && DistanceScan(monstre, personnages.get(i),2)){
                    unique = false;
                }
            }
        } while(!unique);
    } // gère la position de dégarts de l'entité transmise (il faut aussi transmètre son numéro dans la liste)

    private boolean DistanceScan(Entite entite1, Entite entite2, int distance){
        if(Math.abs(entite1.getPosX() - entite2.getPosX()) +
            Math.abs(entite1.getPosY() - entite2.getPosY()) <= distance){
            return true;
        } else{
            return false;
        }
    } // fonction de calcul des distance entre 2 entité renvois vrai si <= à distance
    private boolean EnnemisEnVie(){
        for(int i=1; i< personnages.size(); i++){
            if(personnages.get(i).isEnVie()) return true;
        }
        return false;
    }// renvois si il reste des ennemis en vie
    private void nbEnnemiEnVie(){
        int nb=0;
        for(int i=1; i< personnages.size(); i++){
            if(personnages.get(i).isEnVie()) nb++;
        }
        System.out.println("il reste "+ nb + (nb>1 ? " ennemis":" ennemi"));
    } // comte et affiche le nombre d'ennemi en vie

}
