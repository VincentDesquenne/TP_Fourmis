package fr.ddab.gestion;
import fr.ddab.personnage.*;

import fr.ddab.plateau.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <b>La classe Monde est compose de la classe Grille.</b>
 * <p>Definit par un nombre de nourriture, fourmiliere et de fourmi initial. Toutes les Fourmi du Monde sont
 * stockees dans un tableau.</p>
 * 
 *
 */
public class Monde {
    private Grille plateau;

   	private int nbFourmiInit;
    private int nbNourriture, nbFourmiliere , nbCaseVide;
    private ArrayList <Fourmi> tabFourmis;
    

    /**
     * Constructeur de Monde.
     * Grille du Monde construite a partir de la taille passe en parametre.
     * @param nbNourritureInit : nombre de Nourriture initial du Monde.
     * @param nbFourmiliereInit : nombre de Fourmiliere initial du Monde.
     * @param nbFourmiInit : nombre de Foumi initial du Monde.
     * @param taille : taille de Grille.
     */
    public Monde(int nbNourritureInit,int nbFourmiliereInit, int nbFourmiInit, ArrayList <Short> taille){
	this.nbNourriture = nbNourritureInit;
	this.nbFourmiliere = nbFourmiliereInit;
	this.nbFourmiInit = nbFourmiInit;
	this.tabFourmis = new ArrayList <Fourmi> (nbFourmiInit);
	this.plateau = new Grille(taille);
    }
    
    /**
     * Retourne le nombre de Fourmi initiale.
     * @return Le nombre de Fourmi initiale.
     */
    public int getNbFourmiInit() {
		return nbFourmiInit;
	}
	
	/**
     * Ajoute Nourriture aleatoirement dans Grille de Monde.
     * 
     */

    public void AjouteraleatoireNourriture() {
    int n =0;
    while(n<nbNourriture) { //Implementer Exception si  casevide < nourriture a place
    	int randomX = (int)(Math.random()*plateau.getTaille().get(0));
    	int randomY = (int)(Math.random()*plateau.getTaille().get(1));
    
	    if(plateau.getTabCase().get(randomX).get(randomY).estVide()) {
	    		plateau.getTabCase().get(randomX).set(randomY, new Nourriture());
	    		n++;
	    	}
	    }
    }
    
   /**
    * Ajoute Fourmiliere aleatoirement dans Grille de Monde.
    */
    
    public void AjouteraleatoireFourmiliere() {
        int n =0;
        while(n<nbFourmiliere) {
        	int randomX = (int)(Math.random()*plateau.getTaille().get(0));
        	int randomY = (int)(Math.random()*plateau.getTaille().get(1));
        
    	    if(plateau.getTabCase().get(randomX).get(randomY).estVide()) {
    	    	
    	    		plateau.getTabCase().get(randomX).set(randomY, new Fourmiliere());
    	    		n++;	
    	    }
        }
    }  
    
    /**
     * Met a jour le nombre de Nourriture, Fourmiliere, et Case vide.
     */
    
    public void MettreAJourNbCase() {
    	nbNourriture = 0;
    	nbFourmiliere = 0;
    	nbCaseVide = 0;
    	
    	for(int i=0;i<plateau.getTaille().get(0);i++) {
    		for(int j=0;j<plateau.getTaille().get(1);j++) {
    			if(plateau.getTabCase().get(i).get(j).estNourriture()) {
    				nbNourriture++;
    			}
    			else if(plateau.getTabCase().get(i).get(j).estFourmiliere()) {
    			   nbFourmiliere++;
    			}
    			else {
    				nbCaseVide++;
    			}
    		}
    	}
    }
    
    /**
     * Ajoute autant de Nourriture que le nombre passe en parametre.
     * @param nbAjout : nombre de Nourriture a ajouter a Monde.
     */
    
    public void ajoutNourritureNb(int nbAjout) {
    	for (int i=0;i<nbAjout;i++) {
    		int randomX = (int)(Math.random()*plateau.getTaille().get(0));
        	int randomY = (int)(Math.random()*plateau.getTaille().get(1));
    		if (plateau.getTabCase().get(randomX).get(randomY).estVide()) {
    			plateau.getTabCase().get(randomX).set(randomY, new Nourriture());
    		}
    	}
    }
    
