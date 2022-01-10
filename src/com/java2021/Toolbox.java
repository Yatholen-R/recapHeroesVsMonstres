package com.java2021;

import java.util.Random;
import java.util.Scanner;

public class Toolbox {

    public static int Rnd(int min, int max){
        Random rnd = new Random();
        return (rnd.nextInt(max-min+1) + min);
    } // fonction random avec min et max inclus

    public static void sortingTab(int[] tab, boolean ASC){
        int temp;
        for(int i=0; i< tab.length; i++){
            for(int j=0; j< tab.length-i-1; j++){
                if(ASC){
                    if(tab[j] > tab[j+1]){
                        temp = tab[j];
                        tab[j] = tab[j+1];
                        tab[j+1] = temp;
                    }
                } else {
                    if(tab[j] < tab[j+1]){
                        temp = tab[j];
                        tab[j] = tab[j+1];
                        tab[j+1] = temp;
                    }
                }
            }
        }
    } // tris de tableau

    public static String ScanString(String message) throws Exception {
        Scanner lectureClavier = new Scanner(System.in);
        boolean mauvaiseentree = true;
        String temp = "0";

        while (mauvaiseentree){
            System.out.println(message);
            try{
                temp = lectureClavier.nextLine();
                mauvaiseentree = false;
            } catch (Exception e){
                throw new Exception("l'entrée n'est pas de type chaine de caractère");
            }
        }
        return temp;
    } // scan pour chaine de caractère

    public static int ScanInt(String message) throws Exception {
        Scanner lectureClavier;
        boolean mauvaiseentree = true;
        int temp = 0;

        while (mauvaiseentree){
            System.out.println(message);
            lectureClavier = new Scanner(System.in);
            try{
                temp = lectureClavier.nextInt();
                mauvaiseentree = false;
            } catch (Exception e) {
                e.printStackTrace();
            }finally {

            }
        }
        return temp;
    } // scan pour nombre

    public static boolean ScanBol(String message) throws Exception {
        Scanner lectureClavier;
        boolean mauvaiseentree = true;
        boolean temp = true;

        while (mauvaiseentree){
            lectureClavier = new Scanner(System.in);
            System.out.println(message);
            try{
                temp = lectureClavier.nextBoolean();
                mauvaiseentree = false;
            } catch (Exception e){
                throw new Exception("l'entrée n'est pas un nombre");
            }finally {

            }
        }
        return temp;
    } // scan pour boolean
}