    /**
     * Ajoute autant de Fourmiliere que le nombre passe en parametre.
     * @param nbAjout : nombre de Fouriliere a ajouter a Monde.
     */
    
    public void ajoutFourmiliereNb(int nbAjout) {
    	for (int i=0;i<nbAjout;i++) {
    		int randomX = (int)(Math.random()*plateau.getTaille().get(0));
        	int randomY = (int)(Math.random()*plateau.getTaille().get(1));
    		if (plateau.getTabCase().get(randomX).get(randomY).estVide()) {
    			plateau.getTabCase().get(randomX).set(randomY, new Fourmiliere());
    		}
    	}
    }
    
    /**
     * Definit l'action a faire pour chaque Fourmi du tableau de Fourmi.
     * 
     * @see Fourmi+faireAction(Grille grille)
     */
    
    public void faireAction() {
    	for (Fourmi f : tabFourmis) {
    		f.faireAction (plateau);
    	}
    }
    
    /**
     * Retourne la Grille du Monde.
     * @return La Grille du Monde.
     */
    
	public Grille getGrille() {
		return plateau;
	}
	
	/**
	 * Retourne le nombre de Nourriture present dans Monde.
	 * @return Le nombre de Nourriture present dans Monde.
	 */

	public int getNbNourriture() {
		return nbNourriture;
	}
	
	/**
	 * Retourne le nombre de Fourmiliere present dans Monde.
	 * @return le nombre de Fourmiliere present dans Monde.
	 */

	public int getNbFourmiliere() {
		return nbFourmiliere;
	}
	
	/**
	 * Retourne le nombre de Case vide present dans Monde.
	 * @return le nombre de Case vide present dans Monde.
	 */
	
     public int getNbCaseVide() {
		return nbCaseVide;
	}
     
     /**
      * Ajout d'une Fourmi dans le tableau de Fourmi.
      * @param f : Fourmi a ajouter au tableau de Fourmi.
      */
     
     public void setTabFourmi(Fourmi f){
    	 tabFourmis.add(f);
     }
     
     /**
      * Retourne le tableau de Fourmi
      * @return Le tableau de Fourmi
      */
     
     public ArrayList<Fourmi> getTabFourmi(){
    	 return tabFourmis;
     }
     
     /**
      * Affiche Monde en affichant Grille.
      * @return L'affichage du Monde.
      */
     
     public String afficheMonde() {
    	 return plateau.toString ();
     }
     
     /**
      * Permet l'affichage des Fourmi.
      */
     public void afficherFourmis () {
    	 ArrayList <Short> taille = plateau.getTaille ();
    	 short hauteur = taille.get (0);
    	 short largeur = taille.get (1);
    	 
    	 ArrayList <Short> fposition;
    	 short fx;
    	 short fy;
    	 ArrayList <ArrayList <String>> affichageFourmis = new ArrayList <ArrayList <String>> (hauteur);
    	 
    	 for (short i = 0; i < hauteur; ++i) {
    		 affichageFourmis.add (new ArrayList <String> (largeur));
    		 for (short j = 0; j < largeur; ++j) {
    			 affichageFourmis.get (i). add("");
    		 }
    	 }
    	 for (Fourmi f : tabFourmis) {
    		 fposition = f.getPosition ();
    		 fx = fposition.get (0);
    		 fy = fposition.get (1);
    		 affichageFourmis.get (fx).set (fy, "*");
    	 }
    	 
    	 for (short i = 0; i < hauteur; ++i) {
    		 for (short j = 0; j < largeur; ++j) {
    			 System.out.print (affichageFourmis.get (i).get (j) + " ");
    		 }
    		 System.out.println ();
    	 }
     }
     /**
      * Permet de trier les Fourmis selon leur score. 
      * @return Un tableau de Fourmis trie par ordre croissant en fonction de leur score.
      */
     public ArrayList <Fourmi> trierFourmis () {
    	 ArrayList <Fourmi> tabFourmisTrie = (ArrayList<Fourmi>) tabFourmis.clone();
         Comparator <Fourmi> scoreComparateur = new Comparator <Fourmi>() {
             public int compare(Fourmi f1, Fourmi f2) {
                 return f1.compareTo(f2);
             }
         };
         Collections.sort(tabFourmisTrie, scoreComparateur);
         return tabFourmisTrie;
     }
}

